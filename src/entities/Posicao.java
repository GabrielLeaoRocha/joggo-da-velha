package entities;

public class Posicao {

	private int linha;
	private int coluna;
	
	

	public Posicao() {
	}

	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	//get set
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	//methods
	public Posicao traduzPosicao(int casa) {
		
		int l, c;
		Posicao p;

		if (casa == 1 || casa == 2 || casa == 3) {
			l = 0;
			c = casa - 1;
		} else if (casa == 4 || casa == 5 || casa == 6) {
			l = 1;
			c = casa - 4;
		} else if (casa == 7 || casa == 8 || casa == 9) {
			l = 2;
			c = casa - 7;
		} else {
			System.out.println("babaca, tentando quebrar o sistema ne?!");
			return null;
		}
		
		p = new Posicao(l, c);
		return p;
	}
	
}
