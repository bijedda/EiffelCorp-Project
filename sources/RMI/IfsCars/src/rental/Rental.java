package rental;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Rental extends UnicastRemoteObject implements IRental {

	private int rentalID;
	private String vehiculeRgNb;
	private int customerNb;
	private double costPerDay;
	private Date dateDebut;

	public Rental() throws RemoteException {
		super();
	}

	public Rental(int rentalID, String vehiculeRgNb, int customerNb, double costPerDay, Date dateDebut)
			throws RemoteException {
		super();
		this.rentalID = rentalID;
		this.vehiculeRgNb = vehiculeRgNb;
		this.customerNb = customerNb;
		this.costPerDay = costPerDay;
		this.dateDebut = dateDebut;
	}

	public int getRentalID() throws RemoteException {
		return rentalID;
	}

	public void setRentalID(int rentalID) throws RemoteException {
		this.rentalID = rentalID;
	}

	public String getVehiculeRgNb() throws RemoteException {
		return vehiculeRgNb;
	}

	public void setVehiculeRgNb(String vehiculeRgNb) throws RemoteException {
		this.vehiculeRgNb = vehiculeRgNb;
	}

	public int getCustomerNb() throws RemoteException {
		return customerNb;
	}

	public void setCustomerNb(int customerNb) throws RemoteException {
		this.customerNb = customerNb;
	}

	public double getCostPerDay() throws RemoteException {
		return costPerDay;
	}

	public void setCostPerDay(double costPerDay) throws RemoteException {
		this.costPerDay = costPerDay;
	}

	public Date getDateDebut() throws RemoteException {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) throws RemoteException {
		this.dateDebut = dateDebut;
	}

	@Override
	public String toString() {
		return "Rental [rentalID=" + rentalID + ", vehiculeRgNb=" + vehiculeRgNb + ", customerNb=" + customerNb
				+ ", costPerDay=" + costPerDay + ", dateDebut=" + dateDebut + "]";
	}

}
