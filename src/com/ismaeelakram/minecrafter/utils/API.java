package com.ismaeelakram.minecrafter.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

public abstract class API {
    public static String getUUID(String playerName){
        try {
            String UUID;
            String response = Request.get("https://api.mojang.com/users/profiles/minecraft/" + playerName + "?at=" + System.currentTimeMillis() / 1000L);
            if (response == null) return null;

            JsonObject jsonObj = new JsonParser().parse(response).getAsJsonObject();
            return jsonObj.get("id").getAsString();
        }catch (Exception e){
            return null;
        }
    }

    public static String getSkin(String playerUUID){
        try {
            Gson gson = new Gson();
            String response = Request.get("https://sessionserver.mojang.com/session/minecraft/profile/" + playerUUID);

            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
            String skinJsonRaw = new String(Base64.getDecoder().decode(jsonObject.get("properties").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString().getBytes()), StandardCharsets.UTF_8);
            JsonObject skinJson = new JsonParser().parse(skinJsonRaw).getAsJsonObject();

            return skinJson.get("textures").getAsJsonObject().get("SKIN").getAsJsonObject().get("url").getAsString();

        }catch (Exception e){
            return null;
        }
    }

    public static ArrayList getPlayerNames(String playerUUID){
        try {
            Gson gson = new Gson();
            String response = Request.get("https://api.mojang.com/user/profiles/" + playerUUID + "/names");
            JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();

            ArrayList namesList = gson.fromJson(jsonArray.toString(), ArrayList.class);
            return namesList;
        }catch (Exception e){
            return null;
        }
    }

    public static int serverPing(String serverIP){
        long sentMillis = System.currentTimeMillis();
        try (Socket s = new Socket(serverIP, 25565)) {
            long receivedMillis = System.currentTimeMillis();
            long ping = receivedMillis - sentMillis;
            return (int)ping;
        } catch (IOException ex) {
            return -1;
        }
    }
}
