package com.ismaeelakram.minecrafter.commands;

import com.ismaeelakram.minecrafter.main.Command;
import com.ismaeelakram.minecrafter.utils.API;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;

public class SkinGrabber extends Command {
    public SkinGrabber(){
        this.name = "skin";
        this.description = "Check if a server is online in Minecraft.";
        this.aliases = new String[]{"avatar", "grabskin"};
        this.permission = Permission.UNKNOWN;
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
            return;
        }
        playerNames = (ArrayList) API.getPlayerNames(playerUUID);

        String skinURL = API.getSkin(playerUUID);
        if(skinURL == null){
            skinURL = "https://i.imgur.com/P8Yt5NC.png";
        }

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.green);
        embed.setTitle(playerName + "'s skin");
        embed.setThumbnail(skinURL);
        embed.setFooter("ismaeelakram.com | Mahjestic#9700");

        event.getChannel().sendMessage(embed.build()).queue();
    }
}
