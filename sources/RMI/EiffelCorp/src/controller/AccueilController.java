package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

public class AccueilController implements Initializable {


	@FXML
	private TextField username;
	@FXML
	private PasswordField textpassword;
	@FXML
	private Button login;

	
	 @Override  
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void signup (ActionEvent event) throws SQLException, NotBoundException, FileNotFoundException, IOException, ClassNotFoundException {
            try {
            	if(username.getText().equals("Admin") &&  textpassword.getText().equals("Admin")) 
                {
            		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IfsCarMenu.fxml")) ;
                    Parent  root =(Parent) loader.load();  
                    IfsCarMenuController rc = loader.getController() ;   
                    rc.setUsername(username.getText());
                    rc.AfficherCar(); 
                    rc.setConfirmModification();
                    username.getScene().setRoot(root);   
                }
            	
                  else 
                   {
                	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EiffelCorpMenu.fxml")) ;               	  
                      Parent root ;  
                       root =(Parent) loader.load(); 
                       EiffelCorpMenuController rc = loader.getController() ;  
                       rc.setUsername(username.getText()) ; 
                       rc.AfficherCar();
                       rc.AfficherCarRented() ; 
                       username.getScene().setRoot(root)   ;
                   }

                   }
                    
                  catch (IOException ex) {
                	  	System.out.println(ex);
                  } 
       
     } 
}
