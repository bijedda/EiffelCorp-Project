package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IRental extends Remote {

	public int getRentalID() throws RemoteException;

	public void setRentalID(int rentalID) throws RemoteException;

	public String getVehiculeRgNb() throws RemoteException;

	public void setVehiculeRgNb(String vehiculeRgNb) throws RemoteException;

	public int getCustomerNb() throws RemoteException;

	public void setCustomerNb(int customerNb) throws RemoteException;

	public double getCostPerDay() throws RemoteException;

	public void setCostPerDay(double costPerDay) throws RemoteException;

	public Date getDateDebut() throws RemoteException;

	public void setDateDebut(Date dateDebut) throws RemoteException;

}
