package com.ismaeelakram.minecrafter.commands;

import com.ismaeelakram.minecrafter.main.Command;
import com.ismaeelakram.minecrafter.main.Main;
import com.ismaeelakram.minecrafter.utils.API;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.*;

public class VerifyAccount extends Command {
    public VerifyAccount(){
        this.name = "verify";
        this.description = "To link your account to Minecraft.";
        this.aliases = new String[]{"link", "account", "attach"};
        this.permission = Permission.UNKNOWN;
    }

    @Override
    public void execute(String[] args, MessageReceivedEvent event_){
        User target = event_.getAuthor();

        final String[] username = new String[1];

        final String[] UUID = new String[1];

        target.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(new EmbedBuilder().setColor(Color.green).setDescription("What is your username in Minecraft? **Type cancel to cancel the verification process.**").build()).queue();

            event_.getJDA().addEventListener(new ListenerAdapter() {
                @Override
                public void onMessageReceived(MessageReceivedEvent event) {
                    if(event.getAuthor().isBot()) return;

                    String minecraftUsername = (String) event.getMessage().getContentRaw();
                    username[0] = minecraftUsername;

                    if(username[0].startsWith("cancel")){
                        event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle("Cancelled.").build()).queue();
                        execute(args, event);
                    }
                    if(username[0].isEmpty()){
                        event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle("No username specified.").build()).queue();
                        execute(args, event);
                    }

                    channel.sendMessage(new EmbedBuilder().setColor(Color.green).setDescription("All you have to do now is tell us your UUID. Use [this tutorial](https://www.minecraftforum.net/forums/support/java-edition-support/2392452-finding-your-uuid-using-your-minecraft-client) to help you find it. **Once you have it, remove any hyphens from it (if there are any).**").build()).queue();

                    event_.getJDA().addEventListener(new ListenerAdapter() {
                        @Override
                        public void onMessageReceived(MessageReceivedEvent event) {
                            if(event.getAuthor().isBot()) return;

                            String playerUUID = (String) event.getMessage().getContentRaw();
                            UUID[0] = playerUUID;

                            if(UUID[0].isEmpty()){
                                event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle("No username specified.").build()).queue();
                                execute(args, event);
                            }

                            if(API.getUUID(username[0]).equals(UUID[0])){
                                event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.green).setTitle("Verification successful!").build()).queue();
                                Role verifiedRole = event_.getGuild().getRolesByName("Minecraft Linked", true).get(0);
                                event_.getGuild().addRoleToMember(target.getIdLong(), verifiedRole).queue();
                            }else{
                                event.getChannel().sendMessage(new EmbedBuilder().setColor(Color.red).setTitle("Verification failed. Incorrect UUID.").build()).queue();
                            }

                            event.getJDA().removeEventListener(this);
                        }
                    });

                    event.getJDA().removeEventListener(this);
                }
            });
        });
    }
}
