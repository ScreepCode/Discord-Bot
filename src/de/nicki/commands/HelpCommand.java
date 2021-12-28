package de.nicki.commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelpCommand implements ServerCommand{


    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {

        //!help
            EmbedBuilder builder = new EmbedBuilder();
			builder.setTitle("Hier findest du Hilfe:");
			builder.setDescription(""
					+ "__**Allgemeine Commands**__\n"
					+ "!clear [Anzahl] -> Um Nachrichten zu löschen (Nur wenn du die Rechte hast)\n"
					+ "!hug -> Um Aufmerksamkeit zu bekommen\n"
					+ "!ping -> Um die Antwortzeit des Bots zu erfahren\n\n"
					+ "__**Musikcommands - Kann noch zu Fehlern führen!**__\n"
					+ "!play [Name oder Quelle] -> Um ein Lied zu starten\n"
					+ "!stop -> Um das Lied zu stoppen und den Bot disconnecten\n"
					+ "!pause -> Um das Lied zu pausieren\n"
					+ "!resume -> Um das Lied fortlaufen zu lassen\n"
					+ "!seek [Sekunden] -> Anzahl an Sekunden vorspulen\n"
					+ "!seekto [ss] oder [mm:ss] oder[hh:mm:ss] -> Springt zur Angegeben Stelle\n"
					+ "!queue -> Listet Befehle die mit Queue funktionieren\n"
					+ "!lyrics [Lied] -> Suchen eines Songtextes");
			builder.setFooter("Benutze den Präfix ! um Commands zu benutzen");
			builder.setColor(Color.decode("#00b5cc"));
			channel.sendMessageEmbeds(builder.build()).queue();
    	
        }
    }

