package fleet;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import employees.IEmployee;

public class Fleet extends UnicastRemoteObject implements IFleet {

	public List<Vehicle> listVehicles;

	public Fleet() throws RemoteException {
		super();
		listVehicles = new ArrayList<Vehicle>();
	}


	public List<IVehicle> getListVehicles() throws IOException, ClassNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		String Texte = "D:\\Voiture.txt";
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		try {
			fis = new FileInputStream(Texte);
			ois = new ObjectInputStream(fis);

			listVehicless = (ArrayList<IVehicle>) ois.readObject();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Could not find file");
			fnfe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("File format is wrong ");
			cnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("The file is empty ");
		} finally {
			if (fis != null) {
				safeClose(fis);
				return listVehicless;
			}
		}
		return listVehicless;

	}

	
	
	
	
	private void safeClose(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
	

	@Override
	public void add(String registrationNb, String model, String make, double topSpeed, String fuelType, int nbDoors)
			throws IOException, ClassNotFoundException {

		Vehicle b = new Vehicle(registrationNb, model, make, topSpeed, fuelType, nbDoors);
		String Texte = "D:\\Voiture.txt";
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();

		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		listVehicless.add(b);
		out.writeObject(listVehicless);
		out.close();
		fileOut.close();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();

		System.out.println("Car added");
	}

	@Override
	public void addVehiculeObject(IVehicle b) throws IOException, ClassNotFoundException {
		String Texte = "D:\\Voiture.txt";
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();

		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		listVehicless.add(b);
		out.writeObject(listVehicless);
		out.close();
		fileOut.close();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();
		System.out.println("Car added");

	}

	
	@Override
	public void modify(String registrationNb, String model, String make, double topSpeed, String fuelType, int nbDoors)
			throws IOException, ClassNotFoundException {

		String Texte = "D:\\Voiture.txt";
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) getListVehicles();
		for (int i = 0; i < listVehicless.size(); i++) {
				
			if (listVehicless.get(i).getRegistrationNb().equals(registrationNb)) {
				listVehicless.get(i).setModel(model);
				listVehicless.get(i).setMake(make);
				listVehicless.get(i).setTopSpeed(topSpeed);
				listVehicless.get(i).setFuelType(fuelType);
				listVehicless.get(i).setNbDoors(nbDoors);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listVehicless);
		out.close();
		fileOut.close();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();

		System.out.println("Car added");
	}

	// ***********************************************************************************
	@Override
	public void delete(String registrationNb) throws ClassNotFoundException, IOException {

		String Texte = "D:\\Voiture.txt";
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();

		for (int i = 0; i < listVehicless.size(); i++) {
			if (listVehicless.get(i).getRegistrationNb().equals(registrationNb)) {
				listVehicless.remove(i);
			}
		}
		FileOutputStream fileOut = new FileOutputStream(Texte);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listVehicless);
		out.close();
		fileOut.close();

	}

	@Override
	public List<IVehicle> searchByRgNb(String registrationNb) throws IOException, ClassNotFoundException {
		ArrayList<IVehicle> vehiclesFound = new ArrayList<IVehicle>();
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();
		for (IVehicle b : listVehicless) {
			if (b.getRegistrationNb().equals(registrationNb)) {
				vehiclesFound.add(b);
			}
		}
		return vehiclesFound;

	}

	@Override
	public int searchByRgNbByIndc(String registrationNb) throws IOException, ClassNotFoundException {
		ArrayList<IVehicle> vehiclesFound = new ArrayList<IVehicle>();
		int j = 0;
		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();
		for (int i = 0; i < listVehicless.size(); i++) {
			if (listVehicless.get(i).getRegistrationNb().equals(registrationNb)) {
				vehiclesFound.add(listVehicless.get(i));
				j = i;
			}
		}
		return j;

	}

	@Override
	public List<IVehicle> searchByModel(String Model) throws RemoteException, IOException, ClassNotFoundException {
		List vehiclesFound = new ArrayList<IVehicle>();

		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();
		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();
		for (IVehicle b : listVehicless) {
			if (b.getModel().equals(Model)) {
				vehiclesFound.add(b);
			}
		}
		return vehiclesFound;

	}

	@Override
	public List<IVehicle> searchByMake(String Make) throws RemoteException, IOException, ClassNotFoundException {
		ArrayList<IVehicle> vehiclesFound = new ArrayList<IVehicle>();

		ArrayList<IVehicle> listVehicless = new ArrayList<IVehicle>();

		listVehicless = (ArrayList<IVehicle>) this.getListVehicles();

		for (IVehicle b : listVehicless) {
			if (b.getMake().equals(Make)) {
				vehiclesFound.add(b);
			}
		}
		return vehiclesFound;

	}

	@Override
	public void setListVehicles(List<IVehicle> listVehicles) throws RemoteException {


	}

}
