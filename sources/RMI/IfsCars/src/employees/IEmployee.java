package employees;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fleet.IFleet;
import fleet.IVehicle;
import rental.IRentalManagement;

public interface IEmployee extends Remote {

	// seulement cette méthode sera appelée par la partie Server (IfsCars)
	public void update(IVehicle v, IFleet f, IRentalManagement rentalManager) throws RemoteException;

	public IEmployee searchUsername(String username) throws RemoteException;

}
