package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import employees.IEmployee;
import fleet.IFleet;
import fleet.IVehicle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.StackPane;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;


public class IfsCarMenuController implements Initializable {
    
    
	
    
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private Text username;
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
    private TableColumn<IVehicle, Boolean> rented;

    @FXML
    private TableColumn<IVehicle, Queue<Integer>> waitingQueue;

    @FXML
    private TableColumn<IVehicle,ArrayList<IEmployee>> observers;
    
    @FXML
    private Button modify;
    
    
    @FXML
    private Button confirmModification;
    
    @FXML
    private Button add;
    
    
    
    public TableColumn<IVehicle, List<Integer>> getHiredBy() {
		return hiredBy;
	}




	public TableColumn<IVehicle, Boolean> getRented() {
		return rented;
	}


	public void setRented(TableColumn<IVehicle, Boolean> rented) {
		this.rented = rented;
	}





	@FXML
    private AnchorPane anchroAjoutmodif;
    @FXML
    private TextField registrationNbT;

    @FXML
    private TextField modelT;

    @FXML
    private TextField makeT;

    @FXML
    private TextField topSpeedT;

    @FXML
    private TextField fuelTypeT;

    @FXML
    private TextField nbDoorsT;


    
    public TextField getRegistrationNbT() {
		return registrationNbT;
	}


	public void setRegistrationNbT(String string) {
		this.registrationNbT.setText(string);
	}


	public TextField getModelT() {
		return modelT;
	}


	public void setModelT(String string) {
		this.modelT.setText(string);
	}


	public TextField getMakeT() {
		return makeT;
	}


	public void setMakeT(String string) {
		this.makeT.setText(string);
	}


	public TextField getTopSpeedT() {
		return topSpeedT;
	}


	public void setTopSpeedT(String string) {
		this.topSpeedT.setText(string);
	}


	public TextField getFuelTypeT() {
		return fuelTypeT;
	}


	public void setFuelTypeT(String string) {
		this.fuelTypeT.setText(string);
	}


	public TextField getNbDoorsT() {
		return nbDoorsT;
	}


	public void setNbDoorsT(String string) {
		this.nbDoorsT.setText(string);
	}


	public void setUsername(Text username) {
		this.username = username;
	}


