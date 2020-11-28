package dbdrivers;

import java.sql.Statement;

public class DropTables {
    public String boxString = "";
    String[] dropCommands = {
            "DROP TABLE located_in",
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
        boxString = "";
        Statement conn = OracleCon.connectDB();
        for (String command : dropCommands) {
            try {
                conn.execute(command);
                System.out.println("Executed: "+command);
                boxString += ("Executed: "+command+"\n");
                out += "Executed: "+command+"\n";
            } catch (Exception e) {
                System.out.println("Failed to execute: "+command);
                boxString += ("Failed to exectute: "+command+"\n");
                out += "Failed to execute: "+command+"\n";
            }
        }
        return out;
    }
}
