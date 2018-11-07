/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.cejv569.Business.HospitalImpl;
import fxml.FXMLDocumentController;
import java.net.URL;
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
import com.cejv569.Data.PatientData;
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author gina0
 */
public class FXMLInpatientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    FXMLDocumentController mainController = new FXMLDocumentController();
    HospitalImpl hospital = new HospitalImpl();
    
    int inpatientid = 0;
    Timestamp dateofstay = null;
    int roomnumber = 0;
    double dailyrate = 0;
    double supplies = 0;
    double services = 0;
    int patientid = mainController.patientid;  

    
    @FXML
    AnchorPane inpatientRoot;
    
    @FXML
    TextArea textarea_i;
    
    @FXML
    private TextField inpatientid_tx, dateofstay_tx, roomnumber_tx, dailyrate_tx, supplies_tx, services_tx, patientid_tx;
    
    @FXML
    private Button find_btn_i, save_btn_i, delete_btn_i, prev_btn_i, next_btn_i, clear_btn_i, back_btn_i;
    
    @FXML
    private void btnClicked(ActionEvent event) throws SQLException, Exception{
        if(event.getTarget() == back_btn_i){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            inpatientRoot.getChildren().setAll(pane);
        }

        if(event.getTarget() == find_btn_i){
            if(!inpatientid_tx.getText().equals("")){
                try{
                    inpatientid = Integer.parseInt(inpatientid_tx.getText());
                }catch(Exception e){
                    textarea_i.setText("You may enter the wrong input for the patient id. Please try again");
                } 
                
                InpatientData inpatientSearched = hospital.findByInpatientID(inpatientid);
                if(inpatientSearched.getPatientID()!= 0){
                    textarea_i.setText(inpatientSearched.toString()); 
                }else{
                    textarea_i.setText("There is no Inpatient information for id# " + inpatientSearched.getId());
                }
            }
        }
        
        if(event.getTarget() == prev_btn_i){
            if(inpatientid>1){
                inpatientid = inpatientid -1;
            }else if(inpatientid == 1){
                int size = hospital.findAll("inpatient");
                inpatientid = hospital.findAllInpatient().get(size-1).getId();
            }
            InpatientData inpatient = hospital.findByInpatientID(inpatientid);
            if(inpatient.getPatientID() != 0){
                textarea_i.setText(inpatient.toString());
            }else{
                textarea_i.setText("There is no Inpatient information for id# " + inpatientid);
            }
        }
        
        if(event.getTarget() == next_btn_i){
            int size = hospital.findAll("inpatient");
            int lastid = hospital.findAllInpatient().get(size-1).getId();
            
            if(inpatientid == lastid){
                inpatientid = hospital.findAllInpatient().get(0).getId();
            }else if(inpatientid>=1){
                inpatientid = inpatientid +1;
            }
            InpatientData inpatient = hospital.findByInpatientID(inpatientid);
            if(inpatient.getPatientID() != 0){
                textarea_i.setText(inpatient.toString());
            }else{
                textarea_i.setText("There is no Inpatient information for id# " + inpatientid);
            }
        }
                
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
//        StringBuilder sb = new StringBuilder();
//        try{
//            ArrayList<InpatientData> InpatientArray = hospital.findByPatientID_I(patientid);
//            for(InpatientData i: InpatientArray){
//                sb.append(i+"\n");                
//            }
//            textarea_i.setText(sb.toString());
//        }catch(Exception e){
//            textarea_i.setText("There is no inpatient information for patient id# " + patientid );
//        }
    }    
    
}
