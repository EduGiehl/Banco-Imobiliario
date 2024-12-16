import java.io.BufferedWriter;
import java.io.FileWriter;

/*
 * Salvar
 */
public class Salvar {

    //Cria um arquivo tabuleiro.save ou sobrescreve o existente e usa
    //os metodos verifica para ir pegando os campos de cada
    //Terreno e escrevendo no arquivo.
    public static void salvaTabuleiro(){
        Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
        try {
            BufferedWriter escreve = new BufferedWriter(new FileWriter("tabuleiro.save"));
            for (int posicao = 0; posicao < 40; posicao++) {
                Terreno terreno = tabuleiro.getTerreno(posicao);
                escreve.write(String.valueOf(terreno.getDono())+"\n");
                escreve.write(String.valueOf(terreno.getTipo())+"\n");
                escreve.write(String.valueOf(terreno.getPreco())+"\n");
                escreve.write(String.valueOf(terreno.getCasas())+"\n");
                escreve.write(String.valueOf(terreno.getAluguelBase())+"\n");
                escreve.write(String.valueOf(terreno.getPrecoCasa())+"\n");
                escreve.write(String.valueOf(terreno.getAluguelCasa())+"\n");
            }
            escreve.close();
        } catch (Exception e) {
            return;
        }
    }

    //Cria ou sobrescreve um arquivo contas.save e usa o metodo
    //extrato para concatenar o saldo de cada conta na ordem no
    //arquivo contas.save
    public static void salvaContas(){
        Banco banco = Banco.getBanco();
        try {
            BufferedWriter escreve = new BufferedWriter(new FileWriter("contas.save"));
            for (int conta = 0; conta < 4; conta++)
                escreve.write(String.valueOf(banco.extrato(conta))+"\n");
            escreve.close();
        } catch (Exception e) {
            return;
        }
    }

    //Cria ou sobrescreve um arquivo jogadores.save e usa o
    //metodo getJogador para pegar cada jogador e atraves deles
    //usa os metodos get para concatenar os atributos no arquivo
    //jogadores.save, alem disso salva o prox jogador
    public static void salvaJogadores(){
        Sistema sistema = Sistema.getSistema();
        try {
            BufferedWriter escreve = new BufferedWriter(new FileWriter("jogadores.save"));
            for (int id = 0; id < 4; id++) {
                Jogador jogador = sistema.getJogador(id);
                escreve.write(String.valueOf(jogador.getPosicao())+"\n");
                escreve.write(String.valueOf(jogador.getRodadasBloqueado())+"\n");
                escreve.write(String.valueOf(jogador.estaPreso())+"\n");
                escreve.write(String.valueOf(jogador.estaFalido())+"\n");
                for (int i = 0; i < 8; i++)
                    escreve.write(String.valueOf(jogador.getNumPropriedades(i))+"\n");
            }
            escreve.write(sistema.getProxJogador()+"\n");
            escreve.close();
        } catch (Exception e) {
            return;
        }
    }
}
