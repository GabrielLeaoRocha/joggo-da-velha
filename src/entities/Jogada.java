package entities;

import java.util.Random;

public class Jogada {

	Random rd = new Random();

	private int rodada;
	private boolean vitoria;
	private boolean empate;

	// construtor
	public Jogada() {
		this.rodada = rd.nextInt(2);
		this.vitoria = false;
		this.empate = false;
	}

	// get e set
	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public boolean isVitoria() {
		return vitoria;
	}

	public void setVitoria(boolean vitoria) {
		this.vitoria = vitoria;
	}

	public boolean isEmpate() {
		return empate;
	}

	public void setEmpate(boolean empate) {
		this.empate = empate;
	}

	// methods
	public void addRodada() {
		this.rodada ++;
	}

	public void condicaoDeVitoria(String[][] mat, String peca) {

		if (mat[0][0].equals(peca) && mat[0][1].equals(peca) && mat[0][2].equals(peca)
				|| mat[1][0].equals(peca) && mat[1][1].equals(peca) && mat[1][2].equals(peca)
				|| mat[2][0].equals(peca) && mat[2][1].equals(peca) && mat[2][2].equals(peca)
				|| mat[0][0].equals(peca) && mat[1][0].equals(peca) && mat[2][0].equals(peca)
				|| mat[0][1].equals(peca) && mat[1][1].equals(peca) && mat[2][1].equals(peca)
				|| mat[0][2].equals(peca) && mat[1][2].equals(peca) && mat[2][2].equals(peca)
				|| mat[0][0].equals(peca) && mat[1][1].equals(peca) && mat[2][2].equals(peca)
				|| mat[0][2].equals(peca) && mat[1][1].equals(peca) && mat[2][0].equals(peca)) {
			this.vitoria = true;
		}
	}

	public void condicaoDeEmpate(boolean[][] mat) {
		int casasOcupadas = 0;
		for(int i = 0; i<mat.length; i++) {
			for(int j = 0; j<mat[i].length; j++) {
				if(mat[i][j] == true) {
					casasOcupadas++;
				}
			}
		}
		
		if(casasOcupadas >= 9) {
			this.empate = true;
		}
	}
	
	public int traduzJogador(int rodada) {
		if(rodada % 2 == 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public boolean validaPeca(String peca) {
		if (peca.equals("X") || peca.equals("x")) {
			return true;
		} else if (peca.equals("O") || peca.equals("o")) {
			return true;
		}
		System.out.println("\nError!!! Escolhe entre 'X' ou 'O'");
		return false;
	}
}
