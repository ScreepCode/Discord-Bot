package de.nicki.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import de.nicki.commands.ServerCommand;
import de.nicki.core.Mainclass;
import de.nicki.music.MusikController;
import de.nicki.music.TrackScheduler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ResumeCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		MusikController controller = Mainclass.INSTANCE.playerMan.getController(channel.getGuild().getIdLong());
		TrackScheduler scheduler = new TrackScheduler();
		AudioPlayer player = controller.getPlayer();
		scheduler.onPlayerResume(player);
	}

}
