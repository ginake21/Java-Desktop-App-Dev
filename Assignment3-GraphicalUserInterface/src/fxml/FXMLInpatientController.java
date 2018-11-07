/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.cejv569.Business.HospitalImpl;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gina0
 */
public class FXMLInpatientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    HospitalImpl hospital = new HospitalImpl();
    int inpatientid = 0;
    
    @FXML
    AnchorPane inpatientRoot;
    
    @FXML
    TextArea textarea_p;
    
    @FXML
    private TextField inpatientid_tx, dateofstay_tx, roomnumber_tx, dailyrate_tx, supplies_tx, services_tx, patientid_tx;
    
    @FXML
    private Button find_btn, save_btn, delete_btn, prev_btn, next_btn, 
            clear_btn, back_btn;
    
    @FXML
    private void btnClicked(ActionEvent event) throws SQLException, Exception{
        if(event.getTarget() == back_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            inpatientRoot.getChildren().setAll(pane);
        }

        if(event.getTarget() == find_btn){
            if(!inpatientid_tx.getText().equals("")){
                try{
                    inpatientid = Integer.parseInt(inpatientid_tx.getText());
                }catch(Exception e){
                    textarea_p.setText("You may enter the wrong input for the patient id. Please try again");
                }                
//                textarea_p.setText(hospital.findByPatientID(patientid).toString());
            }
//            System.out.println("patient id..." + patientid);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
