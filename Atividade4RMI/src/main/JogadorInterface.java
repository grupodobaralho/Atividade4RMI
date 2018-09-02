package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Trabalho de RMI feito para a disciplina de Programacao Distribuida
 * 
 * @author Israel Deorce e Felipe Nunes
 *
 */
public interface JogadorInterface extends Remote {
	public void encerrado() throws RemoteException;

	public int getId() throws RemoteException;;

	public void setId(int id) throws RemoteException;;

	public int getTempoOcioso() throws RemoteException;;

	public int setTempoOcioso(int t) throws RemoteException;;

	public void setNome(String nome) throws RemoteException;;

	public String getNome() throws RemoteException;;
}