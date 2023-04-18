import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Singleton class for connection to database
 */
public class ConnectTo_Database {
	
	private static Connection con = null;
	  
    static
    {	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         con = DriverManager.getConnection("jdbc:sqlite:test.db");
	      } 
	      catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }		
    }
    
    public static Connection getConnection()
    {
        return con;
    }

}
