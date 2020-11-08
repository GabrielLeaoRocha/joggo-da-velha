package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Jogada;
import entities.Jogador;
import entities.Posicao;
import entities.Tabuleiro;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Posicao posicao = new Posicao();
		Jogada jogada = new Jogada();
		Tabuleiro tabuleiro = new Tabuleiro();
		String peca;
		char resp;
		List<Jogador> jogadores = new ArrayList<>();
		String nomeJogador01, nomeJogador02;

		System.out.println("========== JOGO DA VELHA ==========");
		pularLinha();

		System.out.print("Forneça o nome do jogador 1: ");
		nomeJogador01 = sc.next();

		do {
			System.out.print("Escolha uma letra para jogar: ");
			peca = sc.next();

			if (jogada.validaPeca(peca)) {
				jogadores.add(new Jogador(nomeJogador01, peca.toUpperCase()));
				break;
			}

		} while (true);

		System.out.print("\nForneça o nome do jogador 2: ");
		nomeJogador02 = sc.next();

		if (jogadores.get(0).getPeca().equals("x") || jogadores.get(0).getPeca().equals("X")) {
			jogadores.add(new Jogador(nomeJogador02, "O"));
		} else {
			jogadores.add(new Jogador(nomeJogador02, "X"));
		}

		pularLinha();

		System.out.println("<---- Que comecem os jogos ---->");
		pularLinha();
		System.out.println(jogadores.get(0).getNome() + " é a peça '" + jogadores.get(0).getPeca() + "'");
		System.out.println(jogadores.get(1).getNome() + " é a peça '" + jogadores.get(1).getPeca() + "'");
		pularLinha();

		do {
			for (int j = 1; j <= (tabuleiro.getColunas()*tabuleiro.getLinhas()); j++) {
				if (j == 1) {
					System.out.println("1  2  3\n4  5  6\n7  8  9");
				} else {
					tabuleiro.printTabuleiro();
				}

				System.out.print("\n#" + jogadores.get(jogada.traduzJogador(jogada.getRodada())).getNome() + " selecione a casa: ");
				int casa = sc.nextInt();
				tabuleiro.acrescentaPeca(posicao.traduzPosicao(casa),jogadores.get(jogada.traduzJogador(jogada.getRodada())).getPeca());
				
				pularLinha();
				
				jogada.addRodada();
				jogada.condicaoDeVitoria(tabuleiro.getMat(), jogadores.get(jogada.traduzJogador(jogada.getRodada())).getPeca());
				jogada.condicaoDeEmpate(tabuleiro.getMat_boolean());
				
				if (jogada.isVitoria()) {
					tabuleiro.printTabuleiro();
					pularLinha();
					System.out.println("Jogador " + jogadores.get(jogada.traduzJogador(jogada.getRodada())).getNome() + " venceu!!!!");
					j = 9;
				}
				else if (!jogada.isVitoria() && jogada.isEmpate()) {
					tabuleiro.printTabuleiro();
					pularLinha();
					System.out.println("Empate!");
					j = 9;
				}
			}
			pularLinha();
			System.out.print("Deseja jogar novamente? (s/n): ");
			resp = sc.next().charAt(0);
			pularLinha();
			System.out.println("------------------------------");
			tabuleiro.criaTabuleiro();
			
		} while (resp != 'n');
		sc.close();
	}

	// methods

	public static void pularLinha() {
		System.out.println();
	}
}

/*
 * MELHORIAS A SEREM FEITAS: 
 * lançar exceções 
 * retornar para a jogada passada caso ocorra uma exceção
 * consertar verificacao de vitoria
 */