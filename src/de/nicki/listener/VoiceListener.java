package de.nicki.listener;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	public List<Long> tempchannels;

	public List<Long> tmpChannelList;
	
	public VoiceListener() {
		this.tempchannels = new ArrayList<>();

		this.tmpChannelList = new ArrayList<>();
		tmpChannelList.add(785185482388668436l); //Tisch für 2
		tmpChannelList.add(785239198231166996l); //Tisch für 3
		tmpChannelList.add(785185525320384562l); //Tisch für 4
		tmpChannelList.add(785220857873694740l); //Among Us
		tmpChannelList.add(788873336246501459l); //Technik
		tmpChannelList.add(785221024899006475l); //Minecraft
	}
	
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		onJoin(event.getChannelJoined(),event.getEntity(), event.getGuild());
	}
	
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		onLeave(event.getChannelLeft());
	}

	@Override
	public void onGuildVoiceMove (GuildVoiceMoveEvent event) {
		onJoin(event.getChannelJoined(),event.getEntity(), event.getGuild());
		onLeave(event.getChannelLeft());
	}
	
	public void onJoin(VoiceChannel joined, Member memb, Guild guild) {

		if(tmpChannelList.contains(joined.getIdLong())){
			VoiceChannel nvc = joined.createCopy()
				.setName("🕑" + joined.getName())
				.setBitrate(joined.getBitrate())
				.setUserlimit(joined.getUserLimit())
				.setPosition(joined.getPosition())
				.complete();
			if (joined.getParent() != null){
				nvc.getManager().setParent(joined.getParent()).queue();
			}
			// guild.modifyVoiceChannelPositions().selectPosition(nvc).moveTo(joined.getPosition() + 1).queue();
			guild.moveVoiceMember(memb, nvc).queue();
			this.tempchannels.add(nvc.getIdLong());
		}
	}
	
	public void onLeave(VoiceChannel channel) {
		
		if(channel.getMembers().size() <= 0) {
			if(this.tempchannels.contains(channel.getIdLong())){
				this.tempchannels.remove(channel.getIdLong());
				channel.delete().queue();
			}
		}	
	}
	
}
