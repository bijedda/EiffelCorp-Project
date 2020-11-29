package fleet;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import rental.RentalManagement;

public class FleetServer {
	public static void main(String[] args) {
		try {
			// création de l'objet distant:
			Fleet fleet = new Fleet();
			Vehicle vehicle = new Vehicle();
			RentalManagement rentalManager = new RentalManagement();
			// démarrer l'annuaire
			LocateRegistry.createRegistry(1099);
			// publier la réference dans l'annuaire:
			Naming.rebind("FleetService", fleet);
			Naming.rebind("RentalManager", rentalManager);
			Naming.rebind("Vehicle", vehicle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
