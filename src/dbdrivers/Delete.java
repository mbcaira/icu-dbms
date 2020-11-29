package dbdrivers;

import java.sql.Statement;

public class Delete {
    public String boxString = "";

    public void deleteRow(String[] primKeys, int flag) {
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
                "PHYSICIAN",
                "SURGERY",
                "TREATED_BY",
                "WRITES_PRESCRIPTION"
        };
        String deleteStatement = "DELETE FROM "+tables[flag-1]+" WHERE";
        String out = "";
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (int i = 0; i < primKeys.length; i++) {
            if (primKeys[i] != null && i != primKeys.length - 1) {
                deleteStatement += " " + primKeys[i] + " AND";
            } else {
                if (primKeys[i] != null) {
                    deleteStatement += " "+primKeys[i];
                }
            }
        }
        if (deleteStatement.substring(deleteStatement.length()-3).equals("AND")) {
            deleteStatement = deleteStatement.substring(0, deleteStatement.length()-3);
        }
        try {
            assert conn != null;
            conn.executeUpdate(deleteStatement);
            System.out.println("Executed: "+deleteStatement);
            boxString += ("Executed: "+deleteStatement+"\n");
            out += "Executed: "+deleteStatement+"\n";
        } catch (Exception e) {
            System.out.println("Failed to execute: "+deleteStatement+"\n"+e);
            boxString += ("Failed to execute: "+deleteStatement);
            out += "Failed to execute: "+deleteStatement+"\n";
        }
    }
}
