package de.nicki.music;

import java.util.ArrayList;
import java.util.List;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class Queue {

	private List<AudioTrack> queuelist;
	private MusikController controller;
	
	public Queue(MusikController controller){
		this.setController(controller);
		this.setQueueList(new ArrayList<AudioTrack>());
	}

	public boolean next() {
		if(this.queuelist.size() >= 1) {
			AudioTrack track = queuelist.remove(0);
			
			if(track != null) {
				this.controller.getPlayer().playTrack(track);
				return true;
			}
		}
		return false;
	}
	
	public void addTrackToQueue(AudioTrack track) {
		this.queuelist.add(track);
		
		if(controller.getPlayer().getPlayingTrack()==null) {
			next();
		}
	}
	
	public MusikController getController() {
		return controller;
	}

	public void setController(MusikController controller) {
		this.controller = controller;
	}
	
	public List<AudioTrack> getQueueList(){
		return queuelist;
	}
	
	public void setQueueList(List<AudioTrack> queuelist) {
		this.queuelist = queuelist;
	}
	
}
