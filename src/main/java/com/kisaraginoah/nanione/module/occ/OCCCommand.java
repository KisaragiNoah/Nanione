package com.kisaraginoah.nanione.module.occ;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class OCCCommand {

    @SubscribeEvent
    public static void onRegisterCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("occ")
                        .then(Commands.argument("command", StringArgumentType.greedyString())
                                .executes(OCCCommand::executeCommand))
        );
    }

    private static int executeCommand(CommandContext<CommandSourceStack> context) {
        String raw = StringArgumentType.getString(context, "command");

        String normalised;
        if (raw.startsWith("/")) {
            normalised = raw.substring(1);
        } else {
            normalised = raw;
        }

        OCCCommandStorage.setStoredCommand(normalised);

        context.getSource().sendSuccess(() ->
                Component.literal("コマンド保存完了： /" + normalised), false);

        return 1;
    }
}
