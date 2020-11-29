package dbdrivers;

import java.sql.Statement;

public class Update {
    public String boxString = "";

    public void updateRow(String[] primKeys, String[] updates, int flag) {
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
        String updateStatement = "UPDATE "+tables[flag-1]+" SET";
        String condStatement = " WHERE";
        String out = "";
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (int i = 0; i < primKeys.length; i++) {
            if (primKeys[i] != null && i != primKeys.length - 1) {
                condStatement += " " + primKeys[i] + " AND";
            } else {
                if (primKeys[i] != null) {
                    condStatement += " "+primKeys[i];
                }
            }
        }
        if (condStatement.substring(condStatement.length()-3).equals("AND")) {
            condStatement = condStatement.substring(0, condStatement.length()-3);
        }
        updateStatement += condStatement;
        try {
            assert conn != null;
            conn.executeUpdate(updateStatement);
            System.out.println("Executed: "+updateStatement);
            boxString += ("Executed: "+updateStatement+"\n");
            out += "Executed: "+updateStatement+"\n";
        } catch (Exception e) {
            System.out.println("Failed to execute: "+updateStatement+"\n"+e);
            boxString += ("Failed to execute: "+updateStatement);
            out += "Failed to execute: "+updateStatement+"\n";
        }
    }
}
