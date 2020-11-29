package bank;


public class Account {
	
	private long numCB;
	private int montant;
	
	public Account() {
		super();
	}
	
	
	public Account(long numCB, int montant) {
		super();
		this.numCB = numCB;
		this.montant = montant;
	}
	
	public long getNumCB() {
		return numCB;
	}

	public int getMontant() {
		return montant;
	}

	public void depotDe(int mnt) {	
		montant += mnt;		
	}
	
	public void retraitDe(int mnt) {
		montant = montant - mnt;
	}
	
	public int valeurDuSolde() {
		return montant;
	}

}