	/**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    public StackPane getRootPane() {
        return rootPane;
    }



    public void setRootPane(StackPane rootPane) {
        this.rootPane = rootPane;
    }



    public AnchorPane getRootAnchorPane() {
        return rootAnchorPane;
    }



    public void setRootAnchorPane(AnchorPane rootAnchorPane) {
        this.rootAnchorPane = rootAnchorPane;
    }



    public Text getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username.setText(username) ;
    }



    public TableView getAfficheCars() {
        return afficheCars;
    }



    public void setAfficheCars(TableView afficheCars) {
        this.afficheCars = afficheCars;
    }



    public TableColumn getRegistrationNb() {
        return registrationNb;
    }



    public void setRegistrationNb(TableColumn registrationNb) {
        this.registrationNb = registrationNb;
    }



    public TableColumn getModel() {
        return model;
    }



    public void setModel(TableColumn model) {
        this.model = model;
    }



    public TableColumn getMake() {
        return make;
    }



    public void setMake(TableColumn make) {
        this.make = make;
    }



    public TableColumn getTopSpeed() {
        return topSpeed;
    }



    public void setTopSpeed(TableColumn topSpeed) {
        this.topSpeed = topSpeed;
    }



    public TableColumn getFuelType() {
        return fuelType;
    }



    public void setFuelType(TableColumn fuelType) {
        this.fuelType = fuelType;
    }



    public TableColumn getNbDoors() {
        return nbDoors;
    }



    public void setNbDoors(TableColumn nbDoors) {
        this.nbDoors = nbDoors;
    }



    public AnchorPane getAnchroAjoutmodif() {
        return anchroAjoutmodif;
    }



    public void setAnchroAjoutmodif(AnchorPane anchroAjoutmodif) {
        this.anchroAjoutmodif = anchroAjoutmodif;
    }
    
    
    
    
    
    
    public Button getModify() {
		return modify;
	}




	public void setModify(Button modify) {
		this.modify = modify;
	}




	public Button getConfirmModification() {
		return confirmModification;
	}




	public void setConfirmModification() {
		this.confirmModification.setVisible(false);;
	}




	@FXML
    private void AjouterVoiture (ActionEvent event) throws MalformedURLException, RemoteException, NotBoundException, IOException, NumberFormatException, ClassNotFoundException {
    	IFleet stub = (IFleet) Naming.lookup("FleetService");
    	
    	
    	 if ( registrationNbT.getText().trim().isEmpty() || (modelT.getText()).trim().isEmpty()
                 || (makeT.getText()).trim().isEmpty() ||(topSpeedT.getText()).trim().isEmpty() 
                 ||(fuelTypeT.getText()).trim().isEmpty()||(nbDoorsT.getText()).trim().isEmpty())
               
         { 
                 Alert alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("you must fill the field !");
                 alert.showAndWait();
         }
    	 
    	 
         else  if(topSpeedT.getText().matches("[0-9]*")== false )
         {
                Alert alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("All field must contain only numbers !");
                 alert.showAndWait();
         }
    	 
         else  if(nbDoorsT.getText().matches("[0-9]*")== false )
         {
                Alert alert = new Alert(AlertType.ERROR);
                 alert.setTitle("Attention");
                 alert.setContentText("All field must contain only numbers !");
                 alert.showAndWait();
         }
   	
         else {
    	stub.add(registrationNbT.getText(), modelT.getText(), makeT.getText(),Double.parseDouble(topSpeedT.getText()), fuelTypeT.getText(), Integer.parseInt(nbDoorsT.getText()));       
    	registrationNbT.clear();
    	modelT.clear() ; 
    	makeT.clear() ; 
    	topSpeedT.clear();
    	fuelTypeT.clear();
    	nbDoorsT.clear();
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Well added ");
        alert.showAndWait();	
        AfficherCar() ; }
    } 
    

    
    
    @SuppressWarnings("rawtypes")
	public void AfficherCar() throws MalformedURLException, RemoteException, NotBoundException, FileNotFoundException, IOException, ClassNotFoundException  {
    	
         ObservableList<IVehicle> list = FXCollections.observableArrayList();
         IFleet stub = (IFleet) Naming.lookup("FleetService"); 
         List<IVehicle> v = stub.getListVehicles() ; 
             if (v != null && v.size() != 0) 
             {
            	 for (int i = 0; i < v.size() ; i++) 
            		  list.add(v.get(i)) ; 
          
            	 registrationNb.setCellValueFactory(param1 -> 
				 {
					try {
						return new ReadOnlyStringWrapper(param1.getValue().getRegistrationNb() );
					} catch (RemoteException e) {
						System.out.println(e);
					}
					return null;
				});
            	   
            	 
            	 model.setCellValueFactory(param1 -> 
				 {
					try {
						return new ReadOnlyStringWrapper(param1.getValue().getModel() );
					} catch (RemoteException e) {
						System.out.println(e);
					}
					return null;
				 });
            	   
            	 make.setCellValueFactory(param1 -> 
				 {
					try {
						return new ReadOnlyStringWrapper(param1.getValue().getMake() );
					} catch (RemoteException e) {
						System.out.println(e);
					}
					return null;
				});
	 
            	 
            	 topSpeed.setCellValueFactory(param1 -> 
				 {
					try {
						return new ReadOnlyObjectWrapper(param1.getValue().getTopSpeed() );
					} catch (RemoteException e) {
						System.out.println(e);
					}
					return null;
				});
            	 
            	 
            	 
            	 
            	 fuelType.setCellValueFactory(param1 -> 
				 {
					try {
						return new ReadOnlyStringWrapper(param1.getValue().getFuelType() );
					} catch (RemoteException e) {
						System.out.println(e);
					}
					return null;
				});
            	 
            	 
            	 
            	 nbDoors.setCellValueFactory(param1 -> 
				 {
					try {
						return new ReadOnlyObjectWrapper(param1.getValue().getNbDoors() );
					} catch (RemoteException e) {
						System.out.println(e);
					}
					return null;
				});
            	 
         
            	    rented.setCellValueFactory(param -> { 
            	        final IVehicle car = param.getValue(); 
            	        try {
							return new SimpleObjectProperty<>(car.isRented());
						} catch (RemoteException e) {
							System.out.println(e);
						}
						return null; 
            	    });
            	    
            	    afficheCars.setItems(list);
             } else {
                 System.out.println("The list is empty ");
             }
}
    

    @FXML
    void delete(ActionEvent event) throws RemoteException, ClassNotFoundException, IOException, NotBoundException {
    	
    	IFleet stub = (IFleet) Naming.lookup("FleetService"); 
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation ");
        alert.setContentText("Are you sure you want to delete the car  ?");
        Optional<ButtonType> result = alert.showAndWait();       
        {
        	stub.delete(afficheCars.getSelectionModel().getSelectedItem().getRegistrationNb());
        	afficheCars.getItems().removeAll(afficheCars.getSelectionModel().getSelectedItem());        	
        }
        }
    	

    
    @FXML
    void modify(ActionEvent event) throws RemoteException {
        
    	this.setRegistrationNbT(afficheCars.getSelectionModel().getSelectedItem().getRegistrationNb());
    	this.setFuelTypeT(afficheCars.getSelectionModel().getSelectedItem().getFuelType());
    	this.setMakeT(afficheCars.getSelectionModel().getSelectedItem().getMake()); 
    	this.setModelT(afficheCars.getSelectionModel().getSelectedItem().getModel());
    	this.setNbDoorsT(Integer.toString(afficheCars.getSelectionModel().getSelectedItem().getNbDoors()));
    	this.setTopSpeedT(Double.toString(afficheCars.getSelectionModel().getSelectedItem().getTopSpeed()));

    	registrationNbT.setEditable(false);
    	add.setVisible(false);
    	confirmModification.setVisible(true);

    }
    
    
    
    @FXML
    void confirmModification(ActionEvent event) throws NumberFormatException, ClassNotFoundException, IOException, NotBoundException {
    	IFleet stub = (IFleet) Naming.lookup("FleetService");
    	

   	stub.modify(registrationNbT.getText(), modelT.getText(), makeT.getText(),Double.parseDouble(topSpeedT.getText()), fuelTypeT.getText(), Integer.parseInt(nbDoorsT.getText()));       
   	registrationNbT.clear();
   	modelT.clear() ; 
   	makeT.clear() ; 
   	topSpeedT.clear();
   	fuelTypeT.clear();
   	nbDoorsT.clear();
   	Alert alert = new Alert(AlertType.INFORMATION);
       alert.setTitle("Information Dialog");
       alert.setHeaderText(null);
       alert.setContentText("Well modified ");
       alert.showAndWait();	
       
       confirmModification.setVisible(false);
       add.setVisible(true);
       registrationNbT.setEditable(true);
       AfficherCar() ; 
 
    }


    // Event Listener on FontAwesomeIconView.onMouseClicked
    @FXML
    public void logout(MouseEvent event) {
        try {
              FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
              Parent root ;
                      root = loader.load();
                      AccueilController rc = loader.getController() ;
                      username.getScene().setRoot(root);
            } catch (IOException ex) {
              
            }
    }
    
    
    
    @FXML
    void feedback(ActionEvent event) throws IOException, ClassNotFoundException, NotBoundException {
    	
    	Parent root;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/DetailVehicule.fxml"));
        root = loader.load();       
        DetailVehiculeController rc = loader.getController() ;  
        rc.setVeihculeId(afficheCars.getSelectionModel().getSelectedItem().getRegistrationNb()); 
        rc.AfficherFeedback(); 
        username.getScene().setRoot(root)   ;

    }
    
    
    
    
    
    
    
}
