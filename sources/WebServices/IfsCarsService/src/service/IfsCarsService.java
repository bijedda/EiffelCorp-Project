package service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;
import bank.BankService;
import bank.BankServiceServiceLocator;
import bank.BankServiceSoapBindingStub;
import fleet.IFleet;
import fleet.IVehicle;
import rental.IRentalManagement;

public class IfsCarsService {
		
	List<VehicleToSale> Basket = new ArrayList<VehicleToSale>();

	public VehicleToSale[] getVehicleRentedOnce() 
	{
		
		List<IVehicle> vlist = new ArrayList<IVehicle>();	
		try
		{
			IRentalManagement rentalService = (IRentalManagement) Naming.lookup("RentalManagement");
			IFleet fleet = (IFleet) Naming.lookup("Fleet");

			vlist = rentalService.getVehiclesRentedOnce(fleet);
			VehicleToSale[] vehiclesTab = new VehicleToSale[vlist.size()]; 
			
			 for(int i=0;i<vlist.size();i++) 
			 {
			 double price = (vlist.get(i).getTopSpeed() + vlist.get(i).getNbDoors()) *0.5  + 10000 ;
			 vehiclesTab[i] = new VehicleToSale(
					vlist.get(i).getRegistrationNb(),
					vlist.get(i).getModel(),
					vlist.get(i).getMake(),
					vlist.get(i).getTopSpeed(),
					vlist.get(i).getFuelType(),
					vlist.get(i).getNbDoors(),
					vlist.get(i).isRented(),
					price) ;				 
			}						
			 return vehiclesTab ;
			 			
		} catch (Exception e)
		{
			VehicleToSale[] vehiclesTab = new VehicleToSale[vlist.size()]; 			
			return vehiclesTab ;
		}		
	}
	
	
	public void addToBasket(VehicleToSale v) 
	{			
		try 
		{
			IFleet fleet;
			fleet = (IFleet) Naming.lookup("Fleet");			
			if(fleet.searchByRgNb(v.getRegistrationNb()).get(0).isRented())
				System.out.println("Car already rented !");
			else
			{
				Basket.add(v);
			    System.out.println("Car added to your basket !");
			}							
		} catch (Exception e) 
		{
			System.out.println("Fleet not found in the registry !");
		}		
	}
	
	
	
	public void removeFromBasket(VehicleToSale v)
	{		
		for (int i =0; i< Basket.size(); i++) 
		{
			if (Basket.get(i).getRegistrationNb().equals(v.getRegistrationNb()))
				Basket.remove(Basket.get(i));
		}
		System.out.println("Voiture supprimée du panier!");		
	}
	
	
	
	public VehicleToSale[] consultBasket() 
	{
		if (Basket.size() == 0 ) 
		{
			VehicleToSale[] vehiclesTab = null;
			return vehiclesTab;
		} else
		{
			VehicleToSale[] vehiclesTab = new VehicleToSale[Basket.size()]; 			
			for(int i=0;i<Basket.size();i++) 
				{				 
				 vehiclesTab[i] = new VehicleToSale(
						 Basket.get(i).getRegistrationNb(),
						 Basket.get(i).getModel(),
						 Basket.get(i).getMake(),
						 Basket.get(i).getTopSpeed(),
						 Basket.get(i).getFuelType(),
						 Basket.get(i).getNbDoors(),
						 Basket.get(i).isRented(),
						 Basket.get(i).getPrice()) ;			 
				}
			return vehiclesTab;
		}		
	}
	
	
	
	public boolean purchase(long numCB) throws ServiceException 
	{
		
		BankService bank = new BankServiceServiceLocator().getBankService();
		((BankServiceSoapBindingStub) bank).setMaintainSession(true);		
		int total = 0;
		try 
		{
			IFleet fleet = (IFleet) Naming.lookup("Fleet");			
			for(VehicleToSale v:Basket) 
			{
				total += v.getPrice();
				fleet.delete(v.getRegistrationNb());
			}
				
			if(bank.faireAchat(numCB, total)) 
				{			
					System.out.println("Purchase successfully completed  ");			
					System.out.println("You have " + bank.searchByCCN(numCB).getMontant() + " in your account");			
					return true;
				}
					
			else 
				{			
					System.out.println("Sorry , you don't have enough money in your account");
					return false;
				}
					
		} catch (Exception e) 
		{
			System.out.println("Fleet not found in the registry !");
			e.printStackTrace();
			return false;
		}
						
	}	
}
			
	
