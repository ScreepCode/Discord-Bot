package de.nicki.core;

public class Secrets {

	public static String getToken() {
		String token = System.getenv("DisordBotToken");
		return token; 
	}

	public static long BotOwnerID(){
		long id = 295965839713370123l;
		return id;
	}

	public static long BotID(){
		long id = 673672296296218624l;
		return id;
	}
}
