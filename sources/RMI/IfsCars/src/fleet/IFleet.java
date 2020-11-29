package fleet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFleet extends Remote {

	public void add(String registrationNb, String model, String make, double topSpeed, String fuelType, int nbDoors)
			throws RemoteException, IOException, ClassNotFoundException;

	public void delete(String registrationNb) throws RemoteException, ClassNotFoundException, IOException;

	// public List<Book> afficher() throws RemoteException;
	public List<IVehicle> getListVehicles()
			throws RemoteException, FileNotFoundException, IOException, ClassNotFoundException;

	public void setListVehicles(List<IVehicle> listVehicles) throws RemoteException;

	public List<IVehicle> searchByRgNb(String registrationNb)			throws RemoteException, IOException, ClassNotFoundException;

	public List<IVehicle> searchByModel(String Model) throws RemoteException, IOException, ClassNotFoundException;

	public List<IVehicle> searchByMake(String Make) throws RemoteException, IOException, ClassNotFoundException;

	void addVehiculeObject(IVehicle b) throws IOException, ClassNotFoundException;

	int searchByRgNbByIndc(String registrationNb) throws IOException, ClassNotFoundException;

	void modify(String registrationNb, String model, String make, double topSpeed, String fuelType, int nbDoors)
			throws IOException, ClassNotFoundException;

}
