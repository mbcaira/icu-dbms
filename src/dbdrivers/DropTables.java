package dbdrivers;

import java.sql.Statement;

public class DropTables {
    Statement conn = OracleCon.conn;
    String[] dropCommands = {
            "DROP TABLE LOCATED_IN",
            "DROP TABLE hospital",
            "DROP TABLE assists",
            "DROP TABLE performs",
            "DROP TABLE surgery",
            "DROP TABLE emergency_contact",
            "DROP TABLE treated_by",
            "DROP TABLE medical_bill",
            "DROP TABLE patient",
            "DROP TABLE writes_prescription",
            "DROP TABLE operating_room",
            "DROP TABLE patient_room",
            "DROP TABLE pharmacist",
            "DROP TABLE nurse",
            "DROP TABLE physician",
            "DROP TABLE administrator",
            "DROP VIEW scrub_nurses",
            "DROP VIEW high_cost_surgeries",
            "DROP VIEW new_pharmacists"
    };
    public String drop(){
        String out = "";
        Statement conn = OracleCon.connectDB();
        for (String command : dropCommands) {
            try {
                conn.execute(command);
                System.out.println("Executed: "+command);
                out += "Executed: "+command+"\n";
            } catch (Exception e) {
                System.out.println("Failed to execute: "+command);
                out += "Failed to execute: "+command+"\n";
            }
        }
        return out+"\nTables dropped.";
    }

    public static void main(String[] args) {
        DropTables t = new DropTables();
        t.drop();
    }
}
