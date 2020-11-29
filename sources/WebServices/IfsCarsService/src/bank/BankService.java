/**
 * BankService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank;

public interface BankService extends java.rmi.Remote {
    public bank.Account searchByCCN(long numCB) throws java.rmi.RemoteException;
    public boolean faireAchat(long numCB, int montant) throws java.rmi.RemoteException;
}
