package com.ismaeelakram.minecrafter.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDA jda;

    public static final String token = "NzYzMTQ2NTYyNTI0MDIwNzc3.X3zdnw.7JRaAQAx0P_lhNLz5USolbuhdyc";
    public static final String prefix = "mc!";

    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder().setToken(token).setActivity(Activity.playing("mc!help")).build();

        CommandListener commandListener = new CommandListener();
        commandListener.InitCommands();

        jda.addEventListener(commandListener);
    }

}
