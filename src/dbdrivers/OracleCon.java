package dbdrivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Connects to the OracleDB and provides disconnect functionality.
 */
public class OracleCon {
    private static final Statement conn = connectDB();
    private static final String user = "USERNAME";
    private static final String pass = "PASSWORD";
    public static String boxString = "";

    /**
     * Connects to the database given the specified user information and returns a connection Statement
     * @return Connection statement that can be used to execute queries and generate ResultSets
     */
    public static Statement connectDB() {
        boxString = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl", user, pass);
            System.out.println("Connected to Oracle DB");
            boxString += "Connected to Oracle DB\n";
            return con.createStatement();
        } catch (Exception e) {
            System.out.println("Error, could not connect to Oracle DB, please check credentials and connection.");
            boxString += "Error, could not connect to Oracle DB, please check credentials and connection.\n";
            return null;
        }
    }

    /**
     * Disconnects/closes the OracleDB connection.
     */
    public static void close() {
        try {
            assert conn != null;
            conn.close();
            System.out.print("OracleDB connection closed.");
            boxString += "Disconnected from OracleDB.\n";
        } catch (Exception e) {
            System.out.println("Error disconnecting, connection most likely did not exist.");
            boxString += "Error disconnecting, test connection to see if a connection exists.\n";
        }
    }
}
