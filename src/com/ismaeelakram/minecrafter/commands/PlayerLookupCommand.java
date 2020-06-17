package com.ismaeelakram.minecrafter.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ismaeelakram.minecrafter.main.Command;
import com.ismaeelakram.minecrafter.utils.API;
import com.ismaeelakram.minecrafter.utils.Request;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;

public class PlayerLookupCommand extends Command {
    public PlayerLookupCommand(){
        this.name = "lookup";
        this.description = "Look up a player's history in Minecraft.";
        this.aliases = new String[]{"info", "player"};
        this.permission = Permission.KICK_MEMBERS;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event){
        event.getChannel().sendTyping().queue();

        String playerName = args[0];
        String playerUUID;
        ArrayList playerNames;
        playerUUID = API.getUUID(playerName);
        if(playerUUID == null){
            event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("Player not found.").build()).queue();
        }
        playerNames = (ArrayList) API.getPlayerNames(playerUUID);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.green);
        embed.setTitle(playerName + "'s Player Info");
        embed.addField(new MessageEmbed.Field("UUID", playerUUID, true, true));

        final String[] playerNamesText = {""};
        playerNames.forEach(name -> {
            JsonObject jsonObj = new JsonParser().parse(name.toString()).getAsJsonObject();
            playerNamesText[0] += jsonObj.get("name").toString().replace("\"", "") + "\n";
        });

        embed.addField(new MessageEmbed.Field("All names", playerNamesText[0], true, true));

        event.getChannel().sendMessage(embed.build()).queue();
    }
}
