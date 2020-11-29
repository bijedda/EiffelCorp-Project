package rental;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import employees.IEmployee;
import fleet.IFleet;
import fleet.IVehicle;

public interface IRentalManagement extends Remote {

	public List<IRental> getListRentals() throws RemoteException;

	public boolean rentCar(IFleet fleet, int rentalID, String vehiculeRgNb, int customerNb, double costPerDay,
			Date dateDebut, IEmployee emp) throws IOException, ClassNotFoundException;

	public void returnCar(IFleet fleet, int rentalID,String x) throws RemoteException, IOException, ClassNotFoundException;

	public List<IRental> searchById(int rentalID) throws RemoteException;

	public List<IRental> searchByVehicle(String vehiculeRgNb) throws RemoteException;

	public List<IRental> searchByCustomer(int customerNb) throws RemoteException;

	public List<IVehicle> getVehiclesRentedOnce(IFleet fleet)
			throws RemoteException, IOException, ClassNotFoundException;

	void addLocation(IRental r) throws IOException, ClassNotFoundException;

	public List<IRental> getListRental() throws IOException, ClassNotFoundException;

	
}
