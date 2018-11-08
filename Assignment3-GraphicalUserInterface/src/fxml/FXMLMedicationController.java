/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import com.cejv569.Business.HospitalImpl;
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
import com.cejv569.Data.InpatientData;
import com.cejv569.Data.MedicationData;
import java.sql.SQLIntegrityConstraintViolationException;

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
    int patientid = 0; 
    
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            medicationRoot.getChildren().setAll(pane);
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
                    textarea_m.setText(medicationSearched.toString()); 
                }else{
                    textarea_m.setText("There is no Medication id# " + medicationid);
                }
            }
        }
        
        if(event.getTarget() == prev_btn_m){
//            if(medicationid>1){
//                medicationid = medicationid -1;
//            }else if(medicationid== 1){
//                int size = hospital.findAll("medication");
//                medicationid = hospital.find
//                        .findAllInpatient().get(size-1).getId();
//            }
//            InpatientData inpatient = hospital.findByInpatientID(inpatientid);
//            if(inpatient.getPatientID() != 0){
//                textarea_i.setText(inpatient.toString());
//            }else{
//                textarea_i.setText("There is no Inpatient information for id# " + inpatientid);
//            }
        }
                
                
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
