package com.ismaeelakram.minecrafter.main;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Command {
    public String name = "";
    public String[] aliases;
    public String description = "";
    public boolean visible = true;
    public Permission permission;

    public void execute(String[] args, MessageReceivedEvent event){

    }
}
