package de.nicki.music.commands;

import java.awt.Color;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import de.nicki.commands.ServerCommand;
import de.nicki.core.Mainclass;
import de.nicki.music.MusikController;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SeekCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		String [] args = message.getContentDisplay().split(" ");
		
		int seekTime = 0;
		
		try {
			seekTime = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		MusikController controller = Mainclass.INSTANCE.playerMan.getController(channel.getGuild().getIdLong());
		AudioPlayer player = controller.getPlayer();
		AudioTrack track = player.getPlayingTrack();

		long timeNow = track.getPosition();
		long timeTotal = track.getInfo().length;
		long timeAfter = timeNow + (seekTime*1000);	
		
		
		if(timeAfter < timeTotal) {
			track.setPosition(timeAfter);
			EmbedBuilder builder = new EmbedBuilder();
			builder.setDescription("Der Song wurde um " + seekTime + " Sekunden vorgespult");
			builder.setColor(Color.decode("#f59542"));
			channel.sendMessage(builder.build()).queue();
		}
		else {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setDescription("Du kannst nicht über das Songende hinausspulen");
			builder.setColor(Color.decode("#FF0000"));
			channel.sendMessage(builder.build()).queue();
		}
			
	}

}
