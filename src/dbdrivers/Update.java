package dbdrivers;

import java.sql.Statement;

/**
 * Runs update queries on a desired table with desired updated information.
 */
public class Update {
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
     * Updates a row of a table in the database given the parameters and table index.
     * @param params Array of strings search conditions of the query (i.e. "name='xyz'")
     * @param updates Array of string containing the updated information fields of the query. (i.e. "name='xyz")
     * @param flag Integer specifying which index of the tables array to use, correlating to the desired table
     *             to perform operations on.
     */
    public void updateRow(String[] params, String[] updates, int flag) {
        // Connect to OracleDB
        Statement conn = OracleCon.connectDB();
        String updateStatement = "UPDATE "+tables[flag-1]+" SET";
        String condStatement = " WHERE";
        boxString = "";
        // Build updateStatement, non-inclusive of empty strings
        for (int i = 0; i < updates.length; i++) {
            if (!updates[i].equals("") && i != updates.length - 1) {
                updateStatement += " " + updates[i] + ",";
            } else {
                if (!params[i].equals("")) {
                    updateStatement += " "+updates[i];
                }
            }
        }
        // Remove extra commas from updateStatement
        if (updateStatement.endsWith(",")) {
            updateStatement = updateStatement.substring(0, updateStatement.length()-1);
        }
        // Build conditional statement from params array, non-inclusive of empty strings
        for (int i = 0; i < params.length; i++) {
            if (!params[i].equals("") && i != params.length - 1) {
                condStatement += " " + params[i] + " AND";
            } else {
                if (!params[i].equals("")) {
                    condStatement += " "+params[i];
                }
            }
        }
        // Remove additional "AND"s for statement to work properly.
        if (condStatement.endsWith("AND")) {
            condStatement = condStatement.substring(0, condStatement.length()-3);
        }
        // Concatenate updateStatement with condStatement to complete the string query
        updateStatement += condStatement;
        // Execute the query and display output to user.
        try {
            assert conn != null;
            conn.executeUpdate(updateStatement);
            System.out.println("Executed: "+updateStatement);
            boxString += ("Executed: "+updateStatement+"\n");
        } catch (Exception e) {
            System.out.println("Failed to execute: "+updateStatement+"\n"+e);
            boxString += ("Failed to execute: "+updateStatement);
        }
    }
}
