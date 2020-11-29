package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import fleet.IFleet;
import fleet.IVehicle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class DetailVehiculeController {
	
	@FXML
	private TableView<String> feedback;
	@FXML
	private TableColumn<String, String> FeedbackC;
    @FXML
    private Label VeihculeId;
	
	
	
		
	public String getVeihculeId() {
		return VeihculeId.getText();
	}




	public void setVeihculeId(String veihculeId) {
		VeihculeId.setText(veihculeId);
	}




	// Event Listener on Button.onAction
	@FXML
	public void Close(ActionEvent event) throws IOException, ClassNotFoundException, NotBoundException {
		Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/IfsCarMenu.fxml"));
        root = loader.load();       
        IfsCarMenuController rc = loader.getController() ;          
        rc.setUsername("Admin") ; 
        rc.AfficherCar();     
        feedback.getScene().setRoot(root)   ;
	}
	
	
	
	  
    @SuppressWarnings("rawtypes")
	public void AfficherFeedback() throws MalformedURLException, RemoteException, NotBoundException, FileNotFoundException, IOException, ClassNotFoundException  
    {
    	
         
         IFleet stub = (IFleet) Naming.lookup("FleetService"); 
 
         List<String> rentalFeedback = new ArrayList<String>() ;         
         List<IVehicle> v  = stub.getListVehicles() ; 
      
             	if (v != null && v.size() != 0) 
             	{

	            	 for (int i = 0; i < v.size() ; i++) 
	            	 	{
	            		 	if(v.get(i).getRegistrationNb().equals(getVeihculeId()))
	            		 		rentalFeedback = v.get(i).getRentalFeedback() ; 	            		 
            	}
         
            	 
            	 
	            	 if (rentalFeedback != null && rentalFeedback.size() != 0)
	            	 {  
	            		 ObservableList<String> list = FXCollections.observableArrayList(rentalFeedback);
	                     feedback.getColumns().addAll(FeedbackC);
	            	     FeedbackC.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
	            		 feedback.setItems(list);
	            	 }

             	} else
             	{
                 System.out.println("The list is empty ");
             	}
    }
	
	
}
