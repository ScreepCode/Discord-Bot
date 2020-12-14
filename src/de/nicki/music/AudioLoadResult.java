package de.nicki.music;

import java.awt.Color;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.EmbedBuilder;

public class AudioLoadResult implements AudioLoadResultHandler{
	
	private final MusikController controller;
	private final String uri;
	
	public AudioLoadResult(MusikController controller,String uri) {
		this.controller = controller;
		this.uri = uri;
	}
	
	
	@Override
	public void trackLoaded(AudioTrack track) {
//		controller.getPlayer().playTrack(track);
		Queue queue = controller.getQueue();
		queue.addTrackToQueue(track);
	}

	@Override
	public void playlistLoaded(AudioPlaylist playlist) {
		Queue queue = controller.getQueue();
		
		if(uri.startsWith("ytsearch: ")) {
			queue.addTrackToQueue(playlist.getTracks().get(0));
			return;
		}
		
		int added = 0;
		
		for(AudioTrack track : playlist.getTracks()) {
			queue.addTrackToQueue(track);
			added++;
		}
		
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.decode("#8c14fc"));
		builder.setDescription(added + " Songs in die Queue");
		
		MusikUtil.sendEmbed(controller.getGuild().getIdLong(), builder);
		
	}

	@Override
	public void noMatches() {
		
	}

	@Override
	public void loadFailed(FriendlyException exception) {
		
	}

}
