package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import  java.text.SimpleDateFormat;
import java.time.LocalDate;

import employees.Employee;
import employees.GestionEmployees;
import employees.IEmployee;
import fleet.IFleet;
import fleet.IVehicle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import rental.IRental;
import rental.IRentalManagement;

public class EiffelCorpMenuController  implements Initializable {
	@FXML
	private Text username;
	
    /**
     * Initializes the controller class.
     */
	 @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

	public Text getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username.setText(username); 
	} 
	
    @FXML
    private TableView<IVehicle>  afficheCars;
    @FXML
    private TableColumn<IVehicle, String>  registrationNb;
    @FXML
    private TableColumn<IVehicle, String>  model;
    @FXML
    private TableColumn<IVehicle, String>  make;
    @FXML
    private TableColumn<IVehicle,Double>  topSpeed;
    @FXML
    private TableColumn<IVehicle, String>  fuelType;
    @FXML
    private TableColumn<IVehicle, Integer>  nbDoors;
    @FXML
    private TableColumn<IVehicle, List<Integer>> hiredBy;

    @FXML
    private TableColumn<IVehicle, List<String>> rentalFeedback;


    @FXML
    private TableColumn<IVehicle, Queue<Integer>> waitingQueue;

    @FXML
    private TableColumn<IVehicle,ArrayList<IEmployee>> observers;
    
    
    
    
    @FXML
    private TableView<IRental>  afficheCarsR;
    @FXML
    private TableColumn<IRental, Integer> RentelId;

    @FXML
    private TableColumn<IRental,String> vehiculeRgNb;

    @FXML
    private TableColumn<IRental, Double> Cost;
    @FXML
    private TableColumn<IRental, LocalDate> DateR;

    
    
    
    
    
    
    
    @FXML
    void returnCar(ActionEvent event) throws NotBoundException, ClassNotFoundException, IOException
    {
    	Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/Feedback.fxml"));
        root = loader.load();       
        FeedbackController rc = loader.getController() ;  
        rc.setUsername(username.getText()) ; 
        rc.setRantelID(afficheCarsR.getSelectionModel().getSelectedItem().getRentalID());
        username.getScene().setRoot(root)   ;
    }
    
    
    
    void returnCar2 (int id ,String feedback) throws NotBoundException, ClassNotFoundException, IOException 
    {
    	IRentalManagement rentalService = (IRentalManagement) Naming.lookup("RentalManager");
    	IFleet stub = (IFleet) Naming.lookup("FleetService");    
    	rentalService.returnCar(stub,id,feedback);	
    }
 
    


    @SuppressWarnings("deprecation")
	@FXML
    void rentCar(ActionEvent event) throws NotBoundException, NumberFormatException, ClassNotFoundException, IOException 
    {
    	IRentalManagement rentalService = (IRentalManagement) Naming.lookup("RentalManager");
    	IFleet stub = (IFleet) Naming.lookup("FleetService");    	
    	
    	String format = "dd/MM/yy";

    	java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
    	java.util.Date date = new java.util.Date();   	
    	IEmployee emp = new Employee() ;    	
    	 if(rentalService.rentCar(stub, 1002, afficheCars.getSelectionModel().getSelectedItem().getRegistrationNb(),Integer.parseInt(username.getText()), 12.05, date,emp.searchUsername(username.getText())) ) 
    	 {
    		 	Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setTitle("Information Dialog");
    	        alert.setHeaderText(null);
    	        alert.setContentText("location done ");
    	        alert.showAndWait();
    		 
    	 }
    	 else 
    	 {
    		 	Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setTitle("Information Dialog");
    	        alert.setHeaderText(null);
    	        alert.setContentText("The vehicle is not available, you will be notified as soon it will be available! ");
    	        alert.showAndWait();
    	 }
    	AfficherCar() ; 
    	AfficherCarRented() ; 
    }
	


    public void AfficherCar() throws MalformedURLException, RemoteException, NotBoundException, FileNotFoundException, IOException, ClassNotFoundException  
    	{
    	
         ObservableList<IVehicle> list = FXCollections.observableArrayList();    
         IFleet stub = (IFleet) Naming.lookup("FleetService");  
         List<IVehicle> v = stub.getListVehicles() ; 
             if (v != null && v.size() != 0) 
             {
            	 for (int i = 0; i < v.size() ; i++) 
            		  list.add(v.get(i)) ; 
         
                registrationNb.setCellValueFactory(param -> { 
            	        final IVehicle car = (IVehicle) param.getValue(); 
            	        try {
							return new SimpleStringProperty(car.getRegistrationNb());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    
            	   
            	    model.setCellValueFactory(param -> { 
            	        final IVehicle car = param.getValue(); 
            	        try {
							return new SimpleStringProperty(car.getModel());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    make.setCellValueFactory(param -> { 
            	        final IVehicle car = param.getValue(); 
            	        try {
							return new SimpleStringProperty(car.getMake());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    topSpeed.setCellValueFactory(param -> { 
            	        final IVehicle car = param.getValue(); 
            	        try {
							return new SimpleObjectProperty<>(car.getTopSpeed());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	   
            	    
            	    fuelType.setCellValueFactory(param -> { 
            	        final IVehicle car = param.getValue(); 
            	        try {
							return new SimpleStringProperty(car.getFuelType());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	  
            	    nbDoors.setCellValueFactory(param -> { 
            	        final IVehicle car = param.getValue(); 
            	        try {
							return new SimpleObjectProperty<>(car.getNbDoors());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    

            	    afficheCars.setItems(list);

             } else {
                 System.out.println("the list is empty ");
             }
    	}
   
    
    
    
    public void AfficherCarRented() throws MalformedURLException, RemoteException, NotBoundException, FileNotFoundException, IOException, ClassNotFoundException  
    	{
    	
         ObservableList<IRental> list = FXCollections.observableArrayList();
         IRentalManagement rentalService = (IRentalManagement) Naming.lookup("RentalManager");
         List<IRental> v = rentalService.getListRental() ; 
             if (v != null && v.size() != 0) {
            	 for (int i = 0; i < v.size() ; i++) 
            	 	{
            		 if (Integer.toString(v.get(i).getCustomerNb()).equals(username.getText()))
            			 list.add(v.get(i)) ; 
            		}
            	 
            	 	RentelId.setCellValueFactory(param -> { 
            	        final IRental car =  param.getValue(); 
            	        try {
							return new SimpleObjectProperty(car.getRentalID());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    
            	   
            	    vehiculeRgNb.setCellValueFactory(param -> { 
            	        final IRental car = param.getValue(); 
            	        try {
							return new SimpleStringProperty(car.getVehiculeRgNb());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    Cost.setCellValueFactory(param -> { 
            	        final IRental car = param.getValue(); 
            	        try {
							return new SimpleObjectProperty(car.getCostPerDay());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    DateR.setCellValueFactory(param -> { 
            	        final IRental car = param.getValue(); 
            	        try {
							return new SimpleObjectProperty(car.getDateDebut());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    afficheCarsR.setItems(list);
             } 
	}    
    
	
    @FXML
    public void logout(MouseEvent event) 
    {
        try {
              FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
              Parent root ;
                      root = loader.load();
                      AccueilController rc = loader.getController() ;
                      username.getScene().setRoot(root);
            } catch (IOException ex)
        	{
            	System.out.println(ex);
            }
    }

}
