package service;

import java.util.List;

import fleet.IVehicle;

public class VehicleToSale {
	
	private String registrationNb;
	private String model;
	private String make;
	private double topSpeed;
	private String fuelType;
	private int nbDoors; 
	private boolean rented;
	double price;
	
	public VehicleToSale() {
		super();

	}

	public VehicleToSale(String registrationNb, String model, String make, double topSpeed, String fuelType,
			int nbDoors, boolean rented, double price) {
		super();
		this.registrationNb = registrationNb;
		this.model = model;
		this.make = make;
		this.topSpeed = topSpeed;
		this.fuelType = fuelType;
		this.nbDoors = nbDoors;
		this.rented = rented;
		this.price = price;
	}

	public String getRegistrationNb() {
		return registrationNb;
	}

	public void setRegistrationNb(String registrationNb) {
		this.registrationNb = registrationNb;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(double topSpeed) {
		this.topSpeed = topSpeed;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getNbDoors() {
		return nbDoors;
	}

	public void setNbDoors(int nbDoors) {
		this.nbDoors = nbDoors;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
