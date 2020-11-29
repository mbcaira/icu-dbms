package dbdrivers;

import java.sql.Statement;

public class Delete {
    public String boxString = "";

    public void deleteRow(String[] params, int flag) {
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

        String deleteStatement = "DELETE FROM "+tables[flag-1]+" WHERE";
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (int i = 0; i < params.length; i++) {
            if (params[i] != "" && i != params.length - 1) {
                deleteStatement += " " + params[i] + " AND";
            } else {
                if (params[i] != "") {
                    deleteStatement += " "+params[i];
                }
            }
        }
        if (deleteStatement.endsWith("AND")) {
            deleteStatement = deleteStatement.substring(0, deleteStatement.length()-3);
        }
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
