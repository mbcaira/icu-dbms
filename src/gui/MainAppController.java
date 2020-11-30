package gui;

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

    // Defining all JavaFX Labels, Buttons, Panes, etc.
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
    // All other definitions
    String ongoingOutput = "";
    int i;
    int currentState;
    String[] textInputs = {null, null, null, null, null, null, null, null, null, null};
    String[] updatedArray = {null, null, null, null, null, null, null, null, null, null};
    String[] tempArray = {null, null, null, null, null, null, null, null, null, null};
    CreateTables t = new CreateTables();
    DropTables d = new DropTables();
    OracleCon o = new OracleCon();
    SimpleQueries s = new SimpleQueries();
    PopulateTables p = new PopulateTables();
    AdvancedQueries a = new AdvancedQueries();
    Delete e = new Delete();
    Update u = new Update();
    Search r = new Search();

    // Test the connection to oracle
    @FXML
    void testConnectionButton (ActionEvent event){
        o.connectDB();                              // Tests the connection
        ongoingOutput += o.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    // Creates the Tables
    @FXML
    void createTablesButton (ActionEvent event){
        t.create();                                 // Creates the tables
        ongoingOutput += t.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    // Populates the table with predetermined data
    @FXML
    void populateTablesButton (ActionEvent event){
        p.populate();                               // Populates the tables with data
        ongoingOutput += p.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    // Preforms simple queries on the database
    @FXML
    void runSimpleButton (ActionEvent event) throws SQLException {
        s.queries();                                 // Queries the tables
        ongoingOutput += s.boxString;                // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    // Preforms advanced queries on the database
    @FXML
    void runAdvancedButton (ActionEvent event) throws SQLException {
        a.advancedQueries();                        // Queries the tables
        ongoingOutput += a.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    // Drops the tables
    @FXML
    void dropTablesButton (ActionEvent event){
        d.drop();                                   // Deletes the tables
        ongoingOutput += d.boxString;               // Sets the ScrollPane to the text
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);                        // Auto-scrolls to the bottom
    }
    // controls what happens when the Administrator button is clicked
    @FXML
    void administratorButton (ActionEvent event){
        currentState = 1;
        text.setText("To DELETE an Administrator: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing administrator: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Administrator: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Assists button is clicked
    @FXML
    void assistsButton (ActionEvent event){
        currentState = 2;
        text.setText("To DELETE an Assists Relationship: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button  \nTo UPDATE an existing Assists Relationship: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Assists Relationship: enter the data constraints you would like to search, then click the search button");
        label1.setText("Nurse ID:");
        label2.setText("Operation ID:");
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
    // controls what happens when the Emerg.Contact button is clicked
    @FXML
    void emergContactButton (ActionEvent event){
        currentState = 3;
        text.setText("To DELETE a Contact: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button  \nTo UPDATE an existing Contact: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Emergency Contact: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Hospital button is clicked
    @FXML
    void hospitalButton (ActionEvent event){
        currentState = 4;
        text.setText("To DELETE a Hospital: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button  \nTo UPDATE an existing Hospital: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Hospital: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Located in button is clicked
    @FXML
    void locatedInButton (ActionEvent event){
        currentState = 5;
        text.setText("To DELETE a Located In Relationship: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Located In Relationship: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Located In Relationship: enter the data constraints you would like to search, then click the search button");
        label1.setText("Hospital ID:");
        label2.setText("Medical ID:");
        label3.setText("Patient Room:");
        label4.setText("Admin ID:");
        label5.setText("Patient ID:");
        label6.setText("Pharmacist ID:");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    // controls what happens when the Medical Bill button is clicked
    @FXML
    void medicalBillButton (ActionEvent event){
        currentState = 6;
        text.setText("To DELETE a Medical Bill: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Medical Bill: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Medical Bill: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Nurse button is clicked
    @FXML
    void nurseButton (ActionEvent event){
        currentState = 7;
        text.setText("To DELETE a Nurse: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Nurse: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Nurse: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Operating Room button is clicked
    @FXML
    void operatingRoomButton (ActionEvent event){
        currentState = 8;
        text.setText("To DELETE an Operating Room: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Operating Room: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Operating Room: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Patient button is clicked
    @FXML
    void patientButton (ActionEvent event){
        currentState = 9;
        text.setText("To DELETE a Patient: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Patient: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Patient: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Patient Room button is clicked
    @FXML
    void patientRoomButton (ActionEvent event){
        currentState = 10;
        text.setText("To DELETE a Patient Room: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Patient Room: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Patient Room: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Preforms button is clicked
    @FXML
    void preformsButton (ActionEvent event){
        currentState = 11;
        text.setText("To DELETE a Performs Relationship: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Performs Relationship: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Performs Relationship: enter the data constraints you would like to search, then click the search button");
        label1.setText("Physician ID:");
        label2.setText("Lead Physician:");
        label3.setText("Operation ID:");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    // controls what happens when the Pharmacist button is clicked
    @FXML
    void pharmacistButton (ActionEvent event){
        currentState = 12;
        text.setText("To DELETE a Pharmacist: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Pharmacist: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Pharmacist: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Physician button is clicked
    @FXML
    void physicianButton (ActionEvent event){
        currentState = 13;
        text.setText("To DELETE a Physician: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Physician: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Physician: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Surgery button is clicked
    @FXML
    void surgeryButton (ActionEvent event) {
        currentState = 14;
        text.setText("To DELETE a Surgery: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Surgery: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Surgery: enter the data constraints you would like to search, then click the search button");
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
    // controls what happens when the Treated By button is clicked
    @FXML
    void treatedByButton (ActionEvent event){
        currentState = 15;
        text.setText("To DELETE a Treated By Relationship: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Treated By Relationship: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Treated By Relationship: enter the data constraints you would like to search, then click the search button");
        label1.setText("Patient ID:");
        label2.setText("Physician ID:");
        label3.setText("Nurse ID:");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        pane.setContent(text);
    }
    // controls what happens when the Writes Prescription button is clicked
    @FXML
    void writesPrescriptionButton (ActionEvent event){
        currentState = 16;
        text.setText("To DELETE a Writes Prescription Relationship: Enter one or many fields referencing the data you would like to " +
                "delete, then click the delete button \nTo UPDATE an existing Writes Prescription Relationship: enter the data you would like to change, then click the update button" +
                "\nTo SEARCH an existing Writes Prescription Relationship: enter the data constraints you would like to search, then click the search button");
        label1.setText("Physician ID:");
        label2.setText("Pharmacist ID:");
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
    // controls what happens when the Delete button is clicked
    @FXML
    void deleteButton(ActionEvent event){
        e.deleteRow(submitTextFields(), currentState);
        clearTextFields();
        ongoingOutput += e.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
        currentState = 0;
    }
    // controls what happens when the Update button is clicked
    @FXML
    void updateButton(ActionEvent event){
        submitTextFields();
        for (i=0;i<10;i++){
            tempArray[i] = textInputs[i];
        }
        clearTextFields();
        text.setText("To finish your UPDATE, enter the data you would like to change, and then click the submit button");
        pane.setContent(text);
    }
    // controls what happens when the Submit button is clicked
    @FXML
    void submitButton(ActionEvent event){
        submitTextFields();
        for (i=0;i<10;i++){
            updatedArray[i] = textInputs[i];
        }
        u.updateRow(tempArray, updatedArray, currentState);
        clearTextFields();
        ongoingOutput += u.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
        currentState = 0;
    }
    // controls what happens when the Search button is clicked
    @FXML
    void searchButton (ActionEvent event){
        r.searchEntry(submitTextFields(), currentState);
        clearTextFields();
        ongoingOutput += r.boxString;               // Sets the text to the ScrollPane
        boxText.setText(ongoingOutput);
        pane.setContent(boxText);
        pane.setVvalue(1.0);
        currentState = 0;
    }
    // controls what happens when the Exit Application button is clicked
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

    // Method that reads the inputs of the textfields
    public String[] submitTextFields (){
        switch (currentState) {
            case 1:                 // ADMIN DELETE
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "admin_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "first_name=" + "'" + textField2.getText() + "'" ;
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "last_name=" + "'" + textField3.getText() + "'";
                }
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 2:                 // ASSISTS DELETE
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "nurse_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "operation_id=" + textField2.getText();
                }
                textInputs[2] = textField3.getText();
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 3:                 // DELETE EMERGENCY CONTACT
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "patient_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "phone_number=" + textField2.getText();
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "first_name=" + "'" + textField3.getText() + "'";
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "last_name=" + "'" + textField4.getText() + "'";
                }
                if (textField5.getText().equals("")){
                    textInputs[4] = textField5.getText();
                }
                else {
                    textInputs[4] = "address=" + "'" + textField5.getText() + "'";
                }
                if (textField6.getText().equals("")){
                    textInputs[5] = textField6.getText();
                }
                else {
                    textInputs[5] = "relation_to_patient=" + "'" + textField6.getText() + "'";
                }
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 4:                 // DELETE HOSPITAL
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "hospital_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "address=" + "'" + textField2.getText() + "'";
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "name=" + "'" + textField3.getText() + "'";
                }
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 5:                 // DELETE LOCATED IN
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "hospital_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "medical_id=" + textField2.getText();
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "patient_room=" + textField3.getText();
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "admin_id=" + textField4.getText();
                }
                if (textField5.getText().equals("")){
                    textInputs[4] = textField5.getText();
                }
                else {
                    textInputs[4] = "patient_id=" + textField5.getText();
                }
                if (textField6.getText().equals("")){
                    textInputs[5] = textField6.getText();
                }
                else {
                    textInputs[5] = "pharmacist_id=" + textField6.getText();
                }
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 6:                 // DELETE MEDICAL BILL
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "patient_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "bill_id=" + textField2.getText();
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "total_cost=" + textField3.getText();
                }
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 7:                 // DELETE NURSE
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "medical_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "first_name=" + "'" + textField2.getText() + "'";
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "last_name=" + "'" + textField3.getText() + "'";
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "specialization=" + "'" + textField4.getText() + "'";
                }
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 8:                 // DELETE OPERATING ROOM
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "room_number=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "specialty=" + "'" + textField2.getText() + "'";
                }
                textInputs[2] = textField3.getText();
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 9:                 // DELETE PATIENT
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "patient_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "first_name=" + "'" + textField2.getText() + "'";
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "last_name=" + "'" + textField3.getText() + "'";
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "ohip_number=" + textField4.getText();
                }
                if (textField5.getText().equals("")){
                    textInputs[4] = textField5.getText();
                }
                else {
                    textInputs[4] = "phone_number=" + textField5.getText();
                }
                if (textField6.getText().equals("")){
                    textInputs[5] = textField6.getText();
                }
                else {
                    textInputs[5] = "room_number=" + textField6.getText();
                }
                if (textField7.getText().equals("")){
                    textInputs[6] = textField7.getText();
                }
                else {
                    textInputs[6] = "physician_id=" + textField7.getText();
                }
                if (textField8.getText().equals("")){
                    textInputs[7] = textField8.getText();
                }
                else {
                    textInputs[7] = "pharmacist_id=" + textField8.getText();
                }
                if (textField9.getText().equals("")){
                    textInputs[8] = textField9.getText();
                }
                else {
                    textInputs[8] = "nurse_id=" + textField9.getText();
                }
                if (textField10.getText().equals("")){
                    textInputs[9] = textField10.getText();
                }
                else {
                    textInputs[9] = "admin_id=" + textField10.getText();
                }
                break;
            case 10:                // DELETE PATIENT ROOM
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "room_number=" + textField1.getText();
                }
                textInputs[1] = textField2.getText();
                textInputs[2] = textField3.getText();
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 11:                // PREFORMS DELETE
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "physician_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "lead_physician=" + textField2.getText();
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "operation_id=" + textField3.getText();
                }
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 12:                // PHARMACIST DELETE
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "pharmacist_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "first_name=" + "'" + textField2.getText() + "'";
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "last_name=" + "'" + textField3.getText() + "'";
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "prescriptions_filled=" + textField4.getText();
                }
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 13:                    // DELETE PHYSICIAN
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "medical_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "first_name=" + "'" +  textField2.getText() + "'";
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "last_name=" + "'" +  textField3.getText() + "'";
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "specialty=" + "'" +  textField4.getText() + "'";
                }
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 14:                       // DELETE SURGERY
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "patient_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "operation_id=" + textField2.getText();
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "operation_type=" + "'" + textField3.getText() + "'";
                }
                if (textField4.getText().equals("")){
                    textInputs[3] = textField4.getText();
                }
                else {
                    textInputs[3] = "operating_room=" + textField4.getText();
                }
                if (textField5.getText().equals("")){
                    textInputs[4] = textField5.getText();
                }
                else {
                    textInputs[4] = "medical_bill=" + textField5.getText();
                }
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 15:                    // DELETE TREATED BY
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "patient_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "physician_id=" + textField2.getText();
                }
                if (textField3.getText().equals("")){
                    textInputs[2] = textField3.getText();
                }
                else {
                    textInputs[2] = "nurse_id=" + textField3.getText();
                }
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            case 16:                    // DELETE Writes Prescription
                if (textField1.getText().equals("")){
                    textInputs[0] = textField1.getText();
                }
                else {
                    textInputs[0] = "physician_id=" + textField1.getText();
                }
                if (textField2.getText().equals("")){
                    textInputs[1] = textField2.getText();
                }
                else {
                    textInputs[1] = "patient_id=" + textField2.getText();
                }
                textInputs[2] = textField3.getText();
                textInputs[3] = textField4.getText();
                textInputs[4] = textField5.getText();
                textInputs[5] = textField6.getText();
                textInputs[6] = textField7.getText();
                textInputs[7] = textField8.getText();
                textInputs[8] = textField9.getText();
                textInputs[9] = textField10.getText();
                break;
            default:
                text.setText("Please choose a table to add to before submitting");
                pane.setContent(text);
                clearTextFields();
                currentState = 0;
                break;
        }
        return textInputs;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Statement conn = OracleCon.connectDB();
    }

}
