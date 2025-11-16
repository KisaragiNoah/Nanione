package com.kisaraginoah.nanione.module.otc;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(value = Dist.CLIENT)
public class OTCKeyMappings {

    public static final Lazy<KeyMapping> COMMAND_TOGGLE = Lazy.of(() ->
            new KeyMapping(
                    "key.nanione.command_toggle",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_H,
                    KeyMapping.Category.CREATIVE
            ));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(COMMAND_TOGGLE.get());
    }
}
