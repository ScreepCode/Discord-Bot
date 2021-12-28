package de.nicki.commands;

import de.nicki.core.Mainclass;
import de.nicki.core.SQLite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class AddMessageAsReactionMessage implements ServerCommand{

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        
        try {
            long channelID = channel.getIdLong();
            long messID = Long.parseLong(message.getContentDisplay().split(" ")[1]);
    
            SQLite.onUpdate("INSERT INTO reactroleMessages(guildid, channelid, messageid) VALUES(" +
                            channel.getGuild().getIdLong() + ", " + channelID + ", " + messID + ")");
        } catch (Exception e) {
            Mainclass.INSTANCE.logMan.errorLog("AddMessageAsReactionMessage.performCommand()", e);
        }
    }
    
}
