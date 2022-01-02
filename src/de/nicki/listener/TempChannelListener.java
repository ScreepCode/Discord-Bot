package de.nicki.listener;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class TempChannelListener {

	private static JDA jda = Mainclass.jda;
	
	public static void deleteChannels() {
		for (VoiceChannel channel : jda.getCategoriesByName("Laberecke", true).get(0).getVoiceChannels()) {
			
			if(channel.getName().contains("🕑")) {
				if(channel.getMembers().isEmpty()) {
					channel.delete().queue();
				}
			}
		}	
	}	
}