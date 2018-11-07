package fxml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gina0
 */
public class Assignment3Desktop extends Application {
    Stage window;
    Scene scenePatient; 
    Scene sceneInpatient; 
            
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Parent inpatient = FXMLLoader.load(getClass().getResource("FXMLInpatient.fxml"));
        
        scenePatient = new Scene(root); 
        stage.setScene(scenePatient);
//        stage.setScene(sceneInpatient);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
