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
import com.cejv569.Data.SurgicalData;
import static fxml.Assignment3Desktop.root;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author gina0
 */
public class FXMLSurgicalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    HospitalImpl hospital = new HospitalImpl();
    int surgicalid = 0;
    Timestamp dateofsurgery = null;
    String surgery = "";
    double roomfee = 0;
    double surgeonfee = 0;
    double supplies = 0;
    int patientid = FXMLDocumentController.patientid;
    
    
    @FXML
    AnchorPane surgicalRoot;
    
    @FXML
    TextArea textarea_s;
    
    @FXML
    private TextField surgicalid_tx, dateofsurgery_tx, surgery_tx, roomfee_tx, surgeonfee_tx, supplies_tx, patientid_tx;
    
    @FXML
    private Button find_btn_s, save_btn_s, delete_btn_s, prev_btn_s, next_btn_s, clear_btn_s, back_btn_s;
        
    @FXML
    private void btnClicked(ActionEvent event) throws SQLException, Exception{
        if(event.getTarget() == back_btn_s){
            Assignment3Desktop.root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scenePatient = new Scene(root); 
            scenePatient.getStylesheets().add("/styles/hospital.css");
            Assignment3Desktop.window.setScene(scenePatient);
            Assignment3Desktop.window.show();           
        }
        
        if(event.getTarget() == find_btn_s){
            if(!surgicalid_tx.getText().equals("")){
                try{
                    surgicalid = Integer.parseInt(surgicalid_tx.getText());
                }catch(Exception e){
                    textarea_s.setText("You may enter the wrong input for the surgical id. Please try again");
                } 
                
                SurgicalData surgicalSearched = hospital.findBySurgicalID(surgicalid);
                if(surgicalSearched.getId() != 0){
                    textarea_s.setText("");
                    surgicalInfo(surgicalSearched);
                }else{
                    textarea_s.setText("There is no Surgical id# " + surgicalid);
                }
            }
        }
        
        if(event.getTarget() == prev_btn_s){
            if(surgicalid>1){
                surgicalid = surgicalid -1;
            }else if(surgicalid == 1){
                int size = hospital.findAll("surgical");
                surgicalid = hospital.findAllSurgical().get(size-1).getId();
            }
            
            SurgicalData surgical = hospital.findBySurgicalID(surgicalid);
            if(surgical.getPatientID() != 0){
                textarea_s.setText("");
                surgicalInfo(surgical);
            }else{
                textarea_s.setText("There is no Surgical id# " + surgicalid);
            }
        }
        
        if(event.getTarget() == next_btn_s){
            int size = hospital.findAll("surgical");
            int lastid = hospital.findAllSurgical().get(size-1).getId();
            
            if(surgicalid >= lastid | surgicalid == 0){
                // if it's already the last item, then we go to get the first item
                surgicalid = hospital.findAllSurgical().get(0).getId();
            }else if(surgicalid>=1){
                surgicalid = surgicalid +1;
            }
            SurgicalData surgical = hospital.findBySurgicalID(surgicalid);
            if(surgical.getPatientID() != 0){
                // if patient id is 0, means it's the default set, there is not medication info found 
                textarea_s.setText("");
                surgicalInfo(surgical);
            }else{
                textarea_s.setText("There is no Surgical id# " + surgicalid);
            }
        } 
        
        
                
        if(event.getTarget() == save_btn_s){
            if(surgicalid_tx.getText().equals("")){
                try{                    
                    dateofsurgery = Timestamp.valueOf(dateofsurgery_tx.getText());
                    surgery = surgery_tx.getText();
                    roomfee = Double.parseDouble(roomfee_tx.getText());
                    surgeonfee = Double.parseDouble(surgeonfee_tx.getText());
                    supplies = Double.parseDouble(supplies_tx.getText());
                    patientid = Integer.parseInt(patientid_tx.getText());
                  
                }catch(Exception e){
                    textarea_s.setText("You may not enter all the required field correctly");
                }
//SurgicalData(Timestamp dateOfSurgery, String surgery, double roomFee, double surgeonFee, double supplies, int patientID)
                try{
                    hospital.createSurgical(new SurgicalData(dateofsurgery, surgery, roomfee, surgeonfee, supplies, patientid));
                    int size = hospital.findAllSurgical().size();
                    textarea_s.setText("New Surgical created\n\n");
                    SurgicalData s = hospital.findAllSurgical().get(size-1);
                    surgicalInfo(s);
                    surgicalid = s.getId();

                }catch(SQLIntegrityConstraintViolationException e){
                    textarea_s.setText("The Patient id# " + patientid + " doesn't exist");
                }                         
            }else{
                try{
                    surgicalid = Integer.parseInt(surgicalid_tx.getText());
                    dateofsurgery = Timestamp.valueOf(dateofsurgery_tx.getText());
                    surgery = surgery_tx.getText();
                    roomfee = Double.parseDouble(roomfee_tx.getText());
                    surgeonfee = Double.parseDouble(surgeonfee_tx.getText());
                    supplies = Double.parseDouble(supplies_tx.getText());
                    patientid = Integer.parseInt(patientid_tx.getText());
    
                }catch(Exception e){
                    textarea_s.setText("You may enter the wrong input");
                }
                SurgicalData surgical = new SurgicalData(dateofsurgery, surgery, roomfee, surgeonfee, supplies, patientid);
                int result = 0;
                
                try{
                    result = hospital.Update(surgical, surgicalid);
                    if(result != 1){
                        textarea_s.setText("Update failed - surgical id is not valid");
                    } else{
                        surgical.setId(surgicalid);
                        textarea_s.setText("Surgical information updated. \n\n");
                        surgicalInfo(surgical);
                    }
                }catch(SQLIntegrityConstraintViolationException e){
                    textarea_s.setText("Patient id# " + patientid + " does not exist");
                }
            }
        } 
        
        
        if(event.getTarget() == delete_btn_s){
            try{
                surgicalid = Integer.parseInt(surgicalid_tx.getText());
            }catch(Exception e){
                textarea_s.setText("You may enter the wrong input, please try again");
            }     
            int result = hospital.deleteSurgical(surgicalid);
            if(result == 1){
                textarea_s.setText("The data of surgical id# " +surgicalid +" is deleted");
            }else{
                textarea_s.setText("Update failed, the surgical id# " + surgicalid + " doesn't exist");
            } 
        }
        
        if(event.getTarget() == clear_btn_s){
            surgicalid_tx.setText("");
            dateofsurgery_tx.setText(""); 
            surgery_tx.setText("");
            roomfee_tx.setText("");
            surgeonfee_tx.setText("");
            supplies_tx.setText("");
            patientid_tx.setText("");
        }
        
    }
    
    public void surgicalInfo(SurgicalData s){
        textarea_s.appendText("Surgical ID: " + s.getId()+ "\n");
        textarea_s.appendText("Date of surgery: " + s.getDateOfSurgery() + "\n");
        textarea_s.appendText("Surgery: " + s.getSurgery() +"\n");
        textarea_s.appendText("Room fee: $" + s.getRoomFee() +"\n");
        textarea_s.appendText("Surgeon fee: $" + s.getSurgeonFee() + "\n");
        textarea_s.appendText("Supplies: $" + s.getSupplies() +"\n");
        textarea_s.appendText("Patient ID: " + s.getPatientID()+ "\n");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<SurgicalData> surgicalData = null;
        try{
            surgicalData = hospital.findByPatientID_S(patientid);
            if(patientid != 0){
                textarea_s.setText("There are " + hospital.findByPatientID_S(patientid).size() + " surgical data for patient id # " + patientid +"\n");
                        
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }        
        
        for(SurgicalData s : surgicalData){
//SurgicalData{id=2, dateOfSurgery=2014-02-19 07:00:00.0, surgery=Kidney Transplant, roomFee=2500.12, surgeonFee=4200.0, supplies=934.23, patientID=2}
            textarea_s.appendText("\nSurgical ID: " + s.getId()+ "\n");
            textarea_s.appendText("Date of surgery: " + s.getDateOfSurgery() + "__");
            textarea_s.appendText("Surgery: " + s.getSurgery() +"__");
            textarea_s.appendText("Room fee: $" + s.getRoomFee() +"__");
            textarea_s.appendText("Surgeon fee: $" + s.getSurgeonFee() + "__");
            textarea_s.appendText("Supplies: $" + s.getSupplies() +"__");
            textarea_s.appendText("Patient ID: " + s.getPatientID()+ "\n");
        }
    }    
    
}
