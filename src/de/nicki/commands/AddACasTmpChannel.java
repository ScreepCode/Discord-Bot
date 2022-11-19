package de.nicki.commands;

import de.nicki.core.Mainclass;
import de.nicki.core.SQLite;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class AddACasTmpChannel implements ServerCommand{

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        
        if(!m.hasPermission(Permission.ADMINISTRATOR)) {
			channel.sendMessage("`Du hast nicht die nötigen Rechte um diesen Command auszuführen`").queue();
			return;
		}

        try {
            long channelID = Long.parseLong(message.getContentDisplay().split(" ")[1]);
    
            SQLite.onUpdate("INSERT INTO tmpchannel(guildid, channelid) VALUES(" +
                            channel.getGuild().getIdLong() + ", " + channelID + ")");
        } catch (Exception e) {
            Mainclass.INSTANCE.logMan.errorLog("AddACasTmpChannel.performCommand()", e);
            channel.sendMessage("Bitte Benutze: `!addacastc #channel`").queue();
        }
    }
    
}