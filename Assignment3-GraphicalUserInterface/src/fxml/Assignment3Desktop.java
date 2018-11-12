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
    public static Stage window;
    Scene scenePatient; 
    public static Parent root;        
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        scenePatient = new Scene(root); 
        stage.setTitle("Gina's Hospital System");
        stage.setScene(scenePatient);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
