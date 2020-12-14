package de.nicki.music;

import java.util.concurrent.ConcurrentHashMap;

import de.nicki.core.Mainclass;

public class PlayerManager {

	public ConcurrentHashMap<Long, MusikController> controller;
	
	public PlayerManager(){
		this.controller = new ConcurrentHashMap <Long, MusikController>();
	}
	
	public MusikController getController(long guildid) {
		MusikController mc = null;
		
		if(this.controller.containsKey(guildid)) {
			mc = this.controller.get(guildid);
		}
		else {
			mc = new MusikController(Mainclass.jda.getGuildById(guildid));
			
			this.controller.put(guildid, mc);
		}
		
		return mc;
	}
	
	public long getGuildByPlayerHash(int hash) {
		for(MusikController controller : this.controller.values()) {
			if(controller.getPlayer().hashCode() == hash){
				return controller.getGuild().getIdLong();
			}
		}
		
		return -1;
	}
	
}
