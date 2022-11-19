package de.nicki.commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class InfoCommand implements ServerCommand{


    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {

        //!info
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Infos zum Bot:");
        builder.setDescription(""
                + "__**Allgemeine Infos**__\n"
                + "Creator: Niklas Buse\n"
                + "Version: " + getClass().getPackage().getImplementationVersion() + "\n"
        );
        builder.setFooter("Benutze den Präfix ! um Commands zu benutzen");
        builder.setColor(Color.decode("#00b5cc"));
        channel.sendMessageEmbeds(builder.build()).queue();

    }
}
