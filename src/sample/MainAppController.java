package sample;

import dbdrivers.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Leandra Budau
 */
public class MainAppController implements Initializable {

    /**
     * Initializes the controller class.
     */

    int currentState;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label label5;
    @FXML Label label6;
    @FXML Label label7;
    @FXML Label label8;
    @FXML Label label9;
    @FXML Label label10;
    @FXML Text text = new Text();
    @FXML Text boxText = new Text();
    @FXML ScrollPane pane;
    @FXML TextField textField1;
    @FXML TextField textField2;
    @FXML TextField textField3;
    @FXML TextField textField4;
    @FXML TextField textField5;
    @FXML TextField textField6;
    @FXML TextField textField7;
    @FXML TextField textField8;
    @FXML TextField textField9;
    @FXML TextField textField10;
    String ongoingOutput = "";
    CreateTables t = new CreateTables();
    DropTables d = new DropTables();
    OracleCon o = new OracleCon();
    SimpleQueries s = new SimpleQueries();
    PopulateTables p = new PopulateTables();
    AdvancedQueries a = new AdvancedQueries();

    @FXML
    void testConnectionButton (ActionEvent event){
        o.connectDB();
        ongoingOutput += o.boxString;
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
    }
    @FXML
    void createTablesButton (ActionEvent event){
        t.create();                                 // Creates the tables
        ongoingOutput += t.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    @FXML
    void populateTablesButton (ActionEvent event){
        p.populate();
        ongoingOutput += p.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
    }
    @FXML
    void runSimpleButton (ActionEvent event) throws SQLException {
        s.queries();                                 // Creates the tables
        ongoingOutput += s.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
    }
    @FXML
    void runAdvancedButton (ActionEvent event) throws SQLException {
        a.advancedQueries();                                 // Creates the tables
        ongoingOutput += a.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
    }
    @FXML
    void dropTablesButton (ActionEvent event){
        d.drop();                                       // Deletes the tables
        ongoingOutput += d.boxString;                   // Sets the ScrollPane to the text
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                            // Auto-scrolls to the bottom
    }
    @FXML
    void addPatientButton (ActionEvent event){
        currentState = 1;
        text.setText("Enter needed information below to add new patient");
        label1.setText("Patient ID:");
        label2.setText("First Name:");
        label3.setText("Last Name:");
        label4.setText("OHIP Number:");
        label5.setText("Phone Number");
        label6.setText("Room Number");
        label7.setText("Physician ID:");
        label8.setText("Nurse ID:");
        label9.setText("Pharmacist ID:");
        label10.setText("Administator ID:");
        pane.setContent(text);
    }
    @FXML
    void addPhysicianButton (ActionEvent event){
        currentState = 2;
        text.setText("Enter needed information below to add new Physician");
        label1.setText("Physician ID:");
        label2.setText("First Name:");
        label3.setText("Last Name:");
        label4.setText("Specialty:");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addNurseButton (ActionEvent event){
        currentState = 3;
        text.setText("Enter needed information below to add new Nurse");
        label1.setText("Physician ID:");
        label2.setText("First Name:");
        label3.setText("Last Name:");
        label4.setText("Specialization:");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addPharmacistButton (ActionEvent event){
        currentState = 4;
        text.setText("Enter needed information below to add new Pharmacist");
        label1.setText("Pharmacist ID:");
        label2.setText("First Name:");
        label3.setText("Last Name:");
        label4.setText("Prescriptions Filled:");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addAdministratorButton (ActionEvent event){
        currentState = 5;
        text.setText("Enter needed information below to add new Administrator");
        label1.setText("Administrator ID:");
        label2.setText("First Name:");
        label3.setText("Last Name:");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addSurgeryButton (ActionEvent event){
        currentState = 6;
        text.setText("Enter needed information below to add new Surgery");
        label1.setText("Patient ID:");
        label2.setText("Operation ID:");
        label3.setText("Operation Type:");
        label4.setText("Operating Room:");
        label5.setText("Medical Bill");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addHospitalButton (ActionEvent event){
        currentState = 7;
        text.setText("Enter needed information below to add new Hospital");
        label1.setText("Hospital ID:");
        label2.setText("Address:");
        label3.setText("Name:");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addPatientRoomButton (ActionEvent event){
        currentState = 8;
        text.setText("Enter needed information below to add new Patient Room");
        label1.setText("Room Number:");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addOperatingRoomButton (ActionEvent event){
        currentState = 9;
        text.setText("Enter needed information below to add new Operating room");
        label1.setText("Room Number:");
        label2.setText("Specialty:");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addMedicalBillButton (ActionEvent event){
        currentState = 10;
        text.setText("Enter needed information below to add new Medical Bill");
        label1.setText("Patient ID:");
        label2.setText("Bill ID:");
        label3.setText("Total Cost:");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void addEmergContactButton (ActionEvent event){
        currentState = 11;
        text.setText("Enter needed information below to add new Emergency Contact");
        label1.setText("Patient ID:");
        label2.setText("Phone Number:");
        label3.setText("First Name:");
        label4.setText("Last Name:");
        label5.setText("Address:");
        label6.setText("Relationship to Patient:");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    @FXML
    void submitDataButton(ActionEvent event){
        switch (currentState) {
            case 1:                 // Adding new patient
                text.setText("Trying to submit patient");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 2:                 // Adding new physician
                text.setText("Trying to submit physician");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 3:                 // Adding new Nurse
                text.setText("Trying to submit nurse");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 4:                 // Adding new Pharmacist
                text.setText("Trying to submit pharmacist");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 5:                 // Adding new Administrator
                text.setText("Trying to submit administrator");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 6:                 // Adding new Surgery
                text.setText("Trying to submit surgery");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 7:                 // Adding new Hospital
                text.setText("Trying to submit hospital");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 8:                 // Adding new Patient Room
                text.setText("Trying to submit patient room");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 9:                 // Adding new Operating Room
                text.setText("Trying to submit operating room");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            case 10:                // Adding new Medical Bill
                text.setText("Trying to submit medical bill");
                pane.setContent(text);
                currentState = 0;
                break;
            case 11:                // Adding new Emergency Contact
                text.setText("Trying to submit emergancy contact");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
            default:
                text.setText("Please choose a table to add to before submitting");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
        }
    }
    @FXML
    void exitApplicationButton (ActionEvent event){
        Platform.exit();
    }
    public void clearTextFields(){
        textField1.clear();
        textField2.clear();
        textField3.clear();
        textField4.clear();
        textField5.clear();
        textField6.clear();
        textField7.clear();
        textField8.clear();
        textField9.clear();
        textField10.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Statement conn = OracleCon.connectDB();
    }


}
