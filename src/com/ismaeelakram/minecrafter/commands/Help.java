package com.ismaeelakram.minecrafter.commands;

import com.ismaeelakram.minecrafter.main.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Help extends Command {
    public Help(){
        this.name = "help";
        this.description = "Shows all the commands and what they do.";
        this.aliases = new String[]{"h", "commands"};
        this.permission  = Permission.UNKNOWN;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.green);
        embed.setTitle("Minecrafter Commands");
        embed.setDescription("i can't help you yet. sorry.");
        embed.setFooter("ismaeelakram.com | Mahjestic#9700");

        event.getChannel().sendMessage(embed.build()).queue();
    }
}
