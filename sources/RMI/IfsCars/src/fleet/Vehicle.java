package fleet;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import employees.IEmployee;
import rental.IRental;
import rental.IRentalManagement;

public class Vehicle extends UnicastRemoteObject implements IVehicle {

	private String registrationNb;
	private String model;
	private String make;
	private double topSpeed;
	private String fuelType;
	private int nbDoors;
	private List<Integer> hiredBy;
	private List<String> rentalFeedback;

	private boolean rented = false;
	private Queue<Integer> waitingQueue;

	private ArrayList<IEmployee> observers;

	public Vehicle() throws RemoteException {
		super();
		hiredBy = new ArrayList<Integer>();

		rentalFeedback = new ArrayList<String>();
		waitingQueue = new LinkedList<Integer>();
		observers = new ArrayList<IEmployee>();
	}

	public Vehicle(String registrationNb, String model, String make, double topSpeed, String fuelType, int nbDoors)
			throws RemoteException {
		super();
		this.registrationNb = registrationNb;
		this.model = model;
		this.make = make;
		this.topSpeed = topSpeed;
		this.fuelType = fuelType;
		this.nbDoors = nbDoors;
		this.rented = false;
		hiredBy = new ArrayList<Integer>();
		rentalFeedback = new ArrayList<String>();
		waitingQueue = new LinkedList<Integer>();
		observers = new ArrayList<IEmployee>();
	}
	

	public String getRegistrationNb() throws RemoteException {
		return registrationNb;
	}

	public String getModel() throws RemoteException {
		return model;
	}

	public void setModel(String model) throws RemoteException {
		this.model = model;
	}

	public String getMake() throws RemoteException {
		return make;
	}

	public void setMake(String make) throws RemoteException {
		this.make = make;
	}

	public double getTopSpeed() throws RemoteException {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) throws RemoteException {
		this.topSpeed = topSpeed;
	}

	public String getFuelType() throws RemoteException {
		return fuelType;
	}

	public void setFuelType(String fuelType) throws RemoteException {
		this.fuelType = fuelType;
	}

	public int getNbDoors() throws RemoteException {
		return nbDoors;
	}

	public void setNbDoors(int nbDoors) throws RemoteException {
		this.nbDoors = nbDoors;
	}

	public List<Integer> getHiredBy() throws RemoteException {
		return hiredBy;
	}

	public void setHiredBy(int customerId) throws RemoteException {
		this.hiredBy.add(customerId);
	}

	public List<String> getRentalFeedback() throws RemoteException {
		return rentalFeedback;
	}

	public boolean isRented() throws RemoteException {
		return rented;
	}

	public void setRented(boolean rented) throws RemoteException {
		this.rented = rented;
	}

	public ArrayList<IEmployee> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<IEmployee> observers) {
		this.observers = observers;
	}

	public void setRegistrationNb(String registrationNb) {
		this.registrationNb = registrationNb;
	}

	public void setHiredBy(List<Integer> hiredBy) {
		this.hiredBy = hiredBy;
	}

	public void setRentalFeedback(List<String> rentalFeedback) {
		this.rentalFeedback = rentalFeedback;
	}

	public void setWaitingQueue(Queue<Integer> waitingQueue) {
		this.waitingQueue = waitingQueue;
	}

	public int RemoveWaitingQueue() {
		return this.waitingQueue.remove();
	}

	@Override
	public List<IRental> setRented(boolean rented, IVehicle v, IFleet f, IRentalManagement rl)
			throws ClassNotFoundException, IOException {
		this.rented = rented;

		IEmployee obs;
		for (int i = 0; i < v.getObservers().size(); i++) {
			try {
				System.out.println("Notifying Observers on change in status of the car");
				obs = v.getObservers().get(i);
				obs.update(v, f, rl);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int lastId;
		String format = "dd/MM/yy";
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
		java.util.Date date = new java.util.Date();
		if (rl.getListRental().size() == 0) {
			lastId = 0;
		} else {
			lastId = rl.getListRental().get(0).getRentalID();
		}
		rl.rentCar(f, lastId + 1, v.getRegistrationNb(), v.getFromWaitingQueue(), 18.85, date, v.getObservers().get(0));
		v.getObservers().remove(0);
		int x = v.RemoveWaitingQueue();
		return rl.getListRental();

	}

	@Override
	public String toString() {
		return "Vehicle [registrationNb=" + registrationNb + ", model=" + model + ", make=" + make + ", topSpeed="
				+ topSpeed + ", fuelType=" + fuelType + ", nbDoors=" + nbDoors + ", rented=" + rented + "]";
	}

	public String toString2() {
		return registrationNb + ", " + model + "," + make + "," + topSpeed + "," + fuelType + "," + nbDoors + ",[],[]"
				+ rented + ",[],[]";
	}

	@Override
	public void addHiredBy(int customerId) throws RemoteException {
		this.hiredBy.add(customerId);

	}

	@Override
	public Queue<Integer> getWaitingQueue() throws RemoteException {
		return waitingQueue;
	}

	@Override
	public void addToWaitingQueue(int customerNb) throws RemoteException {
		this.waitingQueue.add(customerNb);
	}

	@Override
	public int getFromWaitingQueue() throws RemoteException {
		return this.waitingQueue.peek();

	}
	
	
	public void addFeedback(String x) throws RemoteException {
		this.rentalFeedback.add(x) ; 

	}

	@Override
	public synchronized void subscribe(IEmployee emp) throws RemoteException {
		observers.add(emp);

	}

}
