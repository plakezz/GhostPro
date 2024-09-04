package io.github.plakezz.ghostqol.gui;

import io.github.plakezz.ghostqol.utils.HypixelAPI; // Import HypixelAPI
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GhostQolGuiScreen extends GuiScreen {

    @Override
    public void initGui() {
        // Add buttons, labels, etc. to the GUI
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 2 - 20, 200, 20, "Achievement Tracker"));
        // ... Add other buttons for Kill Combo, Kills/Hour, Profit Calculator
    }

    @Override
    protected void actionPerformed(GuiButton button) { // Removed throws Exception
        // Handle button clicks
        if (button.id == 0) {
            // Open Achievement Tracker screen (if separate)
        }
        // ... Handle other button clicks
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw background, labels, etc.
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "Ghost QOL Mod", width / 2, 20, 0xFFFFFF);
        // ... Draw other elements

        // Fetch data from Hypixel API
        String playerUUID = mc.thePlayer.getUniqueID().toString();
        int ghostKills = HypixelAPI.getGhostKills(playerUUID);
        long playtime = HypixelAPI.getPlaytime(playerUUID);

        // Calculate kills per hour
        double killsPerHour = (playtime > 0) ? (double) ghostKills / (playtime / 3600.0) : 0;

        // Calculate profit
        double profitPerHour = HypixelAPI.calculateGhostProfit(ghostKills, playtime);

        // Display kills per hour and profit
        drawString(fontRendererObj, "Kills per hour: " + String.format("%.2f", killsPerHour), 10, 40, 0xFFFFFF);
        drawString(fontRendererObj, "Estimated profit per hour: " + String.format("%.2f", profitPerHour), 10, 60, 0xFFFFFF);

        // ... (Rest of the drawScreen method)
    }
}