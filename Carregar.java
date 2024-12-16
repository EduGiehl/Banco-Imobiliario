import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Carregar, todos os metodos retornam false caso o arquivo .save
 * respectivo nao exista ou esteja corrompido, em caso de sucesso retornam true
 */
public class Carregar {

    //Abre um arquivo tabuleiro.save e usando o metodo tabuleiro.getTerreno
    //pega cada um dos terrenos e vai preenchendo os
    //atributos com os dados lidos no arquivo.
    public static boolean carregaTabuleiro(){
        Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("tabuleiro.save"));
            for (int posicao = 0; posicao < 40; posicao++) {
                Terreno terreno = tabuleiro.getTerreno(posicao);
                terreno.setDono(Integer.valueOf(leitor.readLine()));
                terreno.setTipo(Integer.valueOf(leitor.readLine()));
                terreno.setPreco(Integer.valueOf(leitor.readLine()));
                terreno.setCasas(Integer.valueOf(leitor.readLine()));
                terreno.setAluguelBase(Integer.valueOf(leitor.readLine()));
                terreno.setPrecoCasa(Integer.valueOf(leitor.readLine()));
                terreno.setAluguelCasa(Integer.valueOf(leitor.readLine()));
            }
            leitor.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Abre um arquivo contas.save e usando o metodo banco.getConta
    //pega cada conta e preenche o saldo com o valor
    //lido no arquivo.
    public static boolean carregaBanco(){
        Banco banco = Banco.getBanco();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("contas.save"));
            for (int conta = 0; conta < 4; conta++) {
                banco.getConta(conta).setSaldo(Integer.valueOf(leitor.readLine()));
            }
            leitor.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Abre o arquivo jogadores.save e usa o metodo sistema.getJogador e
    //atraves dos metodos set vai preenchendo os atributos
    //Por fim carrega o proxJogador.
    public static boolean carregaJogador(){
        Sistema sistema = Sistema.getSistema();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("jogadores.save"));
            for (int id = 0; id < 4; id++) {
                Jogador jogador = sistema.getJogador(id);
                jogador.setPosicao(Integer.valueOf(leitor.readLine()));
                jogador.setRodadasBloqueado(Integer.valueOf(leitor.readLine()));
                jogador.setPreso(Boolean.valueOf(leitor.readLine()));
                jogador.setFalido(Boolean.valueOf(leitor.readLine()));
                for (int i = 0; i < 8; i++) {
                    jogador.setNumPropriedade(i, Integer.valueOf(leitor.readLine()));
                }
            }
            sistema.setProxJogador(Integer.valueOf(leitor.readLine()));
            leitor.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
