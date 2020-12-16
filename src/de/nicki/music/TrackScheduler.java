package de.nicki.music;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import de.nicki.core.SQLite;
import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class TrackScheduler extends AudioEventAdapter{

	public static AudioTrackInfo info;
	
	@Override
	public void onPlayerPause(AudioPlayer player) {
		player.setPaused(true);
	}
	
	@Override
	public void onPlayerResume(AudioPlayer player) {
		player.setPaused(false);
	}
	
	@Override
	public void onTrackStart(AudioPlayer player, AudioTrack track) {
		long guildid = Mainclass.INSTANCE.playerMan.getGuildByPlayerHash(player.hashCode());
		Guild guild = Mainclass.jda.getGuildById(guildid);
		
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.decode("#00e640"));
		info = track.getInfo();
		builder.setDescription("Jetzt läuft: " + info.title);
		
		long sekunden = info.length/1000;
		long minuten = sekunden/60;
		long stunden = minuten/60;
		minuten %=60;
		sekunden %=60;
		
		String url = info.uri;
		builder.addField(info.author, "[" + info.title + "](" + url + ")", false);
		builder.addField("Länge " ,info.isStream? ":red_circle: Stream" : (stunden>0? stunden+"h ": "") + minuten + "min " + sekunden + "s",true);
		
		if(url.startsWith("https://www.youtube.com/watch?v=")) {
			String videoID = url.replace("https://www.youtube.com/watch?v=", "");
			
			InputStream file;
			try {
				file = new URL("https://img.youtube.com/vi/" + videoID + "/hqdefault.jpg").openStream();
				builder.setImage("attachment://thumpnail.png");
				
				ResultSet set = SQLite.onQuery("SELECT * FROM musicchannel WHERE guildid =" + guildid);
				
				try {
					if(set.next()) {
						long channelid = set.getLong("channelid");

						TextChannel channel;
						if((channel = guild.getTextChannelById(channelid)) != null) {
							channel.sendTyping().queue();
							channel.sendFile(file, "thumpnail.png").embed(builder.build()).queue();;
						}	
					}
				}catch(SQLException e) { 
					Mainclass.INSTANCE.getLogMan().errorLog("TrackScheduler.onTrackStart()", e);
				}
			}catch(IOException e) { 
				Mainclass.INSTANCE.getLogMan().errorLog("TrackScheduler.onTrackStart()", e);
			}
		}
		else {
			MusikUtil.sendEmbed(guildid, builder);
		}
		
	}
	
	@Override
	public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
		long guildid = Mainclass.INSTANCE.playerMan.getGuildByPlayerHash(player.hashCode());
		Guild guild = Mainclass.jda.getGuildById(guildid);
		
		if(endReason.mayStartNext) {
			MusikController controller = Mainclass.INSTANCE.playerMan.getController(guildid);
			Queue queue = controller.getQueue();
			
			if(queue.next()) {
				return;	
			}
			else {
				AudioManager manager = guild.getAudioManager();
				player.stopTrack();
				manager.closeAudioConnection();
			}
				
		}
			
	}
	
}
