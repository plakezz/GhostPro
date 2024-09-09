package io.github.plakezz.ghostqol;

import io.github.plakezz.ghostqol.gui.GhostQolGuiScreen;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = GhostQol.MODID, version = GhostQol.VERSION)
public class GhostQol {
    public static final String MODID = "ghostqol";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Initialize features here (e.g., AchievementTracker)
        ClientCommandHandler.instance.registerCommand(new CommandGhostQol());// Register command here
        FMLLog.info("Ghost QOL Mod initialized successfully.");
    }


    // Inner class for the command
    @SideOnly(Side.CLIENT)
    public static class CommandGhostQol implements ICommand {

        @Override
        public String getCommandName() {
            return "ghostqol";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/ghostqol";
        }

        @Override
        public List<String> getCommandAliases() {
            return new ArrayList<>(); // No aliases
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            // Open the GUI on the client-side only
            if (sender instanceof net.minecraft.client.entity.EntityPlayerSP) {
                net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(new GhostQolGuiScreen());
            } else {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used on the client-side."));
            }
        }

        @Override
        public boolean canCommandSenderUseCommand(ICommandSender sender) {
            return true; // Allow all players to use the command
        }

        @Override
        public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
            return null; // No tab completion
        }

        @Override
        public boolean isUsernameIndex(String[] args, int index) {
            return false;
        }

        @Override
        public int compareTo(ICommand o) {
            return 0;
        }
    }
}