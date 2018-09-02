package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Trabalho de RMI feito para a disciplina de Programacao Distribuida
 * 
 * @author Israel Deorce e Felipe Nunes
 *
 */
public interface JogoInterface extends Remote {
	public int registra(JogadorInterface jogador) throws RemoteException;

	public int joga(int id) throws RemoteException;

	public int encerra(int id) throws RemoteException;
}