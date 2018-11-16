/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import com.cejv569.Business.HospitalImpl;
import com.cejv569.Data.InpatientData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.cejv569.Data.MedicationData;
import static fxml.Assignment3Desktop.root;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author gina0
 */
public class FXMLMedicationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    HospitalImpl hospital = new HospitalImpl();
    int medicationid =0;
    Timestamp dateofmed = null;
    String med = "";
    double unitcost = 0;
    double units =0 ;
    int patientid = FXMLDocumentController.patientid;
    
    @FXML
    AnchorPane medicationRoot;
    
    @FXML
    TextArea textarea_m;
    
    @FXML
    private TextField medicationid_tx, dateofmed_tx, med_tx, unitcost_tx, units_tx, patientid_tx;
    
    @FXML
    private Button find_btn_m, save_btn_m, delete_btn_m, prev_btn_m, next_btn_m, clear_btn_m, back_btn_m;
    
   
    @FXML
    private void btnClicked(ActionEvent event) throws SQLException, Exception{
        if(event.getTarget() == back_btn_m){
            Assignment3Desktop.root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scenePatient = new Scene(root); 
            scenePatient.getStylesheets().add("/styles/hospital.css");
            Assignment3Desktop.window.setScene(scenePatient);
            Assignment3Desktop.window.show();              
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//            medicationRoot.getChildren().setAll(pane);
        }
        
        if(event.getTarget() == find_btn_m){
            if(!medicationid_tx.getText().equals("")){
                try{
                    medicationid = Integer.parseInt(medicationid_tx.getText());
                }catch(Exception e){
                    textarea_m.setText("You may enter the wrong input for the medication id. Please try again");
                } 
                
                MedicationData medicationSearched = hospital.findByMedicationID(medicationid);
                if(medicationSearched.getId() != 0){
//                    textarea_m.setText(medicationSearched.toString()); 
                    textarea_m.setText("");
                    medicationInfo(medicationSearched);
                }else{
                    textarea_m.setText("There is no Medication id# " + medicationid);
                }
            }
        }
        
        if(event.getTarget() == prev_btn_m){
            if(medicationid>1){
                medicationid = medicationid -1;
            }else if(medicationid== 1|| medicationid == 0){
                int size = hospital.findAll("medication");
                medicationid = hospital.findAllMedication().get(size-1).getId();
            }
            
            MedicationData medication = hospital.findByMedicationID(medicationid);
            if(medication.getPatientID() != 0){
//                textarea_m.setText(medication.toString());
                textarea_m.setText("");
                medicationInfo(medication);
            }else{
                textarea_m.setText("There is no Medication id# " + medicationid);
            }
        }
                
        if(event.getTarget() == next_btn_m){
            int size = hospital.findAll("medication");
            int lastid = hospital.findAllMedication().get(size-1).getId();
            System.out.print("Last id: " + lastid);
            
            if(medicationid >= lastid ||medicationid == 0 ){
                // if it's already the last item, then we go to get the first item
                medicationid = hospital.findAllInpatient().get(0).getId();
            }else if(medicationid>=1){
                medicationid = medicationid +1;
            }
            MedicationData medication = hospital.findByMedicationID(medicationid);
            if(medication.getPatientID() != 0){
                // if patient id is 0, means it's the default set, there is not medication info found 
//                textarea_m.setText(medication.toString());
                textarea_m.setText("");
                medicationInfo(medication);
            }else{
                textarea_m.setText("There is no Medication id# " + medicationid);
            }
        }  
        
        
        if(event.getTarget() == save_btn_m){
            if(medicationid_tx.getText().equals("")){
                try{
//                    medicationid = Integer.parseInt(medicationid_tx.getText());
                    dateofmed = Timestamp.valueOf(dateofmed_tx.getText());
                    med = med_tx.getText();
                    unitcost = Double.parseDouble(unitcost_tx.getText());
                    units = Double.parseDouble(units_tx.getText());
                    patientid = Integer.parseInt(patientid_tx.getText());                     
                }catch(Exception e){
                    textarea_m.setText("You may not enter all the required field correctly");
                }
//            MedicationData(Timestamp dateOfMed, String med, double unitCost, double units, int patientID)
                try{
                    hospital.createMedication(new MedicationData(dateofmed, med,  unitcost, units, patientid));
                    int size = hospital.findAllMedication().size();
                    textarea_m.setText("New Medication created\n\n");
                    MedicationData m = hospital.findAllMedication().get(size-1);
//                    textarea_m.appendText(hospital.findAllMedication().get(size-1).toString());
                    medicationInfo(m);
                    medicationid = m.getId();

                }catch(SQLIntegrityConstraintViolationException e){
                    textarea_m.setText("The Patient id# " + patientid + " doesn't exist");
                }                         
            }else{
                try{
                    medicationid = Integer.parseInt(medicationid_tx.getText());
                    dateofmed = Timestamp.valueOf(dateofmed_tx.getText());
                    med = med_tx.getText();
                    unitcost = Double.parseDouble(unitcost_tx.getText());
                    units = Double.parseDouble(units_tx.getText());
                    patientid = Integer.parseInt(patientid_tx.getText());      
                }catch(Exception e){
                    textarea_m.setText("You may enter the wrong input");
                }
                MedicationData medication = new MedicationData(dateofmed, med,  unitcost, units, patientid);
                int result = 0;
                
                try{
                    result = hospital.Update(medication, medicationid);
                    if(result != 1){
                        textarea_m.setText("Update failed - medication id might not be valid");
                    } else{
                        medication.setId(medicationid);
//                        textarea_m.setText("Medication information updated. \n" + medication.toString());
                        textarea_m.setText("Medication information updated\n\n");
                        medicationInfo(medication);
                    }
                }catch(SQLIntegrityConstraintViolationException e){
                    textarea_m.setText("Patient id# " + patientid + " does not exist");
                }
            }
        } 
        
        
        
        if(event.getTarget() == delete_btn_m){
            try{
                medicationid = Integer.parseInt(medicationid_tx.getText());
            }catch(Exception e){
                textarea_m.setText("You may enter the wrong input, please try again");
            }     
            int result = hospital.deleteMedication(medicationid);
            if(result == 1){
                textarea_m.setText("The data of medication# " +medicationid +" is deleted");
            }else{
                textarea_m.setText("Update failed, the medication id# " + medicationid + " doesn't exist");
            } 
        }
        
        if(event.getTarget() == clear_btn_m){
            medicationid_tx.setText(""); 
            dateofmed_tx.setText("");
            med_tx.setText("");
            unitcost_tx.setText("");
            units_tx.setText("");
            patientid_tx.setText("");
        }
    }
    
    public void medicationInfo(MedicationData m){
        textarea_m.appendText("Medication ID: " + m.getId() + "\n");
        textarea_m.appendText("Date of med: " + m.getDateOfMed() + "\n");
        textarea_m.appendText("Med: " + m.getMed() + "\n");
        textarea_m.appendText("Unit cost: $" + m.getUnitCost() + "\n");
        textarea_m.appendText("Units: " + m.getUnits() + "\n");
        textarea_m.appendText("Patient ID: " + m.getPatientID());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<MedicationData> medicationData = null;
        try{
            medicationData = hospital.findByPatientID_M(patientid);
            if(patientid != 0){
                textarea_m.setText("There are " + hospital.findByPatientID_M(patientid).size() + " medication data for patient id# " +patientid + "\n");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }        
        
        for(MedicationData m : medicationData){
//MedicationData{id=2, dateOfMed=2014-02-19 07:00:00.0, med=M and M, unitCost=1.1, units=15.0, patientID=2}
//            textarea_m.appendText(m.toString() +"\n");
            textarea_m.appendText("\nMedication ID: " + m.getId() + "\n");
            textarea_m.appendText("Date of med: " + m.getDateOfMed() + "__");
            textarea_m.appendText("Med: " + m.getMed() + "__");
            textarea_m.appendText("Unit cost: $S" + m.getUnitCost() + "__");
            textarea_m.appendText("Units: " + m.getUnits() + "__");
            textarea_m.appendText("Patient ID: " + m.getPatientID()+"\n");
        }
    }    
    
}
