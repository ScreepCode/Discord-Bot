package de.nicki.music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import de.nicki.commands.ServerCommand;
import de.nicki.core.Mainclass;
import de.nicki.music.MusikController;
import de.nicki.music.MusikUtil;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class StopCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		
		GuildVoiceState state;
		if((state=m.getVoiceState())!= null) {
			VoiceChannel vc;
			if((vc =state.getChannel())!= null) {
				MusikController controller = Mainclass.INSTANCE.playerMan.getController(vc.getGuild().getIdLong());
				AudioManager manager = vc.getGuild().getAudioManager();
				AudioPlayer player = controller.getPlayer();
				MusikUtil.updateChannel(channel);
				player.stopTrack();
				manager.closeAudioConnection();
			}
		}
	}
}
