import java.util.Scanner;

public class Tabuleiro {

  private static char ESPACO = ' ';

  /* campo / atributo - tabuleiro */
  private char[][] tab;

  /* campo / atributo - jogador */
  private char jogador;

  /* construtor em Java */
  public Tabuleiro() {
    tab = new char[3][3];
    jogador = 'X';

    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        tab[i][j] = ESPACO;
  }

  /*
   * método, verifica se
   * X - x ganhou,
   * O - bolinha ganhou
   * E - existem espaços em branco
   * V - empate
   */
  public char estado_partida() {

    // verifica se houve vencedor
    // 1 - diagonais
    if (tab[0][0] == tab[1][1] && tab[0][0] == tab[2][2]) {
      if (tab[1][1] != ESPACO) {
        return tab[1][1];
      }
    }
    if (tab[0][2] == tab[1][1] && tab[2][0] == tab[1][1]) {
      if (tab[1][1] != ESPACO) {
        return tab[1][1];
      }
    }

    // 2 - horizontais
    for (int i = 0; i < 3; i++)
      if (tab[i][0] == tab[i][1] && tab[i][1] == tab[i][2])
        if (tab[i][0] != ESPACO)
          return tab[i][0];

    // 3 - verticais
    for (int i = 0; i < 3; i++)
      if (tab[0][i] == tab[1][i] && tab[1][i] == tab[2][i])
        if (tab[0][i] != ESPACO)
          return tab[0][i];

    // verifica se há espaços em branco
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (tab[i][j] == ESPACO)
          return 'E';

    return 'V';
  }

  public void visualizacao_tabuleiro() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.printf("%c | ", tab[i][j]);
      }
      System.out.println();
    }

  }

  public boolean executa_jogada(int x, int y) {

    if (x < 0 || x > 2)
      return false;
    if (y < 0 || y > 2)
      return false;

    if (tab[x][y] == ESPACO) {
      tab[x][y] = jogador;
      return true;
    }

    return false;
  }

  /* inicia um novo jogo */
  public void jogar() {

    while (estado_partida() == 'E') {

      visualizacao_tabuleiro();

      Scanner leitor = new Scanner(System.in);

      System.out.printf("Jogada (" + jogador + "), x: ");
      int x = leitor.nextInt();

      System.out.printf("Jogada (" + jogador + "), y: ");
      int y = leitor.nextInt();

      boolean resultado = executa_jogada(x, y);

      if (resultado == true) {
        jogador = (jogador == 'X') ? 'O' : 'X';
      } else {
        System.out.println("Movimento inválido!");
      }
    }

    System.out.printf("Fim de jogo: %c\n", estado_partida());

    visualizacao_tabuleiro();
  }
}