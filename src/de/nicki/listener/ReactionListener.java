package de.nicki.listener;

import java.sql.ResultSet;

import de.nicki.core.Mainclass;
import de.nicki.core.SQLite;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter{

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		
		if(event.getMessageIdLong() == 785214654263066625l || event.getMessageIdLong() == 785234651462893588l) {
			
			if(event.getChannelType() == ChannelType.TEXT) {
//				System.out.println(event.getUserIdLong());
				if(event.getUserIdLong() != 673672296296218624l) {
					
					long guildid = event.getGuild().getIdLong();
					long channelid = event.getChannel().getIdLong();
					long messageid = event.getMessageIdLong();
					String emote = "";
					
					
					if(event.getReactionEmote().isEmoji()) {
						emote = event.getReactionEmote().getEmoji();
					}
					else {
						emote = event.getReactionEmote().getId();
					}
					
					ResultSet set = SQLite.onQuery("SELECT rollenid FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND emote = '" + emote + "'");
					
					try {
												
						if(set.next()) {
							long rollenid = set.getLong("rollenID");
							Guild guild = event.getGuild();
							guild.addRoleToMember(event.getUserIdLong(), guild.getRoleById(rollenid)).complete();
						}
					
					} catch (Exception e) {
						Mainclass.INSTANCE.getLogMan().errorLog("ReactionListener.onMessageReactionAdd()", e);
					}			
				}
			}	
		}
		
	}
	
	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
		
		if(event.getMessageIdLong() == 785214654263066625l || event.getMessageIdLong() == 785234651462893588l) {
			
			if(event.getChannelType() == ChannelType.TEXT) {
//				System.out.println(event.getUserIdLong());
				if(event.getUserIdLong() != 673672296296218624l) {
					
					long guildid = event.getGuild().getIdLong();
					long channelid = event.getChannel().getIdLong();
					long messageid = event.getMessageIdLong();
					String emote = "";
					
					if(event.getReactionEmote().isEmoji()) {
						emote = event.getReactionEmote().getEmoji();
					}
					else {
						emote = event.getReactionEmote().getId();
					}

					ResultSet set = SQLite.onQuery("SELECT rollenid FROM reactroles WHERE guildid = " + guildid + " AND channelid = " + channelid + " AND messageid = " + messageid + " AND emote = '" + emote + "'");
					
					try {
						if(set.next()) {
							long rollenid = set.getLong("rollenid");						
							Guild guild = event.getGuild();
							guild.removeRoleFromMember(event.getUserIdLong(), guild.getRoleById(rollenid)).complete();
							
						}
					} catch (Exception e) {
						Mainclass.INSTANCE.getLogMan().errorLog("ReactionListener.onMessageReactionRemove()", e);
					}
				}
			}	
		}
		
		
	}	
}
