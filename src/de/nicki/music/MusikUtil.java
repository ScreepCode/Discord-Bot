package de.nicki.music;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.nicki.core.SQLite;
import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class MusikUtil {

	public static void updateChannel(TextChannel channel) {
		ResultSet set = SQLite.onQuery("SELECT * FROM musicchannel WHERE guildid =" + channel.getGuild().getIdLong());
		
		try {
			if(set.next()) {
				SQLite.onUpdate("UPDATE musicchannel SET channelid = " + channel.getIdLong() + " WHERE guildid = " + channel.getGuild().getIdLong());
				
			}
			else {
				SQLite.onUpdate("INSERT INTO musicchannel(guildid, channelid) VALUES (" + channel.getGuild().getIdLong() +","+channel.getIdLong()+")");
			}
			
		}catch(SQLException e) { 
			Mainclass.INSTANCE.getLogMan().errorLog("MusikUtil.updateChannel()", e);
		} 
	}
	
	public static void sendEmbed(long guildid, EmbedBuilder builder) {
		
		ResultSet set = SQLite.onQuery("SELECT * FROM musicchannel WHERE guildid =" + guildid);
		
		try {
			if(set.next()) {
				long channelid = set.getLong("channelid");
				
				Guild guild;
				if((guild = Mainclass.jda.getGuildById(guildid)) != null) {
					TextChannel channel;
					if((channel = guild.getTextChannelById(channelid)) != null) {
						channel.sendMessageEmbeds(builder.build()).queue();
					}
				}
			}
		}catch(SQLException e) { 
			Mainclass.INSTANCE.getLogMan().errorLog("MusikUtil.sendEmbed()", e);
		} 
	}
	
}
