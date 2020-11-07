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

                if(validaPeca(peca)) {
                    jogadores.add(new Jogador(nomeJogador01, peca.toUpperCase()));
                    break;
                }

        }while(true);

        System.out.print("\nForneça o nome do jogador 2: ");
        nomeJogador02 = sc.next();

        if(jogadores.get(0).getPeca().equals("x") || jogadores.get(0).getPeca().equals("X")) {
            jogadores.add(new Jogador(nomeJogador02, "O"));
        }else {
            jogadores.add(new Jogador(nomeJogador02, "X"));
        }

        pularLinha();

        System.out.println("<---- Que comecem os jogos ---->");
        pularLinha();
        System.out.println(jogadores.get(0).getNome() + " é a peça '" + jogadores.get(0).getPeca() + "'");
        System.out.println(jogadores.get(1).getNome() + " é a peça '" + jogadores.get(1).getPeca() + "'");
        pularLinha();
        
        do {
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
			boolean vitoria = false;
			
			for (int i = 0; i <= 1; i++) {
				if (condicaoDeVitoria(tabuleiro.getMat(), jogadores.get(i).getPeca())) {
					tabuleiro.printTabuleiro();
					pularLinha();
					System.out.println("Jogador " + jogadores.get(i).getNome() + " venceu!!!!");
					vitoria = true;
					j = 9;
				}
			}	
			if(condicaoDeEmpate(tabuleiro.getMat_boolean()) && !vitoria){
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
		
        }while(resp != 'n');
		sc.close();
	}

	//methods
	public static boolean validaPeca(String peca) {
        if(peca.equals("X") || peca.equals("x")) {
            return true;
        }else if(peca.equals("O") || peca.equals("o")){
            return true;
        }
        pularLinha();
        System.out.println("Error!!! Escolhe entre 'X' ou 'O'");
        return false;
    }
	
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
	
	public static boolean condicaoDeEmpate(boolean[][] mat) {
		int casasOcupadas = 0;
		for(int i = 0; i<mat.length; i++) {
			for(int j = 0; j<mat[i].length; j++) {
				if(mat[i][j] == true) {
					casasOcupadas++;
				}
			}
		}
		if(casasOcupadas >= 9) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void pularLinha() {
		System.out.println();
	}
}

/*MELHORIAS A SEREM FEITAS:
	lançar exceções
	retornar para a jogada passada caso ocorra uma exceção
*/