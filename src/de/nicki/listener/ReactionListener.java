package de.nicki.listener;

import java.sql.ResultSet;

import de.nicki.core.Mainclass;
import de.nicki.core.SQLite;
import de.nicki.core.Secrets;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter{

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {

		if(event.getUserIdLong() != Secrets.BotID()) {
			if(event.getChannelType() == ChannelType.TEXT) {

				ResultSet setPre = SQLite.onQuery("SELECT messageid FROM reactroles WHERE guildid = " + event.getGuild().getIdLong());
				try {
					while(setPre.next()) {
						long messIdDB = setPre.getLong("messageid");

						if(messIdDB == event.getMessageIdLong()){
								
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
							break;
						}	
					}
				}
				catch (Exception e) {
					Mainclass.INSTANCE.getLogMan().errorLog("ReactionListener.onMessageReactionAdd()", e);
				}
			}
		}	
	}
	
	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
		
		if(event.getUserIdLong() != Secrets.BotID()) {
			if(event.getChannelType() == ChannelType.TEXT) {

				ResultSet setPre = SQLite.onQuery("SELECT messageid FROM reactroles WHERE guildid = " + event.getGuild().getIdLong());
				try {
					while (setPre.next()) {
						long messIdDB = setPre.getLong("messageid");

						if(messIdDB == event.getMessageIdLong()){
					
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
							break;
						}
					}
				}
				catch (Exception e) {
					Mainclass.INSTANCE.getLogMan().errorLog("ReactionListener.onMessageReactionAdd()", e);
				}	
			}
		}
	}	
}
