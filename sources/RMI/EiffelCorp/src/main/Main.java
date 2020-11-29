package main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import fleet.IFleet;




public class Main extends Application {
	
    private Stage stage;
    private Parent parent;
	@Override
	public void start(Stage primaryStage) throws IOException {
			parent=FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
			Scene scene = new Scene(parent,480,720);
			scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
			primaryStage.setTitle("Application");
			primaryStage.setScene(scene);
			primaryStage.show();	
	}

	
	public static void main(String[] args)  {
		
		launch(args);
	}
}
