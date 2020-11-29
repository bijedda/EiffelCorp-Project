package bank;
import java.util.ArrayList;
import java.util.List;

public class BankService {
	
	List<Account> accountsList;

	public BankService() {
		super();
		this.accountsList = new ArrayList<Account>();
		
		this.accountsList.add(new Account(123456789, 50000));
		this.accountsList.add(new Account(987456321, 5862));
		this.accountsList.add(new Account(963258741, 27539));
	
	}
	
	public Account searchByCCN(long numCB) {
		List accountsFound = new ArrayList<Account>();
		for (Account acc: accountsList) {
			if ( acc.getNumCB() == numCB  ) {
				accountsFound.add(acc);
			}		
		}
		return (Account) accountsFound.get(0);		
	}
	
	public boolean faireAchat(long numCB, int montant) {
		
		Account acc = searchByCCN(numCB);
		if(acc.valeurDuSolde() <= montant) {
			System.out.println("Sorry, you don't have enough money");
			return false;
		}
		else {
			acc.retraitDe(montant);
			System.out.println("Transaction effectuée avec succès");
			return true;
			
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
