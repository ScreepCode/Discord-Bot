package de.nicki.commands;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class TestCommand implements ServerCommand{

	private static JDA jda = Mainclass.jda;
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		channel.sendMessage("Test").queue();
		jda.toString();
			
	}

}