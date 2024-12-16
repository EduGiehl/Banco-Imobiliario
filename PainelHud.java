import javax.swing.*;
import java.awt.*;

public class PainelHud extends JPanel {
    public PainelHud(PainelJogo PainelJogo, JPanel mainPanel, CardLayout cardLayout, ListaCasas casas) {
        setLayout(new GridLayout(2, 3));

        // Criação dos botões
        JButton botaoOpcoes = new JButton("Opcoes");

        // Adiciona os botões ao painel
        add(botaoOpcoes);

        // Referência ao jogador
        JogadorPrint jogador = PainelJogo.getJogadorObj(1);

        botaoOpcoes.addActionListener(e -> {
            PainelJogo.mostrarOpcoes(mainPanel, cardLayout);
            PainelJogo.requestFocusInWindow(); // Recupera o foco para o GamePanel
        });

    }
}
