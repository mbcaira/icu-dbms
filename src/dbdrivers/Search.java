package dbdrivers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Searches for a data entry in a table on the OracleDB given parameters to filter with.
 */
public class Search {
    public String boxString = "";
    // Array of strings with each table in the DB at each index.
    String[] tables = {
            "ADMINISTRATOR",
            "ASSISTS",
            "EMERGENCY_CONTACT",
            "HOSPITAL",
            "LOCATED_IN",
            "MEDICAL_BILL",
            "NURSE",
            "OPERATING_ROOM",
            "PATIENT",
            "PATIENT_ROOM",
            "PERFORMS",
            "PHARMACIST",
            "PHYSICIAN",
            "SURGERY",
            "TREATED_BY",
            "WRITES_PRESCRIPTION"
    };

    /**
     * Searches for an entry in a table in the database given the table index and search parameters
     * @param params Array of strings search conditions of the query (i.e. "name='xyz'")
     * @param flag  Integer specifying which index of the tables array to use, correlating to the desired table
     *              to perform operations on.
     */
    public void searchEntry(String[] params, int flag) {
        // Connect to OracleDB
        Statement conn = OracleCon.connectDB();
        boxString = "";
        // Start building the search query with "WHERE" at the end to accept search parameters.
        String searchString = "SELECT * FROM "+tables[flag-1]+" WHERE";
        for (int i = 0; i < params.length; i++) {
            if (!params[i].equals("") && i != params.length - 1) {
                searchString += " " + params[i] + " AND";
            } else {
                if (!params[i].equals("")) {
                    searchString += " "+params[i];
                }
            }
        }
        // Remove extra "AND"s from the searchString to execute properly.
        if (searchString.endsWith("AND")) {
            searchString = searchString.substring(0, searchString.length()-3);
        }
        // Execute search query and display the result to the user.
        try {
            assert conn != null;
            ResultSet rs = conn.executeQuery(searchString);
            System.out.println("Executed: "+searchString);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnNumber; i++) {
                    String columnValue = rs.getString(i);
                    boxString += columnValue+" ";
                }
                boxString += "\n";
            }
            System.out.println(boxString);
        } catch (Exception e) {
            System.out.println("Failed to execute: "+searchString+"\n"+e);
            boxString += ("Failed to execute: "+searchString);
        }
    }
}