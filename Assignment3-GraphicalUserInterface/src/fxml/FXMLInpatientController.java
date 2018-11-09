/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.cejv569.Business.HospitalImpl;
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
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * FXML Controller class
 *
 * @author gina0
 */
public class FXMLInpatientController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    FXMLDocumentController mainController = new FXMLDocumentController();
    HospitalImpl hospital = new HospitalImpl();
    
    int inpatientid = 0;
    Timestamp dateofstay = null;
    String roomnumber = "";
    double dailyrate = 0;
    double supplies = 0;
    double services = 0;
    int patientid = 0;
//    int patientid = mainController.patientid;  

    
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
                    textarea_i.setText("There is no Inpatient id# " + inpatientSearched.getId());
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
        
        if(event.getTarget() == save_btn_i){
            if(inpatientid_tx.getText().equals("")){
                try{
//                        inpatientid = Integer.parseInt(inpatientid_tx.getText());
                    dateofstay = Timestamp.valueOf(dateofstay_tx.getText());
                    roomnumber = roomnumber_tx.getText();
                    dailyrate = Double.parseDouble(dailyrate_tx.getText());
                    supplies = Double.parseDouble(supplies_tx.getText());
                    services  = Double.parseDouble(services_tx.getText());
                    patientid = Integer.parseInt(patientid_tx.getText());                       
                }catch(Exception e){
                    textarea_i.setText("You may not enter all the required field correctly");
                }
//    public InpatientData(Timestamp dateOfStay, String roomNumber, double dailyRate, double supplies, double services, int patientID)
                try{
                    hospital.createInpatient(new InpatientData(dateofstay, roomnumber, dailyrate, supplies, services, patientid));
                    int size = hospital.findAllInpatient().size();
                    textarea_i.setText("New Inpatient created\n");
                    textarea_i.appendText(hospital.findAllInpatient().get(size-1).toString());

                }catch(SQLIntegrityConstraintViolationException e){
                    textarea_i.setText("The Inpatient id# " + inpatientid + " doesn't exist");
                }                         
            }else{
                try{
                inpatientid = Integer.parseInt(inpatientid_tx.getText());
                dateofstay = Timestamp.valueOf(dateofstay_tx.getText());
                roomnumber = roomnumber_tx.getText();
                dailyrate = Double.parseDouble(dailyrate_tx.getText());
                supplies = Double.parseDouble(supplies_tx.getText());
                services  = Double.parseDouble(services_tx.getText());
                patientid = Integer.parseInt(patientid_tx.getText());    
                }catch(Exception e){
                    textarea_i.setText("You may enter the wrong input");
                }
                InpatientData inpatient = new InpatientData(dateofstay, roomnumber, dailyrate, supplies, services, patientid);
                int result = 0;
                
                try{
                    result = hospital.Update(inpatient, inpatientid);
                    if(result != 1){
                        textarea_i.setText("Update failed - inpatient id might not be valid");
                    } else{
                        inpatient.setId(inpatientid);
                        textarea_i.setText("Inpatient information updated. \n" + inpatient.toString());
                    }
                }catch(SQLIntegrityConstraintViolationException e){
                    textarea_i.setText("Inpatient id# " + inpatient.getPatientID() + " does not exist");
                }
            }
        } 
        
        if(event.getTarget() == clear_btn_i){
            inpatientid_tx.setText(""); 
            dateofstay_tx.setText("");
            roomnumber_tx.setText("");
            dailyrate_tx.setText("");
            supplies_tx.setText("");
            services_tx.setText("");
            patientid_tx.setText("");
        }
        
        if(event.getTarget() == delete_btn_i){
            try{
                inpatientid = Integer.parseInt(inpatientid_tx.getText());
            }catch(Exception e){
                textarea_i.setText("You may enter the wrong input, please try again");
            }     
            int result = hospital.deleteInpatient(inpatientid);
            if(result == 1){
                textarea_i.setText("The data of inpatient# " +inpatientid +" is deleted");
            }else{
                textarea_i.setText("Update failed, the inpatient id# " + inpatientid + " doesn't exist");
            } 
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }    
    
}
