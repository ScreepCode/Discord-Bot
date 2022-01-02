package de.nicki.core;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

public class LogManager{

	private static JDA jda = Mainclass.jda;
	
	public void errorLog(String location, Exception e) {
		
		String errorString = "";
		
//		for(int i = 0; i < e.getStackTrace().length; i++) {
		for(int i = 0; i < 3; i++) {
			errorString = errorString + e.getStackTrace()[i] + "\n";
		}
		
		EmbedBuilder builder = new EmbedBuilder();
		
		builder.setTitle("Fehler bei: " + location);
		builder.setDescription("Fehlerart: " + e.toString() + "\n\n" + errorString);
		builder.setColor(Color.red);
		
		try {
			jda.getTextChannelsByName("error_log", true).get(0).sendMessageEmbeds(builder.build()).queue();
		}
		catch (IndexOutOfBoundsException ignore) {
			System.out.println("Erstellen sie einen Channel mit dem Namen error_log um die Fehler dieses Bot über Discord zu bekommen");
			System.out.println(errorString);
		}
		
	}
	
	public void triggerError() {
		try {
			jda.getTextChannelById(750781823641976864l).sendMessage("Test").queue(); //Falsche ID
		} catch (Exception e) {
			Mainclass.INSTANCE.getLogMan().errorLog("LogManager.triggerError()", e);
		}
	}
	
}
