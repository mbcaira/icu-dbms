package dbdrivers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
/**
 * Runs advanced SQL queries on the OracleDB.
 */
public class AdvancedQueries {
    public String boxString = "";
    // Store queries as an array of string to loop through.
    String[] queries;
    {
        queries = new String[]{
                ("SELECT physician.first_name, patient.first_name\n" +
                        "FROM patient\n" +
                        "INNER JOIN physician ON patient.physician_id = physician.medical_id\n"),
                ("SELECT patient.first_name, emergency_contact.phone_number, medical_bill.total_cost\n" +
                        "FROM patient\n" +
                        "INNER JOIN emergency_contact ON patient.patient_id = emergency_contact.patient_id\n" +
                        "INNER JOIN medical_bill ON patient.patient_id = medical_bill.patient_id\n"),
                ("SELECT patient.first_name, surgery.operation_type, surgery.operating_room\n" +
                        "FROM surgery\n" +
                        "LEFT JOIN patient ON patient.patient_id = surgery.patient_id"),
                ("CREATE VIEW scrub_nurses AS\n" +
                        "SELECT first_name, last_name\n" +
                        "FROM nurse\n" +
                        "WHERE specialization = 'Scrub Nurse'\n"),
                ("CREATE VIEW high_cost_surgeries AS\n" +
                        "SELECT operation_type, medical_bill\n" +
                        "FROM surgery\n" +
                        "WHERE medical_bill>200\n"),
                ("CREATE VIEW new_pharmacists AS\n" +
                        "SELECT first_name, last_name\n" +
                        "FROM pharmacist\n" +
                        "WHERE prescriptions_filled<100\n"),
                ("SELECT first_name\n" +
                        "FROM patient\n" +
                        "WHERE EXISTS\n" +
                        "(SELECT surgery.operation_type\n" +
                        "FROM surgery\n" +
                        "WHERE surgery.patient_id = patient.patient_id AND surgery.operation_type = 'Heart Surgery')\n"),
                ("SELECT first_name, last_name FROM physician\n" +
                        "UNION ALL\n" +
                        "SELECT first_name, last_name FROM nurse"),
                ("SELECT physician_id, COUNT(DISTINCT patient_id) AS Number_of_Patients FROM TREATED_BY GROUP BY physician_id\n"),
                ("SELECT patient_id, COUNT(DISTINCT operation_id) AS Number_of_Operations FROM SURGERY GROUP BY patient_id\n"),
                ("SELECT patient_id, COUNT(*) AS Bills_over_10k FROM MEDICAL_BILL WHERE total_cost > 10000 GROUP BY patient_id\n"),
                ("SELECT first_name, last_name, AVG(prescriptions_filled) FROM pharmacist GROUP BY first_name, last_name HAVING AVG(prescriptions_filled) > (SELECT AVG(prescriptions_filled) FROM pharmacist)\n")
        };
    }

    /**
     * Reads queries from the array of strings and runs a for loop to execute each of them after connecting to the
     * database. Sends the results of the queries to boxString to be displayed.
     */
    public void advancedQueries() {
        // Connect to OracleDB
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (String command : queries) {
            try {
                assert conn != null;
                ResultSet rs = conn.executeQuery(command);
                System.out.println("Executed: "+command);
                boxString += ("Executed: " + command); // Show which command was executed to the user
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnNumber = rsmd.getColumnCount();
                while (rs.next()) {    //Loop through result set and print out each string
                    for (int i = 1; i <= columnNumber; i++) {
                        String columnValue = rs.getString(i);
                        boxString += columnValue+" ";
                    }
                    boxString += "\n";
                }
            } catch (Exception e) {
                System.out.println("Failed to execute: "+command+"\n"+e); //Catch all exceptions, inform user.
                boxString += "Failed to execute: "+command+"\n"+e;
            }
        }
        System.out.println(boxString);
    }
}
