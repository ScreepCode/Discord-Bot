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

public class SeektoCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		String [] args = message.getContentDisplay().split(" ");
		int seekTimeInMs = 0;
		
		String[] timeArray = parseMessage(args[1]);
		
		int seconds = parseSeconds(timeArray[0]);
		int minutes = parseMinutes(timeArray[1]);
		int hours = parseHours(timeArray[2]);
		
		seekTimeInMs = calculateSeekTime(seconds, minutes, hours);		
		
		MusikController controller = Mainclass.INSTANCE.playerMan.getController(channel.getGuild().getIdLong());
		AudioPlayer player = controller.getPlayer();
		AudioTrack track = player.getPlayingTrack();

		long timeTotalInMs = track.getInfo().length;
				
		if(seekTimeInMs < timeTotalInMs) {
			track.setPosition(seekTimeInMs);
			EmbedBuilder builder = new EmbedBuilder();
			builder.setDescription("Der Song wurde auf " + args[1] + " Sekunden vorgespult");
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
	
	

	private String[] parseMessage(String time) {
		String [] timeSplit = time.split(":");
		String [] timeArray = new String[3];
		
		if(timeSplit.length == 1) {
			timeArray[0] = timeSplit[0];
		}
		else if(timeSplit.length == 2) {
			timeArray[0] = timeSplit[1];
			timeArray[1] = timeSplit[0];
		}
		else if(timeSplit.length == 3) {
			timeArray[0] = timeSplit[2];
			timeArray[1] = timeSplit[1];
			timeArray[2] = timeSplit[0];
		}
		
		return timeArray;
	}

	private int calculateSeekTime(int sec, int min, int hours) {
		int calculatedSeekTime = (sec*1000) + (min*60*1000) + (hours*60*60*1000);
		return calculatedSeekTime;
	}
	
	private int parseSeconds(String time) {
		try {
			int timeAsInt = Integer.parseInt(time);
			return timeAsInt;
		}
		catch(NumberFormatException e){
			return 0;
		}
	}
	
	private int parseMinutes(String time) {
		try {
			int timeAsInt = Integer.parseInt(time);
			return timeAsInt;
		}
		catch(NumberFormatException e){
			return 0;
		}
		
	}
	
	private int parseHours(String time) {
		try {
			int timeAsInt = Integer.parseInt(time);
			return timeAsInt;
		}
		catch(NumberFormatException e){
			return 0;
		}
	}

}