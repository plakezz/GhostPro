package io.github.plakezz.ghostqol.combo;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KillComboTracker {

    private int comboCount = 0;
    private long lastKillTime = 0;

    // Method to be called when a ghost is killed
    public void onGhostKill() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastKillTime <= 5000) { // 5 seconds combo window
            comboCount++;
        } else {
            comboCount = 1;
        }
        lastKillTime = currentTime;

        // Play sound effect or spawn particles based on combo milestones
        if (comboCount % 10 == 0) {
            // ... Play sound effect
        }
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            // Display combo count on screen
            net.minecraft.client.Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Combo: " + comboCount, 10, 10, 0xFFFFFF);
        }
    }
}