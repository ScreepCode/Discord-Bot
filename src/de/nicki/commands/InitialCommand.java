package de.nicki.commands;

import java.awt.Color;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class InitialCommand implements ServerCommand{

	private static JDA jda = Mainclass.jda;
	
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		
		Guild guild = jda.getGuildById(message.getContentDisplay().split(" ")[1]);
		
		if(!m.isOwner()) {
			channel.sendMessage("`Du hast nicht die nötigen Rechte um diesen Command auszuführen`").queue();
			return;
		}
		
		createCathegories(guild);
		createChannels(guild);
		createRoles(guild);
		
	}
	
	public void createCathegories(Guild guild) {
		guild.createCategory("dev").complete();
	}
	
	public void createChannels(Guild guild) {
		String[] devChannelNames = {"error_log", "bot_action_log", "dev_talk", "test_area"};
		String[] essentialChannelNames = {"umfragen", "rollenverteilung", "regeln", "bots"};
		String[] additionalChannelNames = {"memes"};
		
		for(int i = 0; i < devChannelNames.length; i++) {
			Category cat = jda.getCategoriesByName("dev", true).get(0);
			cat.createTextChannel(devChannelNames[i]).queue();
		}
		
		for(int i = 0; i < essentialChannelNames.length; i++) {
			Category cat = jda.getCategoriesByName("Textkanäle", true).get(0);
			cat.createTextChannel(essentialChannelNames[i]).queue();
		}
		for(int i = 0; i < additionalChannelNames.length; i++) {
			Category cat = jda.getCategoriesByName("Textkanäle", true).get(0);
			cat.createTextChannel(additionalChannelNames[i]).queue();
		}
	}
	
	public void createRoles(Guild guild) {
		
		String[][] roles = {{"Admin", "#FF0000"}, {"Dev", "#00FF00"}, {"Moderator", "#0000FF"}};
		
		for(int i = 0; i < roles.length; i++) {
			String hexcode = roles[i][1];
			String roleName = roles[i][0];
			
			guild.createRole().queue(role ->{
				Color color = Color.decode(hexcode);
				role.getManager().setName(roleName).setColor(color).setPermissions(Permission.MESSAGE_READ, Permission.MESSAGE_HISTORY, Permission.VOICE_CONNECT).queue();
			});
		}
	}
}
