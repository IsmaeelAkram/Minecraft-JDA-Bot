package com.ismaeelakram.minecrafter.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
}
