package io.github.plakezz.ghostqol.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader; // Import JsonReader

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader; // Import StringReader
import java.net.HttpURLConnection;
import java.net.URL;

public class HypixelAPI {

    private static final String API_KEY = "019506b4-6850-4e4e-b6f4-ffdffe5fa402"; // Replace with your API key

    public static JsonObject getPlayerStats(String playerUUID) {
        try {
            URL url = new URL("https://api.hypixel.net/player?key=" + API_KEY + "&uuid=" + playerUUID);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonReader jsonReader = new JsonReader(new StringReader(response.toString()));
            jsonReader.setLenient(true); // Allow for lenient parsing (optional)

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(jsonReader).getAsJsonObject(); // Call parse on the instance

            return jsonObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getGhostKills(String playerUUID) {
        JsonObject playerStats = getPlayerStats(playerUUID);
        if (playerStats != null && playerStats.has("player") && playerStats.getAsJsonObject("player").has("stats")
                && playerStats.getAsJsonObject("player").getAsJsonObject("stats").has("SkyBlock")
                && playerStats.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyBlock").has("kills_ghost")) {
            return playerStats.getAsJsonObject("player").getAsJsonObject("stats").getAsJsonObject("SkyBlock").get("kills_ghost").getAsInt();
        }
        return 0; // Return 0 if data is not found
    }

    public static long getPlaytime(String playerUUID) {
        JsonObject playerStats = getPlayerStats(playerUUID);
        if (playerStats != null && playerStats.has("player") && playerStats.getAsJsonObject("player").has("lastLogin")
                && playerStats.getAsJsonObject("player").has("firstLogin")) {
            long lastLogin = playerStats.getAsJsonObject("player").get("lastLogin").getAsLong();
            long firstLogin = playerStats.getAsJsonObject("player").get("firstLogin").getAsLong();
            return (lastLogin - firstLogin) / 1000; // Playtime in seconds
        }
        return 0; // Return 0 if data is not found
    }

    public static double calculateGhostProfit(int ghostKills, long playtime) {
        // Adapt code from GhostProfitsCalc repository
        // Replace user inputs with API-retrieved data (ghostKills, playtime)

        double killsPerHour = (double) ghostKills / (playtime / 3600.0);
        double profitPerHour = killsPerHour * 0.5; // Assuming 0.5 coins per ghost kill (replace with actual values)
        return profitPerHour;
    }
}
