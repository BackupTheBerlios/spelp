package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

public class BourseDAO {
	private static BourseDAO instance = new BourseDAO () ;
	private Connection conn = null ;
	
	public Connection getConnection () {
		return conn ;
	}
	
	private BourseDAO () {
		 try {
			 Class.forName(InitDatabase.driver).newInstance();
			 Properties props = new Properties();
			 props.put("user", "admin");
			 props.put("password", "admin");
			 conn  = DriverManager.getConnection(InitDatabase.protocol +
					 InitDatabase.databaseName + ";create=true", props);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long generateId (String table, String column)throws Exception {
		String selectUser = String.format(
				"select max(%s) from %s", column,table);
		
		ResultSet rs = null ;
		Statement s = null ;
		long result = 0 ;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(selectUser);
			if (!rs.next())
			{
				throw new Exception("Pas d'utilisateur");
			}
			result = rs.getLong(1)+1;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close() ;
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		return result ;
	}
	
	
	
	public static BourseDAO getInstance() {
		return instance ;
	}

	

	
}
