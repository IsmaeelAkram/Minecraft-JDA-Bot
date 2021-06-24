package com.ismaeelakram.minecrafter.commands;

import com.ismaeelakram.minecrafter.main.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends Command {
    public Ping(){
        this.name = "ping";
        this.description = "Check if bot is online";
        this.aliases = new String[]{"pong"};
        this.permission = Permission.UNKNOWN;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("🏓 Pong!");
    }
}
