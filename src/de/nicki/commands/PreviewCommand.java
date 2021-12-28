package de.nicki.commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class PreviewCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		//!preview 12 3  4
		String mess = message.getContentRaw().substring(9);
		
		EmbedBuilder builder = new EmbedBuilder();
		
		builder.setDescription(mess);
		builder.setColor(Color.decode("#89CFF0"));
		
		message.delete().queue();
		channel.sendMessageEmbeds(builder.build()).queue();
	}

}
