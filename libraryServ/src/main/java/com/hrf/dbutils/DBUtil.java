package com.hrf.dbutils;

//package {Add package name}

import java.sql.*; //Importing Java package that deals with database.
import java.util.ArrayList;
import java.util.List;
//If you want to do serious runtime error tracing, use the following pckages.
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* This class provides static functions for accessing to and retrieving contents from Database.
*
* Students are permitted to use and modidy this code for any pueposes.
*
* @author Scott Piao
* School of Computing and Communications, Lancaster University, UK
*/
public class DBUtil {

  /**Connect to database and return the conenction.
   * @param dbDriver Database driver class name.
   * @param dbURL Database URL address.
   * @param username Database host username.
   * @param passwird Database access password.
   * @retutn Database conenction; If the database cannot be connected, return null.
   */
  public static Connection getConnection(String dbDriver, String dbURL, String username, String password) {

      Connection connection = null;

      try {
          //System.out.println("OK first");
          Class.forName(dbDriver).newInstance();
          //System.out.println("OK here");
          connection = DriverManager.getConnection(dbURL, username, password);
          return connection;
      } catch (Exception e) {
          System.out.println("Database cannot be connected!");
          e.printStackTrace();
      }
      return null;
  }

  /**Connect to local database and return the conenction.
   * @retutn Database conenction; If the database cannot be connected, return null.
   */
  public static Connection getLocalConnection() {

      Connection connection2 = null;
		
		//Modify the following code for your own MySql database.
		String databaseAddress = "jdbc:mysql://192.168.1.15/lib";
		String databaseUsername = "root";
		String databasePassword = "123456";

      try {
          // Class.forName("com.mysql.jdbc.Driver").newInstance();
    	  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();	
          connection2 = DriverManager.getConnection(databaseAddress, databaseUsername, databasePassword);
          System.out.println("Database connected.");
          return connection2;
      } catch (Exception e) {
          System.err.println("Database cannot be connected!");
          e.printStackTrace();
          return null;
      }

  }



  /**Close database connection.
   *@param c Database Connection object.
   */
  public static void close(Connection c) {
      if (c != null) {
          try {
              c.close();
              System.out.println("Database connection closed.");
          } catch (Exception e) {
              /* ignore close errors */
              System.err.println("Database cannot be closed!");
              e.printStackTrace();
          }
      }
  }

  /**
   * @param rsmd JDBC ResultSetMetaData object.
   * @return List of Strings containing Column Names.
   */
  public static List<String> getColumnNames(ResultSetMetaData rsmd) {

      try {
          int colNum = rsmd.getColumnCount();
          List<String> colNames = new ArrayList<String>();

          for (int i = 1; i <= colNum; i++) {
              colNames.add(rsmd.getColumnName(i));
          }

          return colNames;
      } catch (SQLException e) {
          System.err.println("Error in retrieving column names.");
          e.printStackTrace();
      }

      return null;
  }

  /**
   * @param rset JDBC ResultSet.
   * @return List of Lists containing the elements of a table
   */
  public static List<List> getResult(ResultSet rset) {

      List<List> result = new ArrayList<List>();
      List<String> row;

      try {
          int colNum = rset.getMetaData().getColumnCount();
          while (rset.next()) {
              row = new ArrayList<String>();

              for (int i = 1; i <= colNum; i++) {
                  row.add(rset.getString(i));
              }

              result.add(row);
          }
          return result;
      } catch (SQLException e) {
          System.err.println("Error in retrieving data.");
          e.printStackTrace();
      }

      return null;
  }

  /**
   * @param rset JDBC ResultSet.
   * @return List of Lists containing the elements of a table
   */
  public static List<List> getFilteredResult(ResultSet rset, int col, String filterValue) {

      List<List> result = new ArrayList<List>();
      List<String> row;

      try {
          int colNum = rset.getMetaData().getColumnCount();
          while (rset.next()) {

              //If the value of this colums equals to the filter string, ignore this row.
              if (rset.getString(col).equals(filterValue)) {
                  continue;
              }

              row = new ArrayList<String>();
              for (int i = 1; i <= colNum; i++) {
                  row.add(rset.getString(i));
              }

              result.add(row);
          }
          return result;
      } catch (SQLException e) {
          System.err.println("Error in retrieving data.");
          e.printStackTrace();
      }

      return null;
  }

  public static void main(String[] args) {
      try {
          Connection c = getLocalConnection();
          String query = "Select * from books;";
          Statement statement = c.createStatement();

          ResultSet resultSet = statement.executeQuery(query);
          List<List> result = DBUtil.getResult(resultSet);
          if (result == null) {
              return;
          }

          System.out.println(result);
          String id = (String) result.get(0).get(0);

          System.out.println(id);


      } catch (SQLException ex) {
			System.err.println("Error in retrieving data.");
          ex.printStackTrace();
          Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
      }



  }
}

