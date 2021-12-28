package de.nicki.core;

import java.util.concurrent.ConcurrentHashMap;

import de.nicki.commands.*;
import de.nicki.music.commands.*;
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
		// this.commands.put("init", new InitialCommand());
		// this.commands.put("au", new AmongUsCommand());
		this.commands.put("addacastc", new AddACasTmpChannel());
		
		
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
