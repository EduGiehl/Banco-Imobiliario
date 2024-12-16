import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PainelJogo extends JPanel {
    private TabuleiroPrint tabuleiroObj;
    private JogadorPrint jogadorObj[];
    private PainelCasa casas;

    public PainelJogo(TabuleiroPrint tabuleiroObj, JPanel mainPanel, CardLayout cardLayout, JogadorPrint jogadorObj[], PainelCasa casas) {
        this.tabuleiroObj = tabuleiroObj;
        this.jogadorObj = jogadorObj;
        this.casas = casas;

        // Adiciona o KeyListener para detectar ESC
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    mostrarOpcoes(mainPanel, cardLayout);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Obtém o tamanho do painel
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Desenha o tabuleiro e o jogador
        tabuleiroObj.desenhar(g, panelWidth, panelHeight);
        for(int i = 0; i<4; i++){
            jogadorObj[i].desenhar(g, tabuleiroObj.getX(), tabuleiroObj.getY(), panelWidth, panelHeight);
        }
        casas.desenhar(g, tabuleiroObj.getX(), tabuleiroObj.getY(), panelWidth, panelHeight);

    }

    public void mostrarOpcoes(JPanel mainPanel, CardLayout cardLayout) {
        // Exibe opções usando JOptionPane
        String[] opcoes = {"Continuar", "Salvar Jogo", "Voltar ao Menu","Sair"};
        int escolha = JOptionPane.showOptionDialog(this, "Escolha uma opção:", "Menu do Jogo", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        // Processa a escolha do jogador
        switch (escolha) {
            case 0: // Continuar
                System.out.println("Jogo continuado.");
                break;
            case 1: // Salvar Jogo
                System.out.println("Jogo salvo.");
                break;
            case 2:
                // Adicionar ação para o botão
                System.out.println("Retornou ao menu.");
                cardLayout.show(mainPanel, "Menu Inicial");
                break;
            case 3: // Sair
                System.exit(0);
                break;
            default:
                System.out.println("Nenhuma ação tomada.");
                break;
        }
    }

    public JogadorPrint getJogadorObj(int id) {
        return jogadorObj[id];
    }

    public void atualizar() {
        repaint();
    }
}
