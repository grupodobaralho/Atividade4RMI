package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Trabalho de RMI feito para a disciplina de Programacao Distribuida
 * 
 * @author Israel Deorce e Felipe Nunes
 *
 */
public class Jogador extends UnicastRemoteObject implements JogadorInterface {
	private static final long serialVersionUID = 7896795898928782850L;
	
    private String nome;
    private int id;
    private int tempoOcioso;

    public Jogador(String nome) throws RemoteException{
        this.nome = nome;
        this.id = -1;
        this.tempoOcioso = 0;
    }

    public Jogador(String nome, int id) throws RemoteException{
        this.nome = nome;
        this.id = id;
        this.tempoOcioso = 0;
    }
    
	@Override
    public void encerrado() throws RemoteException{
		System.out.println("Encerrou!");

    }
	@Override
    public String getNome(){
        return this.nome;
    }
	@Override
    public void setNome(String nome){
        this.nome = nome;
    }
	@Override
    public int getId(){
        return this.id;
    }
	@Override
    public void setId(int id){
        this.id = id;
    }
	@Override
    public int getTempoOcioso(){
        return this.tempoOcioso;
    }
	@Override
	public int setTempoOcioso(int t) throws RemoteException {
		return this.tempoOcioso = t;
	}
}