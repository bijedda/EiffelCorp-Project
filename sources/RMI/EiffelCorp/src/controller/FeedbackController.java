package controller;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import fleet.IFleet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import rental.IRentalManagement;

public class FeedbackController {

    @FXML
    private Label CarId;

    @FXML
    private Button postuler;

    @FXML
    private TextArea Feedback;

    
    @FXML
    private Label username;
    
    
    @FXML
    private Label rantelID;
    
    
    
    public String getRantelID() {
		return rantelID.getText();
	}



	public void setRantelID(int rantel) {
		rantelID.setText(Integer.toString(rantel));
	}



	public String getCarId() {
		return CarId.getText();
	}



	public void setCarId(int carId) {
		CarId.setText(Integer.toString(carId));
	}



	public TextArea getFeedback() {
		return Feedback;
	}



	public void setFeedback(JFXTextArea feedback) {
		Feedback = feedback;
	}



	public Label getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username.setText(username);
	}



	@FXML
    void Close(ActionEvent event) throws IOException, ClassNotFoundException, NotBoundException {
	
		 Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText(null);
	        alert.setContentText("The car is returned   ");
	        alert.showAndWait();
    	Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/EiffelCorpMenu.fxml"));
        root = loader.load();       
        EiffelCorpMenuController rc = loader.getController() ;  
        rc.setUsername(username.getText()) ; 
        rc.AfficherCar();
        rc.AfficherCarRented() ;  
        username.getScene().setRoot(root)   ;
        
        
    }

    
    
    @FXML
    void PublishFeedback(ActionEvent event) throws IOException, ClassNotFoundException, NotBoundException {

    	 Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText(null);
	        alert.setContentText("The car is returned   ");
	        alert.showAndWait();
    	Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/EiffelCorpMenu.fxml"));
        root = loader.load();       
        EiffelCorpMenuController rc = loader.getController() ;  
        rc.setUsername(username.getText()) ; 
        System.out.println(Feedback.getText());
        rc.returnCar2(Integer.parseInt(this.getRantelID()),Feedback.getText());      
        rc.AfficherCar();
        rc.AfficherCarRented() ;  
        username.getScene().setRoot(root)   ;
    }

}
