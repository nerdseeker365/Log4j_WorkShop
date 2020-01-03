package com.nt.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class ConnTest_Log4J_Test {
	private static Logger logger=Logger.getLogger(ConnTest_Log4J_Test.class);
	static {
		//Layout Append Object
		Layout l1=new SimpleLayout();
		//create Append Object
		Appender ap=new ConsoleAppender(l1);
		logger.addAppender(ap);
		logger.setLevel(Level.DEBUG);
	}

	public static void main(String[] args) {
		logger.debug("main method");
		Connection con=null;
		logger.debug("Start");
		//Registering driver .it is optional
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.warn("class.forname is optional");
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
		logger.info("Connection is Established");
		
		PreparedStatement ps=con.prepareStatement("SELECT * FROM EMP");
		logger.debug("successfully done ps");
		ResultSet rs=ps.executeQuery();
		logger.debug("reading first row rs");
		logger.info("Display Each columns value");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		}
		logger.info("after getting result");
		} catch (ClassNotFoundException e) {
			logger.error("error in class.forname");
			e.printStackTrace();
		}
		catch(Exception e) {
			logger.error("error in url or username or pwd");
			e.printStackTrace();
		}
		logger.debug("End");
	}

}
