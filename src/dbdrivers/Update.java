package dbdrivers;

import java.sql.Statement;

public class Update {
    public String boxString = "";

    public void updateRow(String[] params, String[] updates, int flag) {
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
        String updateStatement = "UPDATE "+tables[flag-1]+" SET";
        String condStatement = " WHERE";
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (int i = 0; i < updates.length; i++) {
            if (updates[i] != "" && i != updates.length - 1) {
                updateStatement += " " + updates[i] + ",";
            } else {
                if (params[i] != "") {
                    updateStatement += " "+updates[i];
                }
            }
        }
        if (updateStatement.endsWith(",")) {
            updateStatement = updateStatement.substring(0, updateStatement.length()-1);
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] != "" && i != params.length - 1) {
                condStatement += " " + params[i] + " AND";
            } else {
                if (params[i] != "") {
                    condStatement += " "+params[i];
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
        } catch (Exception e) {
            System.out.println("Failed to execute: "+updateStatement+"\n"+e);
            boxString += ("Failed to execute: "+updateStatement);
        }
    }
}
