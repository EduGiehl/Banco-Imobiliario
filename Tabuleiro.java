
public class Tabuleiro {
    private static Tabuleiro tabuleiro;
    private Terreno[] terrenos;

    private Tabuleiro(){
        terrenos = new Terreno[40];
        for (int i = 0; i < 40; i++) {
            terrenos[i] = new Terreno();
        }
    }
    public static synchronized Tabuleiro getTabuleiro() {
        if (tabuleiro == null)
            tabuleiro = new Tabuleiro();
        return tabuleiro;
    }

    //Seta os valores padroes dos terrenos conforme o tipo
    public void inicializa(){
        for (int i = 0; i < 40; i++) {
            switch (i) {
                //Terrenos verdes
                case 1:
                case 2:
                case 4:
                    terrenos[i].setTipo(0);
                    terrenos[i].setPreco(100000);
                    terrenos[i].setAluguelBase(60);
                    terrenos[i].setPrecoCasa(500);
                    terrenos[i].setAluguelCasa(900);
                    break;
                //Terrenos vermelhos
                case 5:
                case 7:
                case 9:
                    terrenos[i].setTipo(1);
                    terrenos[i].setPreco(240000);
                    terrenos[i].setAluguelBase(200);
                    terrenos[i].setPrecoCasa(1500);
                    terrenos[i].setAluguelCasa(3000);
                    break;
                //Terrenos azuis
                case 12:
                case 13:
                case 15:
                    terrenos[i].setTipo(2);
                    terrenos[i].setPreco(200000);
                    terrenos[i].setAluguelBase(120);
                    terrenos[i].setPrecoCasa(1000);
                    terrenos[i].setAluguelCasa(1800);
                    break;
                //Terrenos rosa
                case 17:
                case 18:
                    terrenos[i].setTipo(3);
                    terrenos[i].setPreco(400000);
                    terrenos[i].setAluguelBase(500);
                    terrenos[i].setPrecoCasa(2000);
                    terrenos[i].setAluguelCasa(6000);
                    break;
                //Terrenos roxos
                case 19:
                case 21:
                    terrenos[i].setTipo(4);
                    terrenos[i].setPreco(120000);
                    terrenos[i].setAluguelBase(80);
                    terrenos[i].setPrecoCasa(500);
                    terrenos[i].setAluguelCasa(1000);
                    break;
                //Terrenos ciano
                case 25:
                case 26:
                case 28:
                    terrenos[i].setTipo(5);
                    terrenos[i].setPreco(160000);
                    terrenos[i].setAluguelBase(160);
                    terrenos[i].setPrecoCasa(1000);
                    terrenos[i].setAluguelCasa(2200);
                    break;
                //Terrenos laranjados
                case 31:
                case 33:
                case 34:
                    terrenos[i].setTipo(6);
                    terrenos[i].setPreco(320000);
                    terrenos[i].setAluguelBase(280);
                    terrenos[i].setPrecoCasa(2000);
                    terrenos[i].setAluguelCasa(4500);
                    break;
                //Terrenos amarelos
                case 35:
                case 37:
                case 39:
                    terrenos[i].setTipo(7);
                    terrenos[i].setPreco(280000);
                    terrenos[i].setAluguelBase(260);
                    terrenos[i].setPrecoCasa(1500);
                    terrenos[i].setAluguelCasa(3600);
                    break;
                //Companhias
                case 3:
                case 14:
                case 22:
                case 23:
                case 29:
                    terrenos[i].setTipo(8);
                    terrenos[i].setPreco(200000);
                    terrenos[i].setAluguelBase(500);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Sorte ou reves
                case 6:
                case 11:
                case 24:
                case 27:
                case 32:
                case 38:
                    terrenos[i].setTipo(9);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Receita federal
                case 16:
                    terrenos[i].setTipo(10);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Restituicao imposto de renda
                case 8:
                    terrenos[i].setTipo(11);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Va para detencao
                case 30:
                    terrenos[i].setTipo(12);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Prisao
                case 10:
                    terrenos[i].setTipo(13);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Feriado
                case 20:
                    terrenos[i].setTipo(14);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                //Inicio
                case 0:
                    terrenos[i].setTipo(15);
                    terrenos[i].setPreco(0);
                    terrenos[i].setAluguelBase(0);
                    terrenos[i].setPrecoCasa(0);
                    terrenos[i].setAluguelCasa(0);
                    break;
                default:
                    break;
            }
        }
    }

