package de.nicki.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import de.nicki.listener.CommandListener;
import de.nicki.listener.ReactionListener;
import de.nicki.listener.TempChannelListener;
import de.nicki.listener.VoiceListener;
import de.nicki.music.PlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Mainclass {

	public static Mainclass INSTANCE;
	
	public static JDA jda;
	public CommandManager cmdMan;
	public LogManager logMan;
	public AudioPlayerManager audioMan;
	public PlayerManager playerMan;
	
	Secrets secret = new Secrets();
	
	public static void main(String[] args){
		try {
			new Mainclass();
		} 
		catch (LoginException | IllegalArgumentException e) {
			Mainclass.INSTANCE.getLogMan().errorLog("Mainclass.main()", e);
		}
	}
	
	public Mainclass() throws LoginException, IllegalArgumentException {

		INSTANCE = this;
		
		SQLite.connect();
		SQLManager.onCreate();
		
		jda = JDABuilder.createDefault(secret.getToken()).build();
		
		jda.getPresence().setActivity(Activity.playing("mit deinem Gewissen ^^"));
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		
		this.audioMan = new DefaultAudioPlayerManager();
		this.playerMan = new PlayerManager();
		
		this.cmdMan = new CommandManager();
		this.logMan = new LogManager();
		
		jda.addEventListener(new CommandListener());
		jda.addEventListener(new VoiceListener());
		jda.addEventListener(new ReactionListener());
		
		try {
			jda.awaitReady();
			TempChannelListener.deleteChannels();
		} catch (Exception e) {
			Mainclass.INSTANCE.getLogMan().errorLog("Mainclass.TmpChannelListener",e);
		}
		
		System.out.println("Bot Online");

		AudioSourceManagers.registerRemoteSources(audioMan);
		audioMan.getConfiguration().setFilterHotSwapEnabled(true);
		
		shutdown();
	}
	
	public void shutdown() {
		
		new Thread (() -> {
			
			String line =" ";
			
			try {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				
				while((line = reader.readLine()) != null){
					
					if(line.equalsIgnoreCase("exit")) {
						if(jda != null) {
							jda.getPresence().setStatus(OnlineStatus.OFFLINE);
							jda.shutdown();
							SQLite.disconnect();
							System.out.println("Bot offline");
							System.exit(0);
					
						}
						
					}
					else {
						System.out.println("Use exit to shutdown");
					}
				}
				
			}catch (IOException e) {
				//e.printStackTrace();
				System.exit(0);
		
			}
			
		}).start();
		
	}

	public CommandManager getCmdMan() {
		return cmdMan;
	}
	
	public LogManager getLogMan() {
		return logMan;
	}
	
}
