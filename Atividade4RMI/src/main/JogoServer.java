package main;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

/**
 * Trabalho de RMI feito para a disciplina de Programacao Distribuida
 * 
 * @author Israel Deorce e Felipe Nunes
 *
 */
class JogoServer {
	public static void main(String[] argv) {

		try {
			System.setProperty("Servidor", "localhost");
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (RemoteException e) {
			System.out.println("RMI registry already running.");
		}
		try {
			Jogo jogo = new Jogo();
			Naming.rebind("Jogo", jogo);
			System.out.println("Jogo is ready.");

			int quantidadeEncerrados;
			JogadorInterface jogadorInterface;
			while (true) {
				TimeUnit.SECONDS.sleep(1);
				quantidadeEncerrados = jogo.refresh();
				while (quantidadeEncerrados > 0) {
					jogadorInterface = (JogadorInterface) Naming.lookup("//localhost/Jogador");
					jogadorInterface.encerrado();
					quantidadeEncerrados--;
				}
			}
		} catch (Exception e) {
			System.out.println("Jogo failed:");
			e.printStackTrace();
		}
	}
}
