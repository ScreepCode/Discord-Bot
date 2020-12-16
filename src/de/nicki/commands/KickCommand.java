package de.nicki.commands;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class KickCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		if(!m.hasPermission(Permission.KICK_MEMBERS)) {
			channel.sendMessage("`Du hast nicht die nötigen Rechte um diesen Command auszuführen`").queue();
			return;
		}
		
		if(m.hasPermission(Permission.KICK_MEMBERS)) {
			List<Member> mentionedMembers = message.getMentionedMembers();
			
			if(mentionedMembers.isEmpty()) {
				System.out.println("Keine Genannt");
				return;
			}

			Guild guild = message.getGuild();
			guild.kick(mentionedMembers.get(0)).queue();
		}
		
	}

}
