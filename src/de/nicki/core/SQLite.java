package de.nicki.core;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {

	private static Connection conn;
	private static Statement stmt;
	
	public static void connect() {
		conn = null;
		
		try {
			File file = new File("datenbank.db");
			if(!file.exists()) {
				file.createNewFile();
			}
			
			String url = "jdbc:sqlite:" + file.getPath();
			conn = DriverManager.getConnection(url);
			
			System.out.println("Verbindung zur Datenbank hergestellt.");
			
			stmt = conn.createStatement();
			
		}catch (Exception e) {
			Mainclass.INSTANCE.getLogMan().errorLog("SQLite.connect()", e);
		}
	
	}
	
	public static void disconnect() {
		try {
			if(conn != null) {
				conn.close();
				System.out.println("Verbindung zur Datenbank getrennt.");
			}
			
		} catch (SQLException e) {
			Mainclass.INSTANCE.getLogMan().errorLog("SQLite.disconnect()", e);
		}
	
	}
	
	public static void onUpdate(String sql) {
		try {
			stmt.execute(sql);
		}catch(SQLException | NullPointerException e) {
			Mainclass.INSTANCE.getLogMan().errorLog("SQLite.onUpdate()", e);
		}
	}
	
	public static ResultSet onQuery(String sql) {
		try {
			return stmt.executeQuery(sql);
		}catch(SQLException e){
			Mainclass.INSTANCE.getLogMan().errorLog("SQLite.ResultSet()", e);
		}
		
		return null;
	}
}