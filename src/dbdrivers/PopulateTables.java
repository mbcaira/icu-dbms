package dbdrivers;

import java.sql.Statement;

public class PopulateTables {
    public String boxString = "";
    String[] populateCommands = {
            ("INSERT INTO administrator VALUES(51,'Joe','Swanson')\n"),
            ("INSERT INTO administrator VALUES(52,'Donna','Paulson')\n"),
            ("INSERT INTO physician VALUES(1,'Jorge','Lopez','Cardiology')\n"),
            ("INSERT INTO physician VALUES(2,'Hector','Salermo','Orthopedics')\n"),
            ("INSERT INTO physician VALUES(3,'Soosan','Beheshti','Pathology')\n"),
            ("INSERT INTO physician VALUES(4,'Derek','Shepherd','Neurosurgey')\n"),
            ("INSERT INTO nurse VALUES(10,'Bokhee','Ling','Scrub Nurse')\n"),
            ("INSERT INTO nurse VALUES(11,'Olivia','Harper','ER Nurse')\n"),
            ("INSERT INTO nurse VALUES(12,'Eli','Lloyd','General Nurse')\n"),
            ("INSERT INTO pharmacist VALUES(20,'Michael','Canaran','240')\n"),
            ("INSERT INTO pharmacist VALUES(21,'Lloyd','Banks','223')\n"),
            ("INSERT INTO pharmacist VALUES(22,'Bill','Williams','87')\n"),
            ("INSERT INTO pharmacist VALUES(23,'Bob','Roberts','53')\n"),
            ("INSERT INTO writes_prescription VALUES(3,21)\n"),
            ("INSERT INTO writes_prescription VALUES(3,23)\n"),
            ("INSERT INTO writes_prescription VALUES(2,21)\n"),
            ("INSERT INTO patient_room VALUES(45)\n"),
            ("INSERT INTO patient_room VALUES(49)\n"),
            ("INSERT INTO patient_room VALUES(53)\n"),
            ("INSERT INTO patient_room VALUES(56)\n"),
            ("INSERT INTO patient_room VALUES(58)\n"),
            ("INSERT INTO operating_room VALUES(37,'Cardiac Surgery')\n"),
            ("INSERT INTO operating_room VALUES(38,'Neurosurgery')\n"),
            ("INSERT INTO operating_room VALUES(39,'Radiology')\n"),
            ("INSERT INTO operating_room VALUES(40,'Orthopedics')\n"),
            ("INSERT INTO patient VALUES(901,'Denny','Duquette','5443217669','4164432187',45,1,20,11,51)\n"),
            ("INSERT INTO patient VALUES(902,'Alon','Mez','544422749','4162217855',49,3,21,12,51)\n"),
            ("INSERT INTO patient VALUES(903,'Bryan','Zhen','543464949','4163252231',53,2,22,12,52)\n"),
            ("INSERT INTO patient VALUES(904,'Robert','Thompson','542174663','4162072122',56,2,23,11,52)\n"),
            ("INSERT INTO medical_bill VALUES(901,300,30000)\n"),
            ("INSERT INTO medical_bill VALUES(902,301,2000)\n"),
            ("INSERT INTO medical_bill VALUES(903,302,12300)\n"),
            ("INSERT INTO medical_bill VALUES(904,303,17600)\n"),
            ("INSERT INTO treated_by VALUES(901,1,11)\n"),
            ("INSERT INTO treated_by VALUES(902,1,12)\n"),
            ("INSERT INTO treated_by VALUES(903,3,12)\n"),
            ("INSERT INTO treated_by VALUES(904,2,11)\n"),
            ("INSERT INTO emergency_contact VALUES(901,4153342915,'Isobel','Stephens','23 Queen st','Wife')\n"),
            ("INSERT INTO emergency_contact VALUES(902,4156479051,'Alex','Mez','437 Yonge st','Father')\n"),
            ("INSERT INTO emergency_contact VALUES(903,6472915448,'Lydia','Zhen','392 Yonge st','Mother')\n"),
            ("INSERT INTO emergency_contact VALUES(904,9053356112,'Richard','Thompson','903 Bramalea rd','Father')\n"),
            ("INSERT INTO surgery VALUES(901,500,'Heart Surgery',37,300)\n"),
            ("INSERT INTO surgery VALUES(903,501,'Brain Surgery',38,302)\n"),
            ("INSERT INTO surgery VALUES(904,502,'Knee Surgery',40,303)\n"),
            ("INSERT INTO performs VALUES(1,2,500)\n"),
            ("INSERT INTO performs VALUES(4,4,501)\n"),
            ("INSERT INTO performs VALUES(4,2,502)\n"),
            ("INSERT INTO assists VALUES(10,500)\n"),
            ("INSERT INTO assists VALUES(10,501)\n"),
            ("INSERT INTO assists VALUES(10,502)\n"),
            ("INSERT INTO hospital VALUES(0,'33 University','Sick Kids Hospital')\n"),
            ("INSERT INTO located_in VALUES(0,3,56,51,901,20)\n")
    };
    public void populate(){
        Statement conn = OracleCon.connectDB();
        boxString = "";
        for (String command : populateCommands) {
            try {
                assert conn != null;
                conn.execute(command);
                System.out.println("Executed: "+command);
                boxString += ("Executed: "+command+"\n");
            } catch (Exception e) {
                System.out.println("Failed to execute: "+command);
                boxString += ("Failed to exectute: "+command+"\n");
            }
        }
    }
}