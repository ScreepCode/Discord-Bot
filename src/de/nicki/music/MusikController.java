package de.nicki.music;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import de.nicki.core.Mainclass;
import net.dv8tion.jda.api.entities.Guild;

public class MusikController {

	
	private Guild guild;
	private AudioPlayer player;
	private Queue queue;
	
	public MusikController(Guild guild) {
		this.guild = guild;
		this.player = Mainclass.INSTANCE.audioMan.createPlayer();
		this.queue = new Queue(this);
		
		this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player)); 
		this.player.addListener(new TrackScheduler());
		this.player.setVolume(20);
	}
	
	public Guild getGuild() {
		return guild;
	}
	
	public AudioPlayer getPlayer() {
		return player;
	}
	
	public Queue getQueue() {
		return queue;
	}
		
}
