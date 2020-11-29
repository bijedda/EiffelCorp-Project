package rental;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import employees.IEmployee;
import fleet.IFleet;
import fleet.IVehicle;
import fleet.Vehicle;

public class RentalManagement extends UnicastRemoteObject implements IRentalManagement {

	public List<IRental> listRentals;

	public RentalManagement() throws RemoteException {
		super();
		this.listRentals = new ArrayList<IRental>();
	}

	public List<IRental> getListRentals() throws RemoteException {
		return listRentals;
	}

	@SuppressWarnings("finally")
	public List<IRental> getListRental() throws IOException, ClassNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		String Texte = "D:\\RentalCar.txt";
		ArrayList<IRental> listRental = new ArrayList<IRental>();
		try {
			fis = new FileInputStream(Texte);
			ois = new ObjectInputStream(fis);

			listRental = (ArrayList<IRental>) ois.readObject();
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("Could not find file");
			fnfe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("File format is wrong :(");
			cnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("The file is empty ");
		} finally {
			if (fis != null) {
				safeClose(fis);
				return listRental;
			}
		}
		return listRental;

	}

	private void safeClose(Closeable closeable) {
		
		try {
			closeable.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	@Override
	public void addLocation(IRental r) throws IOException, ClassNotFoundException {

		String Texte = "D:\\RentalCar.txt";
		ArrayList<IRental> listRental = new ArrayList<IRental>();
		listRental = (ArrayList<IRental>) this.getListRental();
		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		listRental.add(r);
		out.writeObject(listRental);
		out.close();
		fileOut.close();

	}

	@Override
	public boolean rentCar(IFleet fleet, int rentalID, String vehiculeRgNb, int customerNb, double costPerDay,
			Date dateDebut, IEmployee emp) throws IOException, ClassNotFoundException {

		List<IVehicle> v = fleet.getListVehicles();

		boolean x;
		if (fleet.searchByRgNb(vehiculeRgNb).get(0).isRented() == false) 
		{
			x = true;
			IRental r = new Rental(rentalID, vehiculeRgNb, customerNb, costPerDay, dateDebut);
			addLocation(r);
			System.out.println("location done ");

			for (int i = 0; i < v.size(); i++) 
			{
				if (v.get(i).getRegistrationNb().equals(vehiculeRgNb)) 
					v.get(i).setRented(true);
			}
			Naming.rebind("Fleet", fleet);
			Naming.rebind("RentalManagement", this);
		}

		else 
		{
			x = false;
			for (int i = 0; i < v.size(); i++) 
			{
				if (v.get(i).getRegistrationNb().equals(vehiculeRgNb)) 
				{
					v.get(i).addToWaitingQueue(customerNb);
					v.get(i).subscribe(emp);
				}
			}
			System.out.println("The vehicle is not available, you will be notified as soon it will be available!");
		}

		String Texte = "D:\\Voiture.txt";
		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(v);
		out.close();
		fileOut.close();
		return x;

	}

	@Override
	public void returnCar(IFleet fleet, int rentalID,String feedback) throws IOException, ClassNotFoundException {

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		int x = 0;
		ArrayList<IRental> listRental = new ArrayList<IRental>();
		listRental = (ArrayList<IRental>) this.getListRental();
		List<IVehicle> v = fleet.getListVehicles();

		for (int i = 0; i < listRental.size(); i++) 
		{

			if (listRental.get(i).getRentalID() == rentalID) 
			{
				x = i;
				int j = fleet.searchByRgNbByIndc(listRental.get(x).getVehiculeRgNb());

				if (v.get(j).getWaitingQueue().isEmpty()) 
					{
						v.get(j).addHiredBy(listRental.get(i).getCustomerNb());
						v.get(j).setRented(false);
						listRental.remove(listRental.get(x));
						String Texte2 = "D:\\RentalCar.txt";
						FileOutputStream fileOut2 = new FileOutputStream(Texte2);
						ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
						out2.writeObject(listRental);
						out2.close();
						fileOut2.close();
						v.get(j).addFeedback(feedback);
					}

				else 
					{
						v.get(j).addHiredBy(listRental.get(x).getCustomerNb());
						listRental.remove(listRental.get(x));

						String Texte2 = "D:\\RentalCar.txt";
						FileOutputStream fileOut2 = new FileOutputStream(Texte2);
						ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
						out2.writeObject(listRental);
						out2.close();
						fileOut2.close();					
						v.get(j).addFeedback(feedback);						
						listRental = (ArrayList<IRental>) v.get(j).setRented(false, v.get(j), fleet, this);

					}
			}
		}

		String Texte = "D:\\Voiture.txt";
		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(v);
		out.close();
		fileOut.close();
		
		Naming.rebind("Fleet", fleet);
		Naming.rebind("RentalManagement", this);

	}

	@Override
	public List<IRental> searchById(int rentalID) throws RemoteException 
	{
		List rentalsFound = new ArrayList<IRental>();
		for (IRental r : listRentals) 
		{
			if (r.getRentalID() == rentalID) 
				rentalsFound.add(r);
		}
		return rentalsFound;
	}

	@Override
	public List<IRental> searchByVehicle(String vehiculeRgNb) throws RemoteException 
	{
		List rentalsFound = new ArrayList<IRental>();
		for (IRental r : listRentals) 
		{
			if (r.getVehiculeRgNb().equals(vehiculeRgNb)) 
				rentalsFound.add(r);
		}
		return rentalsFound;
	}

	@Override
	public List<IRental> searchByCustomer(int customerNb) throws RemoteException 
	{
		List rentalsFound = new ArrayList<IRental>();
		for (IRental r : listRentals) 
		{
			if (r.getCustomerNb() == customerNb) 
				rentalsFound.add(r);
		}
		return rentalsFound;
	}

	@Override
	public List<IVehicle> getVehiclesRentedOnce(IFleet fleet) throws IOException, ClassNotFoundException 
	{
		List<IVehicle> vehiclesFound = new ArrayList<IVehicle>();
		List<IVehicle> listCehicules = fleet.getListVehicles();
		
		for (IVehicle r : listCehicules) 
		{			
			int nbRented = r.getHiredBy().size();
			if(nbRented >= 1)
				vehiclesFound.add(r);	
		}
		return vehiclesFound;
	}

}
