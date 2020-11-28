package dbdrivers;

import java.sql.Statement;

public class CreateTables {
    public String boxString = "";
    String[] createCommands = {
            ("CREATE TABLE administrator(\n" +
                    "    admin_id INT PRIMARY KEY,\n" +
                    "    first_name VARCHAR2(20) NOT NULL,\n" +
                    "    last_name VARCHAR2(20) NOT NULL\n" +
                    ")"),
            ("CREATE TABLE physician(\n" +
                    "    medical_id INT PRIMARY KEY,\n" +
                    "    first_name VARCHAR2(20) NOT NULL,\n" +
                    "    last_name VARCHAR2(20) NOT NULL,\n" +
                    "    specialty VARCHAR2(20)\n" +
                    ")"),
            ("CREATE TABLE nurse(\n" +
                    "    medical_id INT PRIMARY KEY,\n" +
                    "    first_name VARCHAR2(20) NOT NULL,\n" +
                    "    last_name VARCHAR2(20) NOT NULL,\n" +
                    "    specialization VARCHAR2(20)\n" +
                    ")"),
            ("CREATE TABLE pharmacist(\n" +
                    "    pharmacist_id INT PRIMARY KEY,\n" +
                    "    first_name VARCHAR2(20) NOT NULL,\n" +
                    "    last_name VARCHAR2(20) NOT NULL,\n" +
                    "    prescriptions_filled VARCHAR2(30)\n" +
                    ")"),
            ("CREATE TABLE patient_room(\n" +
                    "    room_number INT PRIMARY KEY\n" +
                    ")"),
            ("CREATE TABLE operating_room(\n" +
                    "    room_number INT PRIMARY KEY,\n" +
                    "    speciality VARCHAR2 (30)\n" +
                    ")"),
            ("CREATE TABLE writes_prescription(\n" +
                    "    physician_id INT NOT NULL,\n" +
                    "    pharmacist_id INT NOT NULL,\n" +
                    "    PRIMARY KEY(physician_id,pharmacist_id),\n" +
                    "    FOREIGN KEY(physician_id) REFERENCES physician(medical_id) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY(pharmacist_id) REFERENCES pharmacist(pharmacist_id) ON DELETE SET NULL\n" +
                    ")"),
            ("CREATE TABLE patient (\n" +
                    "    patient_id INT PRIMARY KEY,\n" +
                    "    first_name VARCHAR2(20) NOT NULL,\n" +
                    "    last_name VARCHAR2(20) NOT NULL,\n" +
                    "    ohip_number INT,\n" +
                    "    phone_number INT,\n" +
                    "    room_number INT NOT NULL UNIQUE,\n" +
                    "    physician_id INT NOT NULL,\n" +
                    "    pharmacist_id INT NOT NULL,\n" +
                    "    nurse_id INT NOT NULL,\n" +
                    "    admin_id INT NOT NULL,\n" +
                    "    FOREIGN KEY(physician_id) REFERENCES physician(medical_id) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY(nurse_id) REFERENCES nurse(medical_id) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY(admin_id) REFERENCES administrator(admin_id) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY(pharmacist_id) REFERENCES pharmacist(pharmacist_id) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY(room_number) REFERENCES patient(room_number) ON DELETE SET NULL\n" +
                    ")"),
            ("CREATE TABLE medical_bill(\n" +
                    "    patient_id INT NOT NULL UNIQUE,\n" +
                    "    bill_id INT NOT NULL UNIQUE,\n" +
                    "    total_cost INT,\n" +
                    "    PRIMARY KEY(patient_id,bill_id),\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE\n" +
                    ")"),
            ("CREATE TABLE treated_by(\n" +
                    "    patient_id INT NOT NULL,\n" +
                    "    physician_id INT NOT NULL,\n" +
                    "    nurse_id INT NOT NULL,\n" +
                    "    PRIMARY KEY(patient_id,physician_id,nurse_id),\n" +
                    "    FOREIGN KEY(physician_id) REFERENCES physician(medical_id) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY(nurse_id) REFERENCES nurse(medical_id) ON DELETE SET NULL\n" +
                    ")"),
            ("CREATE TABLE emergency_contact(\n" +
                    "    patient_id INT NOT NULL,\n" +
                    "    phone_number INT NOT NULL,\n" +
                    "    first_name VARCHAR2(20) NOT NULL,\n" +
                    "    last_name VARCHAR2(20) NOT NULL,\n" +
                    "    address VARCHAR2(20),\n" +
                    "    relation_to_patient VARCHAR(20),\n" +
                    "    PRIMARY KEY(patient_id,phone_number),\n" +
                    "    FOREIGN KEY(patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE\n" +
                    ")"),
            ("CREATE TABLE surgery(\n" +
                    "    patient_id INT NOT NULL,\n" +
                    "    operation_id INT NOT NULL,\n" +
                    "    operation_type VARCHAR2(20) NOT NULL,\n" +
                    "    operating_room INT,\n" +
                    "    medical_bill INT,\n" +
                    "    PRIMARY KEY(patient_id,operation_id),\n" +
                    "    FOREIGN KEY(operating_room) REFERENCES operating_room(room_number) ON DELETE SET NULL,\n" +
                    "    FOREIGN KEY (patient_id,medical_bill) REFERENCES medical_bill(patient_id,bill_id),\n" +
                    "    FOREIGN KEY(patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE\n" +
                    ")"),
            ("CREATE TABLE performs(\n" +
                    "    physician_id INT NOT NULL,\n" +
                    "    lead_physician INT NOT NULL,\n" +
                    "    operation_id INT NOT NULL,\n" +
                    "    PRIMARY KEY(physician_id,operation_id),\n" +
                    "    FOREIGN KEY(physician_id) REFERENCES physician(medical_id) ON DELETE SET NULL\n" +
                    ")"),
            ("CREATE TABLE assists(\n" +
                    "    nurse_id INT NOT NULL,\n" +
                    "    operation_id INT NOT NULL,\n" +
                    "    PRIMARY KEY(nurse_id,operation_id),\n" +
                    "    FOREIGN KEY(nurse_id) REFERENCES nurse(medical_id) ON DELETE SET NULL\n" +
                    ")"),
            ("CREATE TABLE hospital(\n" +
                    "    hospital_id INT PRIMARY KEY,\n" +
                    "    address VARCHAR2 (20) NOT NULL,\n" +
                    "    name VARCHAR2 (40) NOT NULL\n" +
                    ")"),
            ("CREATE TABLE located_in(\n" +
                    "    hospital_id     INT NOT NULL UNIQUE,\n" +
                    "    medical_id      INT NOT NULL UNIQUE,\n" +
                    "    patient_room    INT NOT NULL UNIQUE,\n" +
                    "    admin_id        INT NOT NULL UNIQUE,\n" +
                    "    patient_id      INT NOT NULL UNIQUE,\n" +
                    "    pharmacist_id   INT NOT NULL UNIQUE,\n" +
                    "    PRIMARY KEY (hospital_id, medical_id, admin_id, patient_id, pharmacist_id, patient_room),\n" +
                    "    FOREIGN KEY (hospital_id) REFERENCES hospital(hospital_id),\n" +
                    "    FOREIGN KEY (medical_id) REFERENCES physician(medical_id),\n" +
                    "    FOREIGN KEY (patient_room) REFERENCES patient_room(room_number),\n" +
                    "    FOREIGN KEY (admin_id) REFERENCES administrator(admin_id),\n" +
                    "    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),\n" +
                    "    FOREIGN KEY (pharmacist_id) REFERENCES pharmacist(pharmacist_id)\n" +
                    ")")
    };
    public String create(){
        String out = "";
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (String command : createCommands) {
            try {
                conn.execute(command);
                System.out.println("Executed: "+command);
                boxString += ("Executed: "+command);
                out += "Executed: "+command+"\n";
            } catch (Exception e) {
                System.out.println("Failed to execute: "+command);
                boxString += ("Failed to execute: "+command);
                out += "Failed to execute: "+command+"\n";
            }
        }
        return out;
    }
}