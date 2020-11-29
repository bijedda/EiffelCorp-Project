package employees;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import fleet.IFleet;
import fleet.IVehicle;
import rental.IRentalManagement;

public class Employee extends UnicastRemoteObject implements IEmployee  {
	
	private static final long serialVersionUID = 1L;
	
	long id;
	String email;
	String firstname;
	String lastname;
	String username;
	String address;
	long phoneNumber;
	Date dateofbirth;
	String password;
	List <String> hiredvehicules;
	
	@Override
	public String toString() {
		return "Employee [email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", address=" + address + ", phoneNumber=" + phoneNumber + ", dateofbirth=" + dateofbirth
				+ ", password=" + password + ", hiredvehicules=" + hiredvehicules + "]";
	}

	public Employee() throws RemoteException{
		super();
		hiredvehicules = new ArrayList();
	}

	public long getId() throws RemoteException {
		return id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee(long id, String email, String firstname, String lastname, String username, String address,
			long phoneNumber, Date dateofbirth, String password) throws RemoteException {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dateofbirth = dateofbirth;
		this.password = password;
		this.hiredvehicules = new ArrayList();
	}

	public List<String> getHiredvehicules() {
		return hiredvehicules;
	}

	public void setHiredvehicules(List<String> hiredvehicules) {
		this.hiredvehicules = hiredvehicules;
	}
	
	
	

	@Override
	public void update(IVehicle v, IFleet f, IRentalManagement rentalManager) throws ClassNotFoundException, IOException {
		System.out.println("the car is now available!" );
		System.out.println("Renting to the first customer waiting ..." );
	}


	
	public IEmployee searchUsername(String username) throws RemoteException{
			Employee  foundEmployee = new Employee();
			GestionEmployees listemp = new GestionEmployees() ; 
			ArrayList<Employee> listEmployee = new ArrayList<Employee>();
			listEmployee = (ArrayList<Employee>) listemp.getListEmployee();
			
			for (Employee emp : listEmployee) {
		        if (emp.getUsername().equals(username)) {
		        	foundEmployee = emp;
		        }
		    }
			return (IEmployee)foundEmployee;
	}
	
	

}
