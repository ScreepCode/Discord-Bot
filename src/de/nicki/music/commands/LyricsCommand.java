package de.nicki.music.commands;

import com.jagrosh.jlyrics.Lyrics;
import com.jagrosh.jlyrics.LyricsClient;

import de.nicki.commands.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class LyricsCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		String titel = message.getContentDisplay().toString().substring(8);
			
		LyricsClient client = new LyricsClient("Genius");
			
		try {
				
			Lyrics lyrics = client.getLyrics(titel).get();
			String lyric = lyrics.getContent();
			int laenge = lyric.length();
			int teile = laenge/1000;
			System.out.println(laenge);
			String [] lyricteile = new String[10];
			int x=0;
			int i;
			for(i=0;i!=teile;i++) {
				System.out.println(x);
				lyricteile[i] = lyric.substring(x, x+=1000);
				System.out.println(x);
				
			}
			lyricteile[i] = lyric.substring(x, laenge);
			
				
			EmbedBuilder builder = new EmbedBuilder();
			builder.setTitle(lyrics.getTitle());
			builder.setDescription("Lyrics");
			for(int j=0;j<=teile;j++) {
				builder.addField("",lyricteile[j], false);
			}
			channel.sendMessage(builder.build()).queue();
			
				
		} catch (Exception e) {
			channel.sendMessage("Zu diesem Titel finde ich keine Lyrics. Sry ^^").queue();
			//Mainclass.INSTANCE.getLogMan().errorLog("LyricsCommand.performCommand()", e);
		}
	}
}
