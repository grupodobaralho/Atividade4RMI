package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Trabalho de RMI feito para a disciplina de Programacao Distribuida
 * 
 * @author Israel Deorce e Felipe Nunes
 *
 */
public class Jogo extends UnicastRemoteObject implements JogoInterface {
	private static final long serialVersionUID = 7896795898928782849L;
	private List<JogadorInterface> jogadores = new ArrayList<>();
	private int countId;

	public Jogo() throws RemoteException {
		this.countId = 0;
	}

	public int registra(JogadorInterface jogador) throws RemoteException {
		jogador.setId(++countId);
		jogadores.add(jogador);
		return jogador.getId();
	}

	public int joga(int id) throws RemoteException {
		for (JogadorInterface j : jogadores) {
			if (j.getId() == id) {
				j.setTempoOcioso(0);
				System.out.println("Jogador " + j.getNome() + " jogou! O ID dele eh: " + id);
				return id;
			}
		}
		System.out.println("Nao Encontrou jogador em joga(int id)!");
		return -1;
	}

	public int encerra(int id) throws RemoteException {
		for (JogadorInterface j : jogadores) {
			if (j.getId() == id) {
				jogadores.remove(j);
				System.out.println("Jogador " + j.getNome() + " encerrou! O ID dele era: " + id);
				return id;
			}
		}
		System.out.println("Nao Encontrou jogador em joga(int id)!");
		return -1;
	}

	public int refresh() throws RemoteException {
		int quantidade = 0;
		for (JogadorInterface j : jogadores) {
			j.setTempoOcioso(j.getTempoOcioso() + 1);
			if (j.getTempoOcioso() == 20) {
				quantidade++;
			}
		}
		return quantidade;
	}

}
