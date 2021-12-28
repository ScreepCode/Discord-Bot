package de.nicki.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class HugCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		// if(m.getIdLong() != 561206675416023041l) {
			channel.sendMessage("Fühle dich Umarmt ^^").queue();
		// }
		// else {
		// 	channel.sendMessage("Jeder wird umarmt, nur du nicht xD").queue();
		// }

	}

	
}
