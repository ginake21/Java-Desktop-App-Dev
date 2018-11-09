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
import java.sql.SQLIntegrityConstraintViolationException;

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
    Timestamp dateofmed = null;
    String med = "";
    double unitcost = 0;
    double units = 0;
    int patientid = 0;
    
    
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            surgicalRoot.getChildren().setAll(pane);
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
                    textarea_s.setText(surgicalSearched.toString()); 
                }else{
                    textarea_s.setText("There is no Surgical id# " + surgicalid);
                }
            }
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
