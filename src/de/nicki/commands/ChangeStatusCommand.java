package de.nicki.commands;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ChangeStatusCommand implements ServerCommand{
	
	private static JDA jda = Mainclass.jda;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		//!changestatus <xyz>
		
		if(!m.hasPermission(Permission.ADMINISTRATOR)) {
			channel.sendMessage("`Du hast nicht die n�tigen Rechte um diesen Command auszuf�hren`").queue();
			return;
		}
		
		String mess = message.getContentDisplay().split(" ")[1];
		
		if(mess.equals("live")) {
			jda.getPresence().setActivity(Activity.streaming("Live auf Twitch", "https://www.twitch.tv/screepcode"));
		}
		else if(mess.equals("listen")) {
			jda.getPresence().setActivity(Activity.listening("dir"));
		}
		else if(mess.equals("play")) {
			jda.getPresence().setActivity(Activity.playing("mit deinem Gewissen ^^"));
		}
		else {
			channel.sendMessage(mess + " ?").queue();
		}
		
		
		
	}
}
