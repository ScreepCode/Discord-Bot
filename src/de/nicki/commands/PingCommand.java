package de.nicki.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class PingCommand implements ServerCommand  {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		long gatewayping = channel.getJDA().getGatewayPing();
		
		channel.getJDA().getRestPing().queue( (time) ->
		channel.sendMessageFormat("Pong! %dms", time , gatewayping).queue());

	}

}
