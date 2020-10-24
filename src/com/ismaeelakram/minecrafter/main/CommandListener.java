package com.ismaeelakram.minecrafter.main;

import com.ismaeelakram.minecrafter.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    public List<Command> CommandList = new ArrayList<Command>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getMessage().getContentRaw().startsWith(Main.prefix)) return;

        String command = event.getMessage().getContentRaw().split(" ")[0].replace(Main.prefix, "");
        String[] args = event.getMessage().getContentRaw().replace(Main.prefix + command, "").trim().split(" ");

        Command commandObj = ProcessCommand(command, args, event);
        if(commandObj == null) {
            event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("Unknown command.").build()).queue();
            return;
        }
        if(event.getMember().hasPermission(commandObj.permission)){
            commandObj.execute(args, event);
        }else {
            event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.RED).setTitle("Insufficient privileges.").build()).queue();
        }
    }

    public Command ProcessCommand(String cmd, String[] args, MessageReceivedEvent event)
    {
        for(Command command: CommandList){
            if(cmd.equals(command.name)){
                return command;
            }
            if(command.aliases != null) {
                for (String alias : command.aliases) {
                    if (cmd.equals(alias)) {
                        return command;
                    }
                }
            }
        }
        return null;
    }

    public void InitCommands()
    {
        CommandList.add(new PlayerLookup());
        CommandList.add(new ServerStatus());
        CommandList.add(new SkinGrabber());
        CommandList.add(new VerifyAccount());
        CommandList.add(new Help());
    }
}
