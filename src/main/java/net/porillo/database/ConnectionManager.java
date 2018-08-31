package net.porillo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private Connection connection;
	private String host;
	private int port;
	private String database;
	private String username, password;

	public ConnectionManager(String host, int port, String database, String username, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	public Connection openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		}

		synchronized (this) {
			if (connection != null && !connection.isClosed()) {
				return connection;
			}
			Class.forName("com.mysql.jdbc.Driver");
			String connectionString = "jdbc:mysql://" + this.host + ":" + this.port + "/"
					+ this.database + "?user=" + this.username + "&password=" + this.password +
					"&allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false&useUnicode=true" +
					"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			connection = DriverManager.getConnection(connectionString);
			return connection;
		}
	}
}
