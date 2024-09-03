package io.github.plakezz.ghostqol;

import io.github.plakezz.ghostqol.gui.GhostQolGuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.client.ClientCommandHandler;

@Mod(modid = GhostQol.MODID, version = GhostQol.VERSION)
public class GhostQol {
    public static final String MODID = "ghostqol";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Initialize features here (e.g., AchievementTracker)
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        // Register command
        ClientCommandHandler.instance.registerCommand(new CommandGhostQol());
    }

    // Inner class for the command
    public static class CommandGhostQol extends net.minecraftforge.client.ClientCommand {
        @Override
        public String getCommandName() {
            return "ghostqol";
        }

        @Override
        public String getCommandUsage(net.minecraft.command.ICommandSender sender) {
            return "/ghostqol";
        }

        @Override
        public void processCommand(net.minecraft.command.ICommandSender sender, String[] args) {
            // Open the GUI
            net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(new GhostQolGuiScreen());
        }
    }
}