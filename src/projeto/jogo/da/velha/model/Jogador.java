package projeto.jogo.da.velha.model;

import java.util.List;

public class Jogador extends Pessoa{

	private int pontos;
	private String peca;
	private List<Jogador> jogadores;
	
	public Jogador(String nome, String peca) {
		super(nome);
		this.pontos = 0;
		this.peca = peca;
	}

	//get set
	public int getPontos() {
		return pontos;
	}
	
	public String getPeca() {
		return peca;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	//methods
	public void addJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}
	
	@Override
	public String toString() {
		return super.getNome() + "\npontuação: " + this.getPontos();
	}
}
