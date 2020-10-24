package com.ismaeelakram.minecrafter.commands;

import com.ismaeelakram.minecrafter.main.Command;
import com.ismaeelakram.minecrafter.utils.API;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class ServerStatus extends Command {
    public ServerStatus(){
        this.name = "serverstatus";
        this.description = "Check if a server is online in Minecraft.";
        this.aliases = new String[]{"server", "status", "up"};
        this.permission = Permission.UNKNOWN;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event){
        event.getChannel().sendTyping().queue();

        String serverIP = args[0];

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.yellow);
        eb.setTitle("Checking server status... :hourglass:");
        eb.addField(new MessageEmbed.Field("IP", serverIP, true, true));
        eb.addField(new MessageEmbed.Field("Port", "25565", true, true));

        MessageEmbed embed = eb.build();
        event.getChannel().sendMessage(embed).queue(message -> {
            boolean serverStatus;
            int serverPing = API.serverPing(serverIP);
            if(serverPing == -1){
                serverStatus = false;
            }else{
                serverStatus = true;
            }
            eb.setTitle(serverStatus ? "Server is on. :white_check_mark:" : "Server is off. :x:");
            eb.addField(new MessageEmbed.Field("Status", serverStatus ? "On" : "Off", true, true));
            eb.addField(new MessageEmbed.Field("Ping", serverPing + "ms", true, true));
            eb.setColor(serverStatus ? Color.green : Color.red);
            eb.setFooter("ismaeelakram.com | Mahjestic#9700");
            message.editMessage(eb.build()).queue();
        });
    }
}
