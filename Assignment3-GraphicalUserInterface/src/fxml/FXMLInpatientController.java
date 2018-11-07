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
    private Button find_btn_i, save_btn, delete_btn, prev_btn, next_btn, clear_btn, back_btn;
    
    @FXML
    private void btnClicked(ActionEvent event) throws SQLException, Exception{
        if(event.getTarget() == back_btn){
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
