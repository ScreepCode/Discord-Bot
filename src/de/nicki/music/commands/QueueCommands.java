package de.nicki.music.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import de.nicki.commands.ServerCommand;
import de.nicki.core.Mainclass;
import de.nicki.music.MusikController;
import de.nicki.music.Queue;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class QueueCommands implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		MusikController controller = Mainclass.INSTANCE.playerMan.getController(channel.getGuild().getIdLong());
		Queue queue = controller.getQueue();
		String [] args = message.getContentDisplay().split(" ");
		
		// Zeigt Befehle die mit Queue funktionieren
		if (args.length == 1) {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setTitle("Queue - Befehle");
			builder.setDescription(""
					+ "!queue list -> Listet Songs in der Queue auf\n"
					+ "!queue clear -> Leert die Warteschlange");
			channel.sendMessage(builder.build()).queue();
			
		}
		
		
		if (args.length == 2) {
			
			//Listet die gesammte Queue auf
			if (args[1].equals("list")) {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setTitle("Aktuelle Queue");
				builder.setDescription("");
				
				
				for(int i=0; i<queue.getQueueList().size();i++) {
					AudioTrack track = queue.getQueueList().get(i);
					AudioTrackInfo info = track.getInfo();
					builder.addField(i+1 +". " + info.author, "[" + info.title + "]", false);
				}
				channel.sendMessage(builder.build()).queue();
			}
			
			// Leert die Queue
			if(args[1].equals("clear")) {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setTitle(queue.getQueueList().size()+" Elemente aus der Queue gelöscht");
				queue.getQueueList().clear();
				channel.sendMessage(builder.build()).queue();
			}
		
		}
		
	}

}
