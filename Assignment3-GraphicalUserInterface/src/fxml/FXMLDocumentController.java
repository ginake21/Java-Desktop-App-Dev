package fxml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cejv569.Data.PatientData;
import com.cejv569.Business.HospitalImpl;
import fxml.Assignment3Desktop;
import static fxml.Assignment3Desktop.root;
import java.sql.*;
import java.util.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author gina0
 */
public class FXMLDocumentController implements Initializable {
//    Assignment3Desktop main = new  Assignment3Desktop();
    HospitalImpl hospital = new HospitalImpl();
    public static int patientid = 0;
    String lastname = null;
    String firstname = null;
    String diagnosis = null;
    Timestamp admissiondate = null;
    Timestamp releasedate = null;
    
    @FXML
    AnchorPane rootPane;
    
    @FXML
    private TextField patientid_tx, lastname_tx, firstname_tx, diagnosis_tx, admissiondate_tx, rdate_tx;
    
    @FXML
    private Button find_btn, save_btn, delete_btn, prev_btn, next_btn, 
            clear_btn, exit_btn, report_btn, surgical_btn, medication_btn, inpatient_btn;
    
    @FXML
    private TextArea textarea_p;
    
  
    @FXML
    private void btnClicked(ActionEvent event) throws SQLException{
                
        if(event.getTarget() == clear_btn){
            patientid_tx.setText("");
            lastname_tx.setText("");
            firstname_tx.setText("");
            diagnosis_tx.setText("");
            admissiondate_tx.setText("");
            rdate_tx.setText("");
        }

        if(event.getTarget() == find_btn){
            if(!patientid_tx.getText().equals("")){
                try{
                    patientid = Integer.parseInt(patientid_tx.getText());
                }catch(Exception e){
                    textarea_p.setText("You may enter the wrong input for the patient id. Please try again");
                }   
                PatientData patient = hospital.findByPatientID(patientid);
                if(patient.getPatientID() != 0){
//                    PatientData p = hospital.findByPatientID(patientid);
//                    textarea_p.setText(p.toString() + "\n\n");
                    textarea_p.setText("");
                    patientInfo(patient);
//                    textarea_p.appendText("Patient ID: " + patient.getPatientID() + "\n");
//                    textarea_p.appendText("Last Name: " + patient.getLastName() + "\n");
//                    textarea_p.appendText("First Name: " + patient.getFirstName() + "\n");
//                    textarea_p.appendText("Diagnosis: " + patient.getDiagnosis() + "\n");
//                    textarea_p.appendText("Admission Date: " + patient.getAdmissionDate() + "\n");
//                    textarea_p.appendText("Release Date: " + patient.getReleaseDate() + "\n");
                }else{
                    textarea_p.setText("There is no Patient id# " + patientid);
                }
                
            }
//            System.out.println("patient id..." + patientid);
        }
        
        if(event.getTarget() == save_btn){
            if(patientid_tx.getText().equals("")){
                try{
                    lastname = lastname_tx.getText();
                    firstname = firstname_tx.getText();
                    diagnosis = diagnosis_tx.getText();
                    admissiondate = Timestamp.valueOf(admissiondate_tx.getText());
                    releasedate = Timestamp.valueOf(rdate_tx.getText());
                }catch(Exception e){
                    textarea_p.setText("This is for testing");
                }

                PatientData patient = new PatientData(lastname, firstname, diagnosis, admissiondate, releasedate);
                hospital.createPatient(patient);
                
                int size = hospital.findAll().size();
                PatientData patient1 = hospital.findAll().get(size - 1);
//          System.out.println("Patient id is " + patient.getPatientID());
          
                patient.setPatientID(patient1.getPatientID());
//                System.out.println("new patient created");
//                textarea_p.setText("New patient created: \n " + patient.toString());
                textarea_p.setText("New patient created\n\n");    
                patientInfo(patient);

                patientid =  patient.getPatientID();
//                textarea_p.appendText("Patient ID: " + patient.getPatientID() + "\n");
//                textarea_p.appendText("Last Name: " + patient.getLastName() + "\n");
//                textarea_p.appendText("First Name: " + patient.getFirstName() + "\n");
//                textarea_p.appendText("Diagnosis: " + patient.getDiagnosis() + "\n");
//                textarea_p.appendText("Admission Date: " + patient.getAdmissionDate() + "\n");
//                textarea_p.appendText("Release Date: " + patient.getReleaseDate() + "\n");
            }else{
                try{
                    patientid = Integer.parseInt(patientid_tx.getText());
                    lastname = lastname_tx.getText();
                    firstname = firstname_tx.getText();
                    diagnosis = diagnosis_tx.getText();
                    admissiondate = Timestamp.valueOf(admissiondate_tx.getText());
                    releasedate = Timestamp.valueOf(rdate_tx.getText());
                }catch(Exception e){
                    textarea_p.setText("You may enter the wrong input, please try again");
                }
                PatientData patient = new PatientData(lastname, firstname, diagnosis, admissiondate, releasedate);
                patient.setPatientID(patientid);
                if(hospital.findByPatientID(patientid).getPatientID()!=0){
                    hospital.Update(patient ,patientid );
    //                textarea_p.setText("Patient data is updated\n" + patient.toString());
                    textarea_p.setText("Patient data is updated\n\n");         
                    patientInfo(patient);
//                    textarea_p.appendText("Patient ID: " + patient.getPatientID() + "\n");
//                    textarea_p.appendText("Last Name: " + patient.getLastName() + "\n");
//                    textarea_p.appendText("First Name: " + patient.getFirstName() + "\n");
//                    textarea_p.appendText("Diagnosis: " + patient.getDiagnosis() + "\n");
//                    textarea_p.appendText("Admission Date: " + patient.getAdmissionDate() + "\n");
//                    textarea_p.appendText("Release Date: " + patient.getReleaseDate() + "\n");                
                }else{
                    textarea_p.setText("Patient ID " + patientid + " doesn't exist");
                }
            }
        }
        
        if(event.getTarget() == prev_btn){
            if(patientid>1){
                patientid = patientid -1;
            }else if(patientid == 1){
                int size = hospital.findAll().size();
                patientid = hospital.findAll().get(size-1).getPatientID();
            }else{
                int size = hospital.findAll().size();
                patientid = hospital.findAll().get(size-1).getPatientID();
            }
            PatientData patient = hospital.findByPatientID(patientid);
            if(patient.getPatientID() != 0){
                PatientData p = hospital.findByPatientID(patientid);
                textarea_p.setText("");
                patientInfo(p);
//                textarea_p.appendText("Patient ID: " + p.getPatientID() + "\n");
//                textarea_p.appendText("Last Name: " + p.getLastName() + "\n");
//                textarea_p.appendText("First Name: " + p.getFirstName() + "\n");
//                textarea_p.appendText("Diagnosis: " + p.getDiagnosis() + "\n");
//                textarea_p.appendText("Admission Date: " + p.getAdmissionDate() + "\n");
//                textarea_p.appendText("Release Date: " + p.getReleaseDate() + "\n");
//                textarea_p.setText(hospital.findByPatientID(patientid).toString());
            }else{
                textarea_p.setText("There is no Patient information for id# " + patientid);
            }
        }
        
        if(event.getTarget() == next_btn){
            int size = hospital.findAll().size();
            if(patientid < hospital.findAll().get(size-1).getPatientID()){
                patientid = patientid + 1;
            }else{
                patientid = 1;
            }
            
            PatientData patient = hospital.findByPatientID(patientid);
            if(patient.getPatientID() != 0){
                PatientData p = hospital.findByPatientID(patientid);
                textarea_p.setText("");
                patientInfo(p);
//                textarea_p.appendText("Patient ID: " + p.getPatientID() + "\n");
//                textarea_p.appendText("Last Name: " + p.getLastName() + "\n");
//                textarea_p.appendText("First Name: " + p.getFirstName() + "\n");
//                textarea_p.appendText("Diagnosis: " + p.getDiagnosis() + "\n");
//                textarea_p.appendText("Admission Date: " + p.getAdmissionDate() + "\n");
//                textarea_p.appendText("Release Date: " + p.getReleaseDate() + "\n");
//                textarea_p.setText(hospital.findByPatientID(patientid).toString());
            }else{
                textarea_p.setText("There is no Patient information for id# " + patientid);
            }
        }
        
        if(event.getTarget() == delete_btn){
            try{
                patientid = Integer.parseInt(patientid_tx.getText());
            }catch(Exception e){
                textarea_p.setText("You may enter the wrong input, please try again");
            }     
            hospital.deletePatient(patientid); 
            textarea_p.setText("The data of patient# " +patientid +" is deleted");
        }
        
        if(event.getTarget() == exit_btn){   
            System.exit(0);
        }
        
        // need to change to pop-up window and SQL need to be modified
        if(event.getTarget() == report_btn){
            if(!patientid_tx.equals("")){
                patientid = patientid; 
            }else{
                try{
                    patientid = Integer.parseInt(patientid_tx.getText());
                }catch(Exception e){
                    textarea_p.setText("You may enter the wrong input, please try again");
                }  
            }
            
            String query1 = "select sum(unitcost * units) as cost from medication where patientid = ?";
            String query2 = "select sum(dailyrate)+ sum(supplies) + sum(services) as cost from inpatient where patientid = ?";
            String query3 = "select sum(roomfee) + sum(surgeonfee) + sum(SUPPLIES) as cost from surgical WHERE patientid = ?";
            

            String totalCost = String.format("$%.2f", hospital.runQuery(query1, patientid) + hospital.runQuery(query2, patientid) + hospital.runQuery(query3, patientid));
            System.out.println(totalCost);
            JOptionPane popup = new JOptionPane();
//            popup.setFont(new Font("Serif", Font.ITALIC, 20));
            UIManager.put("OptionPane.minimumSize",new Dimension(800,350));             
            UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 30));
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,30)));
            popup.showMessageDialog(null, "The total cost of patient ID#" + patientid + " is : " + totalCost, " Total Cost", 1);

  
        }
        
        

    }
    
    @FXML
    private void changeScene(ActionEvent event) throws SQLException, Exception{
        if(event.getTarget() == inpatient_btn){

            Assignment3Desktop.root = FXMLLoader.load(getClass().getResource("FXMLInpatient.fxml"));
            Scene sceneInpatient = new Scene(root); 
            sceneInpatient.getStylesheets().add("/styles/hospital.css");
//            Assignment3Desktop.window.setTitle("Gina's Hospital System");
            Assignment3Desktop.window.setScene(sceneInpatient);
            Assignment3Desktop.window.show();
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLInpatient.fxml"));            
//            rootPane.getChildren().setAll(pane);
        }
        if(event.getTarget() == medication_btn){
            Assignment3Desktop.root = FXMLLoader.load(getClass().getResource("FXMLMedication.fxml"));
            Scene sceneMedication = new Scene(root); 
            sceneMedication.getStylesheets().add("/styles/hospital.css");
            Assignment3Desktop.window.setScene(sceneMedication);
            Assignment3Desktop.window.show();            
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLMedication.fxml"));
//            rootPane.getChildren().setAll(pane);
        }
        if(event.getTarget() == surgical_btn){
            Assignment3Desktop.root = FXMLLoader.load(getClass().getResource("FXMLSurgical.fxml"));
            Scene sceneSurgical = new Scene(root); 
            sceneSurgical.getStylesheets().add("/styles/hospital.css");
            Assignment3Desktop.window.setScene(sceneSurgical);
            Assignment3Desktop.window.show();    
            
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLSurgical.fxml"));
//            rootPane.getChildren().setAll(pane);
        }
    }
    
    
    private void patientInfo(PatientData patient){
        textarea_p.appendText("Patient ID: " + patient.getPatientID() + "\n");
        textarea_p.appendText("Last Name: " + patient.getLastName() + "\n");
        textarea_p.appendText("First Name: " + patient.getFirstName() + "\n");
        textarea_p.appendText("Diagnosis: " + patient.getDiagnosis() + "\n");
        textarea_p.appendText("Admission Date: " + patient.getAdmissionDate() + "\n");
        textarea_p.appendText("Release Date: " + patient.getReleaseDate() + "\n");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
