package net.funnydude.sunsetarmory.registries;

import net.funnydude.sunsetarmory.commands.ReinforcementCommand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class ModCommands {
    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        var commandDispatcher = event.getDispatcher();
        ReinforcementCommand.register(commandDispatcher);
    }
}
