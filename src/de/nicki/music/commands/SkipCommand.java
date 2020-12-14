package de.nicki.music.commands;

import de.nicki.commands.ServerCommand;
import de.nicki.core.Mainclass;
import de.nicki.music.MusikController;
import de.nicki.music.Queue;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SkipCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		MusikController controller = Mainclass.INSTANCE.playerMan.getController(channel.getGuild().getIdLong());
		Queue queue = controller.getQueue();
		int tracks = queue.getQueueList().size();
		if(tracks >0) {
			queue.next();
		}
		else {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setTitle("Es ist der Letzte Song der Queue ;)");
			builder.setFooter("Wenn du dennoch dieses Lied beenden möchtest nutze !stop");
			channel.sendMessage(builder.build()).queue();
		}
		
		
		
	}

	
}
