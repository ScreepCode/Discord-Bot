package de.nicki.commands;

import java.util.List;

import de.nicki.core.Mainclass;
import de.nicki.core.SQLite;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class ReactRoleCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		//args[0]		args[1]		args[2]	args[3]	args[4]
		//!reactrole 	#channel 	messID 	:one: 	@Rolle
		
		if(!m.hasPermission(Permission.ADMINISTRATOR)) {
			channel.sendMessage("`Du hast nicht die nötigen Rechte um diesen Command auszuführen`").queue();
			return;
		}
		
		String[] args = message.getContentDisplay().split(" ");

		List<TextChannel> channels = message.getMentionedChannels();
		List<Role> roles = message.getMentionedRoles();
		List<Emote> emotes = message.getEmotes();
		
		
		if(!channels.isEmpty() && !roles.isEmpty()) {
			TextChannel tc = channels.get(0);
			Role role = roles.get(0);
			String messageIDString = args[2];

			
			try {
				long messageID = Long.parseLong(messageIDString);
				
				if(!emotes.isEmpty()) {
					Emote emote = emotes.get(0);
					tc.addReactionById(messageID, emote).queue();
					SQLite.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, rollenid) VALUES(" +
						channel.getGuild().getIdLong() + ", " + tc.getIdLong() + ", " + messageID + ", '" + emote.getId() + "', " + role.getIdLong() + ")");
				}
				else {
					String emote = args[3];
					tc.addReactionById(messageID, emote).queue();
					SQLite.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, rollenid) VALUES(" +
							channel.getGuild().getIdLong() + ", " + tc.getIdLong() + ", " + messageID + ", '" + emote + "', " + role.getIdLong() + ")");
				}
			} catch (NumberFormatException e) { 
				Mainclass.INSTANCE.getLogMan().errorLog("ReactRoleCommand.performCommand()", e);
			}
		}
		else {
			channel.sendMessage("Bitte Benutze: `!reactrole #channel messageID :emote: @Rolle`").queue();
		}
		
	}
}
