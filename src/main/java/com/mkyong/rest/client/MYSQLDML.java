package com.mkyong.rest.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
public class MYSQLDML {
   public static void main(String[] args) {
	   
	   dbInsert("2022-03-15 10:13:02, otp", "2022-03-15 10:13:02", "A", "STC", "95650471543");
   }
   
   public static String dbInsert(String msgBody, String msgSentTime, String status,String serviceProvider, String mobileNum) {
      Connection conn = null;
      java.sql.Statement stmt = null;
      try {
         try {
         //   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	 Class.forName("com.mysql.jdbc.Driver");
         } catch (Exception e) {
            System.out.println(e);
      }
//         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//         Date date = new Date();
//         String currentTime = dateFormat.format(date);
//         System.out.println("currentTime:"+currentTime);
      //conn =  DriverManager.getConnection("jdbc:sqlserver://bb-moon.database.windows.net;databaseName=SMSMON", "dbmgmt", "KG&sas2sv");
      conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/SRS", "root", "vasa@123");
      System.out.println("Connection is created successfully:");
      stmt = conn.createStatement();
      
      List<String> msgBodyList = Arrays.asList(msgBody.split(","));
      if(msgBodyList.size() > 0) {
      String query1 = "INSERT INTO smsmonitor (msgBody, msgSentTime, status, serviceProvider, mobileNum, queue) VALUES ('"+msgBody+"','"+msgBodyList.get(0)+"', '"+status+"','"+serviceProvider+"','"+mobileNum+"','"+msgBodyList.get(1)+"')";
      stmt.executeUpdate(query1);
      
      System.out.println("Record is inserted in the table successfully..................");
      return "Record is inserted in the table successfully..................";
      }
      return "Error in fetching queue name";
      } catch (SQLException excep) {
         excep.printStackTrace();
         return "SQLException";
      } catch (Exception excep) {
         excep.printStackTrace();
         return "Exception";
      } finally {
         try {
            if (stmt != null)
               conn.close();
         } catch (SQLException se) {}
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }  
      }
   }
}