import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Sistema sistema;
    private static JFrame frame;
    private static JPanel mainPanel;

    public static void main(String[] args) {

        //eh oq o eduardo mandou eu por
        Sistema sistema = Sistema.getSistema();
        //Sistema.proximoTurno();

        
        //chama configuracao de tela
        frame = Tela.configurarFrame();
        mainPanel = Tela.configurarMainPanel(frame);

        //adiciona o painel
        frame.add(mainPanel);
        frame.setVisible(true);

        if(sistema.getJogadoresVivos()<=1){
        String[] opcoes = {"OK"};
        int id = sistema.getVencedor();
        int escolha = JOptionPane.showOptionDialog(
                mainPanel,
                "Jogador " + id + " venderdor!!!",
                "Menu do Jogo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );
        }   
    }



    public static void carregarJogo(JPanel mainPanel){
        Sistema sistema = Sistema.getSistema();



        if(!sistema.carregarJogo()){
            // Exibe opções usando JOptionPane
            String[] opcoes = {"OK"};
            int escolha = JOptionPane.showOptionDialog(
                    mainPanel,
                    "Nenhum save encontrado!",
                    "Menu do Jogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
        }

        inicializa();
        
    }

    private static  void inicializa(){
        Sistema sistema = Sistema.getSistema();
        for(int i = 0; i<4; i++){
            Jogador jogador = sistema.getJogador(i);
            Tela.playerMovimenta(i, jogador.getPosicao());
        }
        //adiciona o painel
        frame.add(mainPanel);
        iniciarJogo();
        atualizaPanel();
        while(true){
            if(sistema.getJogadoresVivos()<=1){
                String[] opcoes = {"OK"};
                int id = sistema.getVencedor();
                int escolha = JOptionPane.showOptionDialog(
                    mainPanel,
                    "Jogador " + id + " venderdor!!!",
                    "Menu do Jogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
                );
            }else{
                sistema.proximoTurno();
                atualizaPanel();
            }
        }
    }



    private static void atualizaPanel(){
        //adiciona o painel
        frame.repaint();

    }

    public static JPanel getMainPanel(){
        return mainPanel;
    }

    public static void iniciarJogo(){
        Sistema sistema = Sistema.getSistema();
        sistema.novoJogo();

        while(true){
            if(sistema.getJogadoresVivos()<=1){
                String[] opcoes = {"OK"};
                int id = sistema.getVencedor();
                int escolha = JOptionPane.showOptionDialog(
                    mainPanel,
                    "Jogador " + id + " venderdor!!!",
                    "Menu do Jogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
                );
            }else{
                sistema.proximoTurno();
                atualizaPanel();
            }
        }

    }

    
}
