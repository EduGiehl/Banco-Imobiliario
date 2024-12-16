import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuInicial extends JPanel {

    private BufferedImage backgroundImage;

    public  MenuInicial(JPanel mainPanel, CardLayout cardLayout, JFrame frame) {
        setLayout(new BorderLayout());

        // Carregar imagem de fundo do menu inicial
        try {
            backgroundImage = ImageIO.read(new File("imagens/telaInicial.png"));
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem de fundo: " + e.getMessage());
        }

        // Criar painel de botões com BoxLayout para empilhar os botões verticalmente
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Alinha verticalmente os botões
        buttonPanel.setOpaque(false); // Torna o painel transparente para ver a imagem de fundo

        // Adicionar algum espaço entre os botões
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

        Dimension dim = new Dimension(200,50);
        Font font = new Font("Arial", Font.BOLD, 18);

        // Botão de novo jogo
        JButton btnNovoJogo = new JButton("Novo Jogo");
        btnNovoJogo.setPreferredSize(dim);
        btnNovoJogo.setFont(font);
        btnNovoJogo.setAlignmentX(CENTER_ALIGNMENT);  // Centraliza o botão
        btnNovoJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Jogo");
                Main.iniciarJogo();

            }
        });

        // Botão de carregar jogo
        JButton btnCarregaJogo = new JButton("Carregar Jogo");
        btnCarregaJogo.setPreferredSize(dim);
        btnCarregaJogo.setFont(font);
        btnCarregaJogo.setAlignmentX(CENTER_ALIGNMENT);  // Centraliza o botão
        btnCarregaJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Jogo");
                Main.carregarJogo(mainPanel);

            }
        });

        // Botão de sair
        JButton btnSair = new JButton("Sair");
        btnSair.setPreferredSize(dim);
        btnSair.setFont(font);
        btnSair.setAlignmentX(CENTER_ALIGNMENT);  // Centraliza o botão
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o jogo
            }
        });

        // Adicionando os botões ao painel
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Espaço entre os botões
        buttonPanel.add(btnNovoJogo);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Espaço entre os botões
        buttonPanel.add(btnCarregaJogo);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100))); // Espaço entre os botões
        buttonPanel.add(btnSair);

        // Criar um painel para os botões
        JPanel buttonWrapper = new JPanel(new BorderLayout());
        buttonWrapper.setOpaque(false); // Transparente para mostrar a imagem de fundo
        buttonWrapper.add(buttonPanel, BorderLayout.CENTER);

        // Adicionar o painel de botões ao painel principal
        add(buttonWrapper, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            // Obter a largura e altura do painel
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Calcular a proporção da imagem
            double imageAspect = (double) backgroundImage.getWidth() / backgroundImage.getHeight();
            double panelAspect = (double) panelWidth / panelHeight;

            int newWidth, newHeight;

            // Ajustar imagem mantendo a proporção
            if (panelAspect > imageAspect) {
                newHeight = panelHeight;
                newWidth = (int) (newHeight * imageAspect);
            } else {
                newWidth = panelWidth;
                newHeight = (int) (newWidth / imageAspect);
            }

            // Calcular posição para centralizar a imagem
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            // Desenhar a imagem redimensionada
            g.drawImage(backgroundImage, x, y, newWidth, newHeight, null);
        }
    }
}
