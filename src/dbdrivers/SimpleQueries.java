package dbdrivers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQueries {
    public String boxString = "";
    String[] queries;
    {
        queries = new String[]{
                ("SELECT room_number, 'Performs operations of specialty ', speciality\n" +
                        "   FROM operating_room\n" +
                        "   ORDER BY room_number ASC\n"),
                ("SELECT 'patient with ID ', patient_id, 'Has a total bill of $', total_cost\n" +
                        "   FROM medical_bill\n" +
                        "   ORDER BY total_cost DESC\n"),
                ("SELECT patient_id, ' Has OHIP number ', ohip_number\n" +
                        "   FROM patient\n" +
                        "   WHERE first_name = 'Alon'\n"),
                ("SELECT 'patient ', patient_id, 'Is treated by ',physician_id,' and ',nurse_id\n" +
                        "   FROM treated_by\n" +
                        "   ORDER BY patient_id ASC\n"),
                ("SELECT physician_id, 'writes prescription and is filled by the pharmacist with ID ', pharmacist_id\n" +
                        "   FROM writes_prescription\n" +
                        "   ORDER BY physician_id ASC\n"),
                ("SELECT admin_id, 'has last name', last_name\n" +
                        "   FROM administrator\n" +
                        "   WHERE first_name = 'Joe'"),
                ("SELECT medical_id, first_name, last_name\n" +
                        "   FROM physician\n" +
                        "   WHERE specialty = 'Cardiology'\n" +
                        "   ORDER BY medical_id ASC\n"),
                ("SELECT *\n" +
                        "   FROM nurse\n" +
                        "   ORDER BY last_name ASC\n"),
                ("SELECT prescriptions_filled\n" +
                        "   FROM pharmacist\n" +
                        "   WHERE pharmacist_id = 21\n"),
                ("SELECT * FROM patient_room\n"),
                ("SELECT 'This patient has emergency contacts: ',\n" +
                        "      first_name,last_name FROM emergency_contact WHERE patient_id=902\n"),
                ("SELECT 'The emergency contact with this phone number is: ',\n" +
                        "      first_name,last_name FROM emergency_contact WHERE phone_number=4156479051\n"),
                ("SELECT 'OperationId: ', operation_id, 'PatientId :', patient_id, 'Operation type: ', operation_type,\n" +
                        "      'Operating room: ', operating_room FROM surgery\n"),
                ("SELECT 'This patient has had surgeries: ', operation_type\n" +
                        "       FROM surgery WHERE patient_id=901\n"),
                ("SELECT 'Nurses participating in this operation: ', nurse_id\n" +
                        "      FROM assists WHERE operation_id=500\n"),
                ("SELECT 'Nurse has participated in these operations: ', operation_id\n" +
                        "      FROM assists WHERE nurse_id=10\n"),
                ("SELECT 'Physicians participating in this operation: ', physician_id\n" +
                        "      FROM performs WHERE operation_id=500\n"),
                ("SELECT 'Physician has participated in these operations: ', operation_id\n" +
                        "      FROM performs WHERE physician_id=1\n"),
                ("SELECT 'Lead physician ID: ', lead_physician\n" +
                        "       FROM performs WHERE operation_id=500\n"),
                ("SELECT * FROM located_in WHERE hospital_id=0\n"),
                ("SELECT 'Medical staff at this hospital: ',medical_id FROM located_in\n"),
                ("SELECT patient_id FROM located_in WHERE patient_id=901\n"),
                ("SELECT name FROM hospital WHERE hospital_id = 0")
        };
    }

    public void queries() throws SQLException {
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (String command : queries) {
            try {
                ResultSet rs = conn.executeQuery(command);
                System.out.println("Executed: "+command);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnNumber; i++) {
                        String columnValue = rs.getString(i);
                        boxString += columnValue+" ";
                    }
                    boxString += "\n";
                }
            } catch (Exception e) {
                System.out.println("Failed to execute: "+command+"\n"+e);
                boxString += "Failed to execute: "+command+"\n"+e;
            }
        }
    }
}