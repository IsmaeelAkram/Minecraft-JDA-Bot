package com.ismaeelakram.minecrafter.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;

    public static final String token = System.getenv("TOKEN");
    public static final String prefix = "mc!";

    public static void main(String[] args) throws LoginException {
        System.out.println("Token is " + token);

        jda = JDABuilder.createDefault(token)
                .addEventListeners(new CommandListener())
                .setActivity(Activity.playing("mc!help"))
                .build();
    }

}
