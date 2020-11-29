package fleet;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import employees.Employee;
import employees.IEmployee;
import rental.IRental;
import rental.IRentalManagement;

public class FleetClient {

	public static void main(String[] args) {
try {
			
			IFleet stub = (IFleet) Naming.lookup("FleetService");
			IVehicle stub2 = (IVehicle) Naming.lookup("Vehicle");
			stub.add("161JMT1753", "C4", "Citroen", 180.0, "Essence", 4);
			
			IVehicle v = stub.searchByRgNb("161JMT1753").get(0); 
			System.out.println(v.getRegistrationNb());
			
		/*	stub.add("161JMT1687", "Corsa", "Opel", 150.0, "Essence", 4);
			stub.add("180JMT9348", "500", "Fiat", 180.0, "Mazout", 2);
			 
			
	//		List<IVehicle> vehiclesFound  =  stub.searchByMake("Opel");
			
	//		System.out.println("Le modèle de ce vehicule est: " + vehiclesFound.get(0).getModel());
			
	//		vehiclesFound.get(0).setModel("Astra");
			
	//		vehiclesFound  =  stub.searchByMake("Opel");
	//		System.out.println("Le modèle de ce vehicule est: " + vehiclesFound.get(0).getRegistrationNb());

			/* testing the Rental Management */
			/*	IRentalManagement rentalService = (IRentalManagement) Naming.lookup("RentalManager");
			
			
			String string = "January 2, 2010";
			DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
			Date date = format.parse(string);
			
			IEmployee emp1 = new Employee(15,"talel.jedda@esprit.tn","Talel","Jedda","taleljedda","Champs",9652369,date,"");
			IEmployee emp2 = new Employee(16,"bilel.jedda@esprit.tn","Bilel","Jedda","bileljedda","Champs",9652369,date,"");
			*/
			/*GestionEmployees x = new  GestionEmployees() ; 
	String string = "January 2, 2010";
	DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
	Date date = format.parse(string);
	IEmployee emp1 = new Employee(15,"talel.jedda@esprit.tn","Talel","Jedda","taleljedda","Champs",9652369,date,"");
	IEmployee emp2 = new Employee(16,"bilel.jedda@esprit.tn","Bilel","Jedda","bileljedda","Champs",9652369,date,"");
	
	x.addEmployee((Employee) emp1);
	x.addEmployee((Employee) emp2);*/
			
			
			
			
			
		//	rentalService.rentCar(stub, 1001, "161JMT1753", 15, 12.05, date);
		//	rentalService.rentCar(stub, 1002, "161JMT1753", 16 , 12.05, date);
		
	
			
	
		/*	v.subscribe(emp2);
			
		//	rentalService.returnCar(stub, 1001);	
			
			//rentalService.returnCar(stub, 1002);

	  	System.out.println(rentalService.searchByVehicle("161JMT1753").get(0).getCustomerNb());
	  	System.out.println(rentalService.searchByVehicle("161JMT1753").get(0).getRentalID());	
	  //	rentalService.returnCar(stub, 1002);	  	
	  	List<IVehicle> vlist = rentalService.getVehiclesRentedOnce(stub);
	  	for( IVehicle vehicle: vlist ) {
	  		System.out.println(vehicle.getRegistrationNb());	  		
	  	}
				  	
	  	System.out.println(stub.searchByRgNb("161JMT1753").get(0).getWaitingQueue());	  	
	  	System.out.println(stub.searchByRgNb("161JMT1753").get(0).getHiredBy());	  	
		// registery already created	
		//publier la réference dans l'annuaire:
		Naming.rebind("Fleet", stub);
		Naming.rebind("RentalManagement", rentalService);*/
	  		
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	}
