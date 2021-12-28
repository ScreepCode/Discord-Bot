package de.nicki.music.commands;

import java.awt.Color;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

import de.nicki.commands.ServerCommand;
import de.nicki.core.Mainclass;
import de.nicki.music.AudioLoadResult;
import de.nicki.music.MusikController;
import de.nicki.music.MusikUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class PlayCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
				
		String[] args = message.getContentDisplay().split(" ");
		if(args.length > 1) {
			GuildVoiceState state;
			if((state=m.getVoiceState())!= null) {
				VoiceChannel vc;
				if((vc =state.getChannel())!= null) {
					MusikController controller = Mainclass.INSTANCE.playerMan.getController(vc.getGuild().getIdLong());
					AudioPlayerManager apm = Mainclass.INSTANCE.audioMan;
					AudioManager mangager = vc.getGuild().getAudioManager();
					mangager.openAudioConnection(vc);
					
					MusikUtil.updateChannel(channel);
					
					StringBuilder strBuilder = new StringBuilder();
					for(int i=1; i < args.length; i++) strBuilder.append(args[i] + " ");
					
					String url = strBuilder.toString().trim();
					if(!url.startsWith("http")) {
						url = "ytsearch: " + url;
//						url = "scsearch: " + url;
					}
					EmbedBuilder builder = new EmbedBuilder();
					builder.setTitle("Song wurde hinzugefügt");
					channel.sendMessageEmbeds(builder.build()).queue();
					apm.loadItem(url, new AudioLoadResult(controller, url));
					
					
				}
				else { //Wenn der Nutzer in keinem Voicechannel ist
					EmbedBuilder builder = new EmbedBuilder();
					builder.setDescription("Betrete zuerst einen VoiceChat");
					builder.setColor(Color.decode("#FF0000"));
					channel.sendMessageEmbeds(builder.build()).queue();
				}
			}
			else {	//Wenn der Nutzer keinen VoiceStatus hat
				EmbedBuilder builder = new EmbedBuilder();
				builder.setTitle("Fehler beim benutzen des Musikfeatures");
				builder.setDescription("Betrete zuerst einen VoiceChat");
				builder.setColor(Color.decode("#FF0000"));
				channel.sendMessageEmbeds(builder.build()).queue();
			}
		}
		else {	//Wenn der Nutzer keine Quelle angibt
			EmbedBuilder builder = new EmbedBuilder();
			builder.setTitle("Fehler beim benutzen des Musikfeatures");
			builder.setDescription("Bitte benutze !play [Quelle]");
			builder.setColor(Color.decode("#FF0000"));
			channel.sendMessageEmbeds(builder.build()).queue();
		}
		
	}

}
