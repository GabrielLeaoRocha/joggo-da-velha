package projeto.jogo.da.velha.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import projeto.jogo.da.velha.model.Jogada;
import projeto.jogo.da.velha.model.Jogador;
import projeto.jogo.da.velha.model.Posicao;
import projeto.jogo.da.velha.model.Tabuleiro;

public class Application {

	private static Scanner sc = new Scanner(System.in);
	private static Posicao posicao = new Posicao();
	private static Jogada jogada = new Jogada();
	private static List<Jogador> jogadores = new ArrayList<>();
	private static String nomeJogador01, nomeJogador02;
	private static String peca;
	private static char resp;

	Application(){}

	public static void partidaJogoDaVelha() {

		System.out.println("========== JOGO DA VELHA ==========");
		Tabuleiro.pularLinha();

		System.out.print("Nome do jogador 1: ");
		nomeJogador01 = sc.nextLine();

		while (true){
			System.out.print("Escolha uma peça entre 'X' ou 'O': ");
			peca = sc.next();

			if (jogada.validaPeca(peca)) {
				jogadores.add(new Jogador(nomeJogador01, peca.toUpperCase()));
				break;
			}
		}

		System.out.print("\nNome do jogador 2: ");
		sc.nextLine();
		nomeJogador02 = sc.nextLine();

		if (jogadores.get(0).getPeca().equals("X")) {
			jogadores.add(new Jogador(nomeJogador02, "O"));
		} else {
			jogadores.add(new Jogador(nomeJogador02, "X"));
		}

		Tabuleiro.pularLinha();

		System.out.println("<---- Que comecem os jogos ---->");
		Tabuleiro.pularLinha();
		System.out.println(jogadores.get(0).getNome() + " é a peça '" + jogadores.get(0).getPeca() + "'");
		System.out.println(jogadores.get(1).getNome() + " é a peça '" + jogadores.get(1).getPeca() + "'");
		Tabuleiro.pularLinha();

		do {
			
			Tabuleiro tabuleiro = new Tabuleiro();
			int j = 1;

			while (!jogada.isVitoria() && !jogada.isEmpate()) {

				if (j == 1) {
					tabuleiro.printMoldeTabuleiro();
					j++;
				}

				try {
					System.out.print("\n#" + jogadores.get(jogada.traduzJogador(jogada.getRodada())).getNome()
							+ " selecione uma casa de 1 a 9: ");
					int casa = sc.nextInt();
					boolean condicaoRodada = tabuleiro.acrescentaPeca(posicao.traduzPosicao(casa),jogadores.get(jogada.traduzJogador(jogada.getRodada())).getPeca());

					if (condicaoRodada) {
						Tabuleiro.pularLinha();
						tabuleiro.printTabuleiro();
					}

					jogada.condicaoDeVitoria(tabuleiro.getMat(),jogadores.get(jogada.traduzJogador(jogada.getRodada())).getPeca());
					jogada.condicaoDeEmpate(tabuleiro.getMat_boolean());

					if (jogada.isVitoria()) {
						Tabuleiro.pularLinha();
						System.out.println("Jogador " + jogadores.get(jogada.traduzJogador(jogada.getRodada())).getNome() + " venceu!!!!");
					} else if (jogada.isEmpate()) {
						Tabuleiro.pularLinha();
						System.out.println("Deu velha!");
					}

					if (condicaoRodada) {
						jogada.addRodada();
					}
					
				} catch (InputMismatchException e) {
					System.out.println("ATENÇÃO! Casa não existente no tabuleiro");
					sc.nextLine();
				}
			}

			Tabuleiro.pularLinha();

			while(true) {
				System.out.print("Deseja jogar novamente? (s/n): ");
				resp = sc.next().toUpperCase().charAt(0);

				if(resp == 'S' || resp == 'N') {
					break;
				}else{
					System.out.println("Escolha entre S (sim) ou N (não)!");
					Tabuleiro.pularLinha();
				}
			}

			Tabuleiro.pularLinha();
			System.out.println("------------------------------");
			jogada.limpaRodada();
			
		} while (resp != 'N');
		sc.close();
	}
}
