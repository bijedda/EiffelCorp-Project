package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import employees.IEmployee;
import fleet.IFleet;
import fleet.IVehicle;

public interface IRentalManagement extends Remote {
	
	public List<IRental> getListRentals() throws RemoteException;
	public void rentCar(IFleet fleet, int rentalID, String vehiculeRgNb, int customerNb, double costPerDay, Date dateDebut) throws RemoteException;
	public void returnCar(IFleet fleet, int rentalID) throws RemoteException;
	public List<IRental> searchById(int rentalID) throws RemoteException;
	public List<IRental> searchByVehicle(String vehiculeRgNb) throws RemoteException;
	public List<IRental> searchByCustomer(int customerNb) throws RemoteException;
	public List<IVehicle> getVehiclesRentedOnce() throws RemoteException;
	
	public List<IVehicle> getVehiclesRentedOnce(IFleet fleet) throws RemoteException;

}
