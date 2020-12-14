package de.nicki.core;

public class SQLManager {

	public static void onCreate() {
		
		//(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, channelID INTEGER)
		
		SQLite.onUpdate("CREATE TABLE IF NOT EXISTS musicchannel(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER)");
	
		SQLite.onUpdate("CREATE TABLE IF NOT EXISTS reactroles(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER, messageid INTEGER, emote VARCHAR, rollenid INTEGER)");
	}
	
}
