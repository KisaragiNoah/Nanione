package com.kisaraginoah.nanione;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(value = Dist.CLIENT)
public class FavoriteKeyMappings {

    public static final Lazy<KeyMapping> TOGGLE_FAVORITE = Lazy.of(() ->
            new KeyMapping(
                    "key." + Nanione.MODID + ".toggle_favorite",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_K,
                    KeyMapping.Category.CREATIVE
            ));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_FAVORITE.get());
    }
}
