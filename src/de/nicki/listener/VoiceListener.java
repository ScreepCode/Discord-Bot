package de.nicki.listener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import de.nicki.core.Mainclass;
import de.nicki.core.SQLite;
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

		ResultSet set = SQLite.onQuery("SELECT channelid FROM tmpchannel WHERE guildid = " + guild.getIdLong());
		try {
			if(set.next()) {
				long channelID = set.getLong("channelid");

				if(channelID == joined.getIdLong()){
					VoiceChannel nvc = joined.createCopy()
						.setName("🕑" + joined.getName())
						.setBitrate(joined.getBitrate())
						.setUserlimit(joined.getUserLimit())
						.setPosition(joined.getPosition())
						.complete();
					if (joined.getParent() != null){
						nvc.getManager().setParent(joined.getParent()).queue();
					}
					guild.moveVoiceMember(memb, nvc).queue();
					this.tempchannels.add(nvc.getIdLong());
				}
			}
		}
		catch (Exception e) {
            Mainclass.INSTANCE.logMan.errorLog("VoiceListener.onJoin()", e);
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
