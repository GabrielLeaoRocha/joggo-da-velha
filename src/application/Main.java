package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import entities.Jogador;
import entities.Posicao;
import entities.Tabuleiro;

public class Main {


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		Tabuleiro tabuleiro = new Tabuleiro();
		Posicao posicao = new Posicao();
		List<Jogador> jogadores = new ArrayList<>();
		
		System.out.println("========== JOGO DA VELHA ==========");
		pularLinha();

		for (int i = 1; i <= 2; i++) {
			System.out.print("Forneça o nome do jogador " + i + ": ");
			String nome = sc.next();
			System.out.print("Escolha uma letra para jogar: ");
			String peca = sc.next();

			jogadores.add(new Jogador(nome, peca));
			pularLinha();
		}

		System.out.println("<---- Que comecem os jogos ---->");
		pularLinha();

		int rodada = rd.nextInt(2);
		for(int j=1; j<=(tabuleiro.getColunas()*tabuleiro.getLinhas()); j++){
			if(j==1) {
				System.out.println("1  2  3\n4  5  6\n7  8  9");
			}
			else {
				tabuleiro.printTabuleiro();
			}

			int casa;
			
			if (rodada % 2 == 0) {
				System.out.print("\n#" + jogadores.get(0).getNome() + " selecione a casa: ");
				casa = sc.nextInt();
				tabuleiro.acrescentaPeca(posicao.traduzPosicao(casa), jogadores.get(0).getPeca());
			} else {
				System.out.print("\n#" + jogadores.get(1).getNome() + " selecione a casa: ");
				casa = sc.nextInt();
				tabuleiro.acrescentaPeca(posicao.traduzPosicao(casa), jogadores.get(1).getPeca());
			}

			pularLinha();
			rodada++;
			
			for (int i = 0; i <= 1; i++) {
				if (condicaoDeVitoria(tabuleiro.getMat(), jogadores.get(i).getPeca())) {
					tabuleiro.printTabuleiro();
					pularLinha();
					System.out.println("Jogador " + jogadores.get(i).getNome() + " venceu!!!!");
					j = 9;
				}
			}
		}
		sc.close();
	}

	//methods
	public static boolean condicaoDeVitoria(String[][] mat, String peca) {
		
		if (mat[0][0].equals(peca) && mat[0][1].equals(peca) && mat[0][2].equals(peca)
				|| mat[1][0].equals(peca) && mat[1][1].equals(peca) && mat[1][2].equals(peca)
				|| mat[2][0].equals(peca) && mat[2][1].equals(peca) && mat[2][2].equals(peca)
				|| mat[0][0].equals(peca) && mat[1][0].equals(peca) && mat[2][0].equals(peca)
				|| mat[0][1].equals(peca) && mat[1][1].equals(peca) && mat[2][1].equals(peca)
				|| mat[0][2].equals(peca) && mat[1][2].equals(peca) && mat[2][2].equals(peca)
				|| mat[0][0].equals(peca) && mat[1][1].equals(peca) && mat[2][2].equals(peca)
				|| mat[0][2].equals(peca) && mat[1][1].equals(peca) && mat[2][0].equals(peca)) {
			return true;
		} 
		return false;
	}

	public static void pularLinha() {
		System.out.println();
	}
}

/*MELHORIAS A SEREM FEITAS:
	só permitir o jogador escolher entre X ou O, nao qualquer caracter
	criar condição de final por empate
	mudar todos campos envolvendo posicao para a classe posicao
	
*/