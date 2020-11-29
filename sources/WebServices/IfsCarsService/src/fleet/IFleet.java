package fleet;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFleet extends Remote {
	
	public void add(String registrationNb, String model, String make, double topSpeed, String fuelType, int nbDoors) throws RemoteException;
	public void delete(String registrationNb) throws RemoteException;
	//public List<Book> afficher() throws RemoteException;
	public List<IVehicle> searchByRgNb(String registrationNb) throws RemoteException;
	public List<IVehicle> searchByModel(String Model) throws RemoteException;
	public List<IVehicle> searchByMake(String Make) throws RemoteException;

}
