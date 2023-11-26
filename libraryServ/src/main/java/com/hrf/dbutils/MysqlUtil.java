package com.hrf.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MysqlUtil extends DBUtil {
	private final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String MYSQL_DRIVER_CLASS_NAME_LEGACY = "com.mysql.jdbc.Driver";
	private String dbURL;
	private String loginName;
	private String password;
	private Connection conn;

	public MysqlUtil(String dbURL, String loginName, String password) {
		this.dbURL = dbURL;
		this.loginName = loginName;
		this.password = password;
		this.conn = null;
	}

	/**
	 * open a mysql db try with a new class name firstly. try again with the legacy
	 * class name if failed
	 */
	public boolean open() {
		this.conn = null;

		try {
			conn = super.getConnection(MYSQL_DRIVER_CLASS_NAME, dbURL, loginName, password);
		} catch (Exception e) {
			// fail to get class name of driver
			conn = null;
		}

		if (conn != null) {
			return true;
		}

		try {
			conn = super.getConnection(MYSQL_DRIVER_CLASS_NAME_LEGACY, dbURL, loginName, password);
		} catch (Exception e) {
			// fail again.
			conn = null;
			return false;
		}
		return true;
	}

	private static List<Map<String,Object>> getQueryResult(ResultSet rs) {
		
		try {
			List<Map<String, Object>> result = new ArrayList<>();
			List<String> fieldNames = DBUtil.getColumnNames(rs.getMetaData());
			int colNum = fieldNames.size();
			// return if empty
			if( (fieldNames==null) ||(colNum==0)) {
				return result;
			}
			
			while (rs.next()) {
				Map<String,Object> row = new HashMap<String, Object>();

				for (int i = 1; i <= colNum; i++) {
					row.put(fieldNames.get(i-1), rs.getObject(i));
				}
				result.add(row);
			} // end of while
			
			return result;
		} catch (SQLException e) {
			System.err.println("Error in retrieving data.");
			e.printStackTrace();
		}
		//get here if exception
		return null;
	}

	/*
	 * execute a query and return the result in a Map (generic)
	 */
	public List<Map<String,Object>> executeQuery(String sql) {
		List<Map<String,Object>> result = new ArrayList<>();
		if (this.conn == null) {
			if (!this.open()) {
				return result;
			}
		}
		try {
			String query = "Select * from books;";
			Statement statement = this.conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return getQueryResult(rs);
		} catch (Exception e) {
			System.err.println("Error in retrieving data.");
			e.printStackTrace();
		}
		return result; //It's better than null
	}
}
