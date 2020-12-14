package de.nicki.commands;

import java.awt.Color;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class UmfrageCommand implements ServerCommand{

	private static JDA jda = Mainclass.jda;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		//args[0]	args[...]
		//!umfrage <Text>
		
		
		String mess = message.getContentRaw().substring(9);
		EmbedBuilder builder = new EmbedBuilder();
		TextChannel tc = jda.getTextChannelsByName("umfragen", true).get(0);
		String Username = "";
		
		if(!(m.getNickname()==null)) {
			Username = m.getNickname();
		}
		else {
			Username = m.getUser().getName();
		}
			
		
		builder.setTitle("Umfrage von: " + Username);
		builder.setDescription(mess);
		builder.setColor(Color.decode("#678127"));
		
		message.delete().queue();

		
		tc.sendMessage(builder.build()).queue(add ->{
			add.addReaction("U+1F7E9").queue();
			add.addReaction("U+1F7E5").queue();
		});
		
		//------------------------------------------------
		
	}

}
