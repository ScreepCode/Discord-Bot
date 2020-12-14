package de.nicki.core;

import java.util.concurrent.ConcurrentHashMap;

import de.nicki.commands.BanCommand;
import de.nicki.commands.ChangeStatusCommand;
import de.nicki.commands.ClearCommand;
import de.nicki.commands.HelpCommand;
import de.nicki.commands.HugCommand;
import de.nicki.commands.KickCommand;
import de.nicki.commands.AmongUsCommand;
import de.nicki.commands.PingCommand;
import de.nicki.commands.PreviewCommand;
import de.nicki.commands.ReactCommand;
import de.nicki.commands.ReactRoleCommand;
import de.nicki.commands.RoleCreationCommand;
import de.nicki.commands.ServerCommand;
import de.nicki.music.commands.LyricsCommand;
import de.nicki.music.commands.PauseCommand;
import de.nicki.music.commands.PlayCommand;
import de.nicki.music.commands.QueueCommands;
import de.nicki.music.commands.ResumeCommand;
import de.nicki.music.commands.SeekCommand;
import de.nicki.music.commands.SeektoCommand;
import de.nicki.music.commands.SkipCommand;
import de.nicki.music.commands.StopCommand;
import de.nicki.commands.TestCommand;
import de.nicki.commands.UmfrageCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager {

	public ConcurrentHashMap<String, ServerCommand> commands;
	
	public CommandManager() {
		this.commands = new ConcurrentHashMap<>();
		
		//Allgemein
		this.commands.put("clear", new ClearCommand());
		this.commands.put("help", new HelpCommand());
		this.commands.put("hug", new HugCommand());
		this.commands.put("ping", new PingCommand());
		this.commands.put("kick", new KickCommand());
		this.commands.put("ban", new BanCommand());
		this.commands.put("preview", new PreviewCommand());
		this.commands.put("react", new ReactCommand());
		this.commands.put("reactrole", new ReactRoleCommand());
		this.commands.put("createrole", new RoleCreationCommand());
		this.commands.put("umfrage", new UmfrageCommand());
		this.commands.put("changestatus", new ChangeStatusCommand());
		//this.commands.put("init", new InitialCommand());
		this.commands.put("au", new AmongUsCommand());
		
		
		//Musikfeature
		this.commands.put("play", new PlayCommand());
		this.commands.put("stop", new StopCommand());
		this.commands.put("pause", new PauseCommand());
		this.commands.put("resume", new ResumeCommand());
		this.commands.put("seek", new SeekCommand());
		this.commands.put("seekto", new SeektoCommand());
		this.commands.put("skip", new SkipCommand());
		this.commands.put("queue", new QueueCommands());
		this.commands.put("lyrics", new LyricsCommand());
		this.commands.put("test", new TestCommand());
		}
	
	public boolean perform(String command, Member m, TextChannel channel, Message message) {
		
		ServerCommand cmd;
		if ((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.performCommand(m, channel, message);
			return true;
		}
		
		return false; 
	}
}
