package com.proinlab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class DBUtil {

	private Connection con;

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return null;
		}

		try {
			String url = "jdbc:mysql://" + QnASettings.HOST + ":" + QnASettings.PORT + "/" + QnASettings.DB_NAME
					+ "?useUnicode=true&characterEncoding=UTF-8";
			con = DriverManager.getConnection(url, QnASettings.DB_USER, QnASettings.DB_PASSWORD);
		} catch (SQLException e) {
			return null;
		}
		return con;
	}

	private void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
		}
	}

	/**
	 * 
	 * @param query
	 *            : SQL Query
	 * @param rows
	 *            : row in table to find
	 * @return
	 */
	public JSONArray select(String query, String[] rows) {
		JSONArray result = new JSONArray();
		Connection con = getConnection();
		if (con == null)
			return null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				JSONObject row = new JSONObject();
				for (int i = 0; i < rows.length; i++)
					row.put(rows[i], rs.getString(rows[i]));
				result.put(row);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
		}

		closeConnection();
		return result;
	}

	public boolean sendQuery(String query) {
		Connection con = getConnection();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.execute(query);
			stmt.close();
			closeConnection();
			return true;
		} catch (SQLException e) {
			closeConnection();
			return false;
		}

	}
}
