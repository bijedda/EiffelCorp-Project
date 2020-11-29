package fleet;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import employees.IEmployee;
import rental.IRental;
import rental.IRentalManagement;

public interface IVehicle extends Remote {

	public String getRegistrationNb() throws RemoteException;

	public String getModel() throws RemoteException;

	public void setModel(String model) throws RemoteException;

	public String getMake() throws RemoteException;

	public void setMake(String make) throws RemoteException;

	public double getTopSpeed() throws RemoteException;

	public void setTopSpeed(double topSpeed) throws RemoteException;

	public String getFuelType() throws RemoteException;

	public void setFuelType(String fuelType) throws RemoteException;

	public int getNbDoors() throws RemoteException;

	public void setNbDoors(int nbDoors) throws RemoteException;

	public List<Integer> getHiredBy() throws RemoteException;

	public void setHiredBy(int customerId) throws RemoteException;

	public void addHiredBy(int customerId) throws RemoteException;

	public List<String> getRentalFeedback() throws RemoteException;

	public boolean isRented() throws RemoteException;

	public void setRented(boolean rented) throws RemoteException;

	public List<IRental> setRented(boolean rented, IVehicle v, IFleet f, IRentalManagement rl)
			throws RemoteException, ClassNotFoundException, IOException;

	public Queue<Integer> getWaitingQueue() throws RemoteException;

	public void addToWaitingQueue(int customerNb) throws RemoteException;

	public int getFromWaitingQueue() throws RemoteException;

	public void subscribe(IEmployee emp) throws RemoteException;

	public ArrayList<IEmployee> getObservers() throws RemoteException;

	public int RemoveWaitingQueue() throws RemoteException;
	public void addFeedback(String x) throws RemoteException ;

}
