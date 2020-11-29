package dbdrivers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Search {
    public String boxString = "";
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

    public void searchEntry(String[] params, int flag) {
        Statement conn = OracleCon.connectDB();
        boxString = "";
        String searchString = "SELECT * FROM "+tables[flag-1]+" WHERE";
        for (int i = 0; i < params.length; i++) {
            if (params[i] != "" && i != params.length - 1) {
                searchString += " " + params[i] + " AND";
            } else {
                if (params[i] != "") {
                    searchString += " "+params[i];
                }
            }
        }
        if (searchString.endsWith("AND")) {
            searchString = searchString.substring(0, searchString.length()-3);
        }
        try {
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