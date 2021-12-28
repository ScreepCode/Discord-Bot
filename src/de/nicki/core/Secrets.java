package de.nicki.core;

public class Secrets {

	public  String getToken() {
		String token = System.getenv("DisordBotToken");
		return token; 
	}
}
