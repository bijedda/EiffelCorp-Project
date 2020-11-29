package employees;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import fleet.IVehicle;


public class GestionEmployees {
	
	List <Employee> listEmployees;
	
	
	public GestionEmployees(){
		super();
		listEmployees = new ArrayList<Employee>();
	}
	
	
	public Boolean addEmployee(Employee emp) {
		
		String Texte = "E:\\M2 SIA\\Web services\\Employee.txt";
		ArrayList<Employee> listemployee = (ArrayList<Employee>) this.getListEmployee();
		try {
			FileOutputStream fileOut = new FileOutputStream(Texte);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			listemployee.add(emp);
			out.writeObject(listemployee);
			out.close();
			fileOut.close();
			return true;
			}
			catch(Exception e) {
			 return false;
			} 
	
	}
	

	
	
	@SuppressWarnings("unchecked")
	ArrayList<Employee> getListEmployee() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		String Texte = "E:\\M2 SIA\\Web services\\Employee.txt";
		ArrayList<Employee> listemployee = new ArrayList<Employee>();
		try {
			fis = new FileInputStream(Texte);
			ois = new ObjectInputStream(fis);
			listemployee = (ArrayList<Employee>) ois.readObject();

		} catch (FileNotFoundException fnfe) {
			System.out.println("Could not find file");
			fnfe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("File format is wrong ");
			cnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println(ioe);
		} finally {
			if (fis != null) {
				safeClose(fis);
				return listemployee;
			}
		}
		return listemployee;
	}
	
	
	
	private void safeClose(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}


	Boolean deleteEmployee(Employee emp) {
		
		try {
			for (int i=0 ; i <= listEmployees.size();i++) {
				if (listEmployees.get(i).getId() == emp.getId() ) {
					listEmployees.remove(i);
					return true;
				}
			}
			return true;
			}
		catch(Exception e) {
			 return false;
			} 	
	}
	
	
	
	
	public Employee searchUsername(String username) throws RemoteException{
			Employee  foundEmployee = new Employee();
			
	
			ArrayList<Employee> listEmployee = new ArrayList<Employee>();

			listEmployee = (ArrayList<Employee>) this.getListEmployee();
			
			for (Employee emp : listEmployee) {
		        if (emp.getUsername().equals(username)) {
		        	foundEmployee = emp;
		        }
		    }
			return foundEmployee;
	}
	

	
	
	List<Employee> searchAddress(String address){
		List<Employee>foundEmployees = new ArrayList<Employee>();
		for (Employee emp : listEmployees) {
	        if (emp.getUsername().equals(address)) {
	        	foundEmployees.add(emp);
	        }
	    }
		return foundEmployees;
}

}
