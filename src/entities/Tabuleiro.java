package entities;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private String mat[][];
	private boolean mat_boolean[][];
	
	public Tabuleiro() {
		this.linhas = 3;
		this.colunas = 3;
		this.mat = new String[linhas][colunas];
		this.mat_boolean = new boolean[linhas][colunas];
		criaTabuleiro();
	}
	
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public String[][] getMat() {
		return mat;
	}
	
	public boolean[][] getMat_boolean(){
		return mat_boolean;
	}
	
	//metodos
	private void criaTabuleiro(){
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = "-";
			}
		}
		
		for (int i = 0; i < mat_boolean.length; i++) {
			for (int j = 0; j < mat_boolean[i].length; j++) {
				mat_boolean[i][j] = false;
			}
		}
	}
	
	public void acrescentaPeca(Posicao p, String peca) {
		if(this.mat_boolean[p.getLinha()][p.getColuna()] == false){
			this.mat[p.getLinha()][p.getColuna()] = peca;
			this.mat_boolean[p.getLinha()][p.getColuna()] = true;
		} else {
			System.out.println("imposivel fazer essa jogada");
		}
	}
	
	public void printTabuleiro() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
}
