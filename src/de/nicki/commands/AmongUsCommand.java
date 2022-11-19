package de.nicki.commands;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class AmongUsCommand implements ServerCommand {
	
	private static JDA jda = Mainclass.jda;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {

		if(m.hasPermission(Permission.VOICE_MUTE_OTHERS)) {
			String mess = message.getContentDisplay().split(" ")[1];
			VoiceChannel vc =  jda.getVoiceChannelById(785185434014973973l);
			
			if(mess.equals("m")) {
				for(Member member: vc.getMembers()) {
					member.mute(true).queue();
					message.delete().queue();
				}
			}
			
			else if(mess.equals("u")) {
				for(Member member: vc.getMembers()) {
					member.mute(false).queue();
					message.delete().queue();
				}
			}
			else{
				channel.sendMessage("Bitte Benutze: `!au [m/u]`").queue();
			}
		}
		else {
			message.delete().queue();
			channel.sendMessage("Du hast dazu keine Berechtigung").queue();
		}
			
	}

}
