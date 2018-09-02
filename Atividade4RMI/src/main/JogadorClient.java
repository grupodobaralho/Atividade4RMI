package main;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Trabalho de RMI feito para a disciplina de Programacao Distribuida
 * 
 * @author Israel Deorce e Felipe Nunes
 *
 */
class JogadorClient {

	public static String nomeJogador = "Tonico";
	public static int id;
	private static Scanner in;

	public static void main(String[] argv) {
		String cmd = "";
		in = new Scanner(System.in);

		try {
			System.setProperty("Cliente", "localhost");
			java.rmi.registry.LocateRegistry.createRegistry(1098);
			System.out.println("RMI registry ready.");
		} catch (RemoteException e) {
			System.out.println("RMI registry already running.");
		}

		System.out.println("##### MENU #####");
		System.out.println("---OPÇÕES:\n");
		System.out.println("registra");
		System.out.println("joga");
		System.out.println("encerra");
		System.out.println("sair\n");

		try {
			JogoInterface jogoInterface = (JogoInterface) Naming.lookup("//localhost/Jogo");

			// Tornando o Cliente em um servidor para receber quando jogador encerrou por
			// tempo ocioso
			JogadorInterface jogadorI = new Jogador(nomeJogador);
			Naming.rebind("Jogador", jogadorI);

			while (!cmd.equals("sair")) {
				System.out.println("Digite a opção desejada: ");
				cmd = in.nextLine();

				switch (cmd) {
				case "registra":
					System.out.println("Digite o nome do jogador:");
					nomeJogador = in.nextLine();
					JogadorInterface jogador = new Jogador(nomeJogador);
					id = jogoInterface.registra(jogador);
					System.out.println("Jogador " + nomeJogador + " registrado com o id: " + id);
					break;
				case "joga":
					System.out.println("digite o ID do usuario que deseja jogar:");
					id = Integer.parseInt(in.nextLine());

					id = jogoInterface.joga(id);
					if (id == -1) {
						System.out.println("Erro! Usuario inexistente");
					} else {
						System.out.println("Jogou! :) id: " + id);
					}
					break;
				case "encerra":
					System.out.println("digite o ID do usuario que deseja encerrar:");
					id = Integer.parseInt(in.nextLine());
					if (id == -1) {
						System.out.println("Erro! Usuario inexistente");
					} else {
						System.out.println("Encerrou! :( id: " + id);
					}
					break;
				case "sair":
					System.out.println("Tchal!");
					return;
				default:
					System.out.println("Erro! Digite um comando valido!");
				}

			}

		} catch (Exception e) {
			System.out.println("JogadorClient failed:");
			e.printStackTrace();
		}
	}
}
