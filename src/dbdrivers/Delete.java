package dbdrivers;

import java.sql.Statement;

/**
 * Deletes specific entries from a specified table given proper conditions.
 */
public class Delete {
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
     * Deletes a row from a table in the database given a table index and delete conditions.
     * @param params Array of strings delete conditions of the query (i.e. "name='xyz'")
     * @param flag  Integer specifying which index of the tables array to use, correlating to the desired table
     *              to perform operations on.
     */
    public void deleteRow(String[] params, int flag) {
        // Begin to build the delete statement.
        String deleteStatement = "DELETE FROM "+tables[flag-1]+" WHERE";
        // Connect to OracleDB
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (int i = 0; i < params.length; i++) {
            // Add the parameters to the deleteStatement if it is not null, along with an "AND" to continue the
            // statement, unless we are at the end of the array.
            if (!params[i].equals("") && i != params.length - 1) {
                deleteStatement += " " + params[i] + " AND";
            } else {
                if (!params[i].equals("")) {
                    deleteStatement += " "+params[i];
                }
            }
        }
        // Catch extra "AND"s and remove them
        if (deleteStatement.endsWith("AND")) {
            deleteStatement = deleteStatement.substring(0, deleteStatement.length()-3);
        }
        // Display operation status to user
        try {
            assert conn != null;
            conn.executeUpdate(deleteStatement);
            System.out.println("Executed: "+deleteStatement);
            boxString += ("Executed: "+deleteStatement+"\n");
        } catch (Exception e) {
            System.out.println("Failed to execute: "+deleteStatement+"\n"+e);
            boxString += ("Failed to execute: "+deleteStatement);
        }
    }
}
