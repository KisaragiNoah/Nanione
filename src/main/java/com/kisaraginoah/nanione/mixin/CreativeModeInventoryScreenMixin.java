package com.kisaraginoah.nanione.mixin;

import com.kisaraginoah.nanione.FavoritesManager;
import com.kisaraginoah.nanione.FavoritesTabRefresher;
import com.kisaraginoah.nanione.ModCreativeTabs;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin implements FavoritesTabRefresher {

    @Shadow
    private float scrollOffs;

    @Shadow
    private static CreativeModeTab selectedTab;


    @Unique
    private void nanione$rebuildFavorites(boolean resetScroll) {
        if (selectedTab != ModCreativeTabs.FAVORITES_TAB.get()) return;

        AbstractContainerMenu rawMenu = ((AbstractContainerScreenAccessor) this).nanione$getMenu();

        CreativeModeInventoryScreen.ItemPickerMenu pickerMenu = (CreativeModeInventoryScreen.ItemPickerMenu) rawMenu;

        NonNullList<ItemStack> items = pickerMenu.items;

        items.clear();
        for (ItemStack fav : FavoritesManager.getFavoritesStacks()) {
            ItemStack display = fav.copy();
            display.setCount(1);
            items.add(display);
        }

        if (resetScroll) {
            this.scrollOffs = 0.0F;
        }
        pickerMenu.scrollTo(this.scrollOffs);
    }

    @Inject(method = "selectTab", at = @At("TAIL"))
    private void nanione$onSelectTab(CreativeModeTab tab, CallbackInfo ci) {
        nanione$rebuildFavorites(true);
    }

    @Override
    public void nanione$refreshFavoritesTab() {
        nanione$rebuildFavorites(false);
    }
}