    //Esse metodo so devera ser utilizado na classe Carregar para inicializar os terrenos um a um
    //conforme os valores salvos, para o resto do codigo acessar os atributos de cada terreno
    //atraves dos outros metodos, verifica, atualiza e constroi
    public Terreno getTerreno(int terreno){
        return terrenos[terreno];
    }
    public int verificaProprietario(int posicao){
        return terrenos[posicao].getDono();
    }
    public int verificaPreco(int posicao){
        return terrenos[posicao].getPreco();
    }
    public int verificaAluguel(int posicao){
        return terrenos[posicao].getAluguel();
    }
    public int verificaCasas(int posicao){
        return terrenos[posicao].getCasas();
    }
    public int verificaTipo(int posicao){
        return terrenos[posicao].getTipo();
    }
    public int verificaPrecoCasa(int posicao){
        return terrenos[posicao].getPrecoCasa();
    }

    //Recebe uma posicao e o num de terrenos do mesmo tipo que o jogador possui
    //Vefrifica se o jogador possui todos os terrenos desse tipo e se o terreno
    //a ser consttruido possui <= casas que os outros, retorna falso caso algum
    //dos testes for falso
    public boolean podeConstruir(int posicao, int numTerrenos){
        boolean permissao = false;
        int tipo = terrenos[posicao].getTipo();
        int numCasas = terrenos[posicao].getCasas();
        switch (tipo) {
            case 0:
                if (numTerrenos == 3 && numCasas <= terrenos[1].getCasas() &&
                    numCasas <= terrenos[2].getCasas() &&
                    terrenos[4].getCasas() <= numCasas)
                    permissao = true;
                break;
            case 1:
                if (numTerrenos == 3 && numCasas <= terrenos[5].getCasas() &&
                    numCasas <= terrenos[7].getCasas() &&
                    terrenos[9].getCasas() <= numCasas)
                    permissao = true;
                break;
            case 2:
                if (numTerrenos == 3 && numCasas <= terrenos[12].getCasas() &&
                    numCasas <= terrenos[13].getCasas() &&
                    terrenos[15].getCasas() <= numCasas)
                    permissao = true;
                break;
            case 3:
                if (numTerrenos == 2 && numCasas <= terrenos[17].getCasas() &&
                    numCasas <= terrenos[18].getCasas())
                    permissao = true;
                break;
            case 4:
                if (numTerrenos == 2 && numCasas <= terrenos[19].getCasas() &&
                    numCasas <= terrenos[21].getCasas())
                    permissao = true;
                break;
            case 5:
                if (numTerrenos == 3 && numCasas <= terrenos[25].getCasas() &&
                    numCasas <= terrenos[26].getCasas() &&
                    terrenos[28].getCasas() <= numCasas)
                    permissao = true;
                break;
            case 6:
                if (numTerrenos == 3 && numCasas <= terrenos[31].getCasas() &&
                    numCasas <= terrenos[33].getCasas() &&
                    terrenos[34].getCasas() <= numCasas)
                    permissao = true;
                break;
            case 7:
                if (numTerrenos == 3 && numCasas <= terrenos[35].getCasas() &&
                    numCasas <= terrenos[37].getCasas() &&
                    terrenos[39].getCasas() <= numCasas)
                    permissao = true;
                break;
            default:
                break;
        }
        return permissao;
    }
    public void constroiCasa(int posicao){
        terrenos[posicao].novaCasa();
    }
    public void atualizaProprietario(int proprientario, int posicao){
        terrenos[posicao].setDono(proprientario);
    }
}
