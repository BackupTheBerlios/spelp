package dao;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class InitDatabase {

    public static String framework = "embedded";
    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String protocol = "jdbc:derby:";
    public static String databaseName = "bourse";
	
    public static String SQL_CREATE_TABLE_COMPTE = 
    	 "create table COMPTE(" +
    	 	"id int, " +
    	 	"num int, " +
    	 	"cash double, " +
    	 	"id_utilisateur int " +
    	 ")"; 
    
    public static String SQL_CREATE_TABLE_ACTION = 
	   	 "create table ACTION(" +
	   	 	"id int, " +
	   	 	"id_titre int, " +
	   	 	"id_compte int" +
	   	 ")"; 
    
    public static String SQL_CREATE_TABLE_UTILISATEUR = 
   	 "create table UTILISATEUR(" +
   	 	"id int, " +
   	 	"nom varchar(40), " +
   	 	"prenom varchar(40), " +
   	 	"login varchar(40), " +
   	 	"mdp varchar(6), " +
   	 	"role int" +
   	 ")";
    
    public static String SQL_CREATE_TABLE_ALARME = 
      	 "create table ALARME(" +
      	 	"id int, " +
      	 	"type int, " +
      	 	"seuil double, " +
      	 	"id_compte int" +
      	 ")";
    
    public static String SQL_CREATE_TABLE_TITRE = 
     	 "create table TITRE(" +
     	 	"code int, " +
     	 	"libelle varchar(40), " +
     	 	"date_intro date, " +
     	 	"cours_intro double, " +
     	 	"cours double" +
     	 ")";
	
    public static String SQL_CREATE_TABLE_HISTORIQUE = 
    	 "create table HISTORIQUE(" +
    	 	"id int, " +
    	 	"valeur double, " +
    	 	"date_histo time, " +
    	 	"id_titre int" +
    	 ")";
    
    public static String SQL_INSERT_ADMIN = 
   	 "insert into UTILISATEUR values (1,'admin','admin','admin','admin',0)";
    
    public static void drop () {
    	try {
			Class.forName(InitDatabase.driver).newInstance();
			 Properties props = new Properties();
			 props.put("user", "admin");
			 props.put("password", "admin");
			 Connection conn = DriverManager.getConnection(InitDatabase.protocol +
					 InitDatabase.databaseName + ";create=true", props);
			 Statement s = BourseDAO.getInstance().getConnection().createStatement();
			 s.executeUpdate("DROP TABLE COMPTE");
			 s.executeUpdate("DROP TABLE UTILISATEUR");
			 s.executeUpdate("DROP TABLE ACTION");
			 s.executeUpdate("DROP TABLE TITRE");
			 s.executeUpdate("DROP TABLE HISTORIQUE");
			 s.executeUpdate("DROP TABLE ALARME");
			 conn.commit();
			 System.out.println("DROP OK");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
    }
    
	public static void createDatabase() {
		/* parse the arguments to determine which framework is desired*/
        try
        {
            /*
               The driver is installed by loading its class.
               In an embedded environment, this will start up Derby, since it is not already running.
             */
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver.");
            
            Connection conn = null;
            Properties props = new Properties();
            props.put("user", "admin");
            props.put("password", "admin");

            /*
               The connection specifies create=true to cause
               the database to be created. To remove the database,
               remove the directory derbyDB and its contents.
               The directory derbyDB will be created under
               the directory that the system property
               derby.system.home points to, or the current
               directory if derby.system.home is not set.
             */
            conn = DriverManager.getConnection(protocol +
            		databaseName + ";create=true", props);

            System.out.println("Connected to and created database " + databaseName);

            conn.setAutoCommit(false);

            /*
               Creating a statement lets us issue commands against
               the connection.
             */
            Statement s = conn.createStatement();

            /*
               We create the tables
             */
           for (Field f : InitDatabase.class.getFields()){
        	   if (f.get(null) instanceof String){
        		   if (f.getName().startsWith("SQL_")) {
        			   String stmp = (String) f.get(null) ; 
        			   s.execute(stmp);
        			   System.out.printf("req executee : %s%n", stmp) ;
        		   }
        	   }
           }
           
           /*
            * We create the first users
            */
//            s.execute("insert into derbyDB values (1956,'Webster St.')");
//            System.out.println("Inserted 1956 Webster");
//            s.execute("insert into derbyDB values (1910,'Union St.')");
//            System.out.println("Inserted 1910 Union");
//            s.execute(
//                "update derbyDB set num=180, addr='Grand Ave.' where num=1956");
//            System.out.println("Updated 1956 Webster to 180 Grand");
//
//            s.execute(
//                "update derbyDB set num=300, addr='Lakeshore Ave.' where num=180");
//            System.out.println("Updated 180 Grand to 300 Lakeshore");

           

            
          
            s.close();
           

            /*
               We end the transaction and the connection.
             */
            conn.commit();
            conn.close();
           
        }
        catch (Throwable e)
        {
            System.out.println("exception thrown:");          
            if (e instanceof SQLException)
            {
                System.err.println((SQLException) e);
            }
            else
            {
                e.printStackTrace();
            }
        }

        System.out.println("SimpleApp finished");
	}
	
	public static void main (String [] args) {
		InitDatabase.drop () ;
		InitDatabase.createDatabase();
	}
}
