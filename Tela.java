import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.awt.event.MouseAdapter;



public class Tela{
    private static JogadorPrint jogadorObj[];
    private static ImageIcon cartas[];
    private static ImageIcon construct[];
    private static BufferedImage dados[];
    private static CardLayout card;


    public  static JFrame configurarFrame() {
        JFrame frame = new JFrame("Jogo com Menu Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        jogadorObj = new JogadorPrint[4];
        cartas = new ImageIcon[32];
        construct = new ImageIcon[40];
        dados = new BufferedImage[6];
        return frame;
    }

    public static JPanel criarTela(JFrame frame, JPanel mainPanel, CardLayout cardLayout) {
       ListaCasas casas = new ListaCasas();
       card = cardLayout;
       BufferedImage players[] = new BufferedImage[4];
        // Carregar imagens
        BufferedImage tabuleiro = null;
        BufferedImage player = null;

            for(int i = 0; i<40; i++)
                construct[i] = new ImageIcon("./imagens/cartas/" + i + ".png");

            for(int i = 0; i<32; i++)
                cartas[i] = new ImageIcon("./imagens/sorte/" + (i+1) + ".png");

        try {
            tabuleiro = ImageIO.read(new File("./imagens/tabuleiro.png"));
            
            for(int i = 0; i<4; i++)
                players[i] = ImageIO.read(new File("./imagens/players/player" + i + ".png"));

            for(int i = 0; i<6; i++)
                dados[i] = ImageIO.read(new File("./imagens/dados/dado_" + (i+1) + ".png"));



        } catch (IOException e) {
            System.out.println("Erro ao carregar as imagens: " + e.getMessage());
            System.exit(1);
        }

        // Criar objetos do jogo
        TabuleiroPrint tabuleiroObj = new TabuleiroPrint(tabuleiro);



        for(int i = 0; i<4; i++){
            jogadorObj[i] = new JogadorPrint(players[i], casas, i);
            jogadorObj[i].setId(i); 
        }

        // Painel do jogo
        PainelCasa painelCasa = new PainelCasa(casas);
        PainelJogo PainelJogo = new PainelJogo(tabuleiroObj, mainPanel, cardLayout, jogadorObj, painelCasa);
        PainelHud PainelHud = new PainelHud(PainelJogo, mainPanel, cardLayout, casas);
        //panel desenha


        // Configurar painel principal do jogo
        JPanel jogoPanel = new JPanel(new BorderLayout());
        jogoPanel.add(PainelJogo, BorderLayout.CENTER);
        jogoPanel.add(PainelHud, BorderLayout.SOUTH);
        return jogoPanel;
    }

    public static void playerMovimenta(int id, int pos){
        jogadorObj[id].atualizarPosicao(pos);
    }

    public static void mensagem(String mensagem){
       
        // Painel principal
        JPanel messagePanel = new JPanel();

        String[] opcoes = {"OK"};

        int escolha = JOptionPane.showOptionDialog(
                messagePanel,
                mensagem,       // Mensagem
                "Evento",                 // Título da janela
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,                         // Aqui vai a imagem
                opcoes,                         // Botões disponíveis
                opcoes[0]                       // Botão padrão selecionado
        );

    }



    public static void mostraSorte(int sorte){
                // Painel principal
        JPanel sortePanel = new JPanel();
        

        // Exibir o JOptionPane com a imagem
        String[] opcoes = {"OK"};
        System.out.println(sorte);
        int escolha = JOptionPane.showOptionDialog(
                sortePanel,
                "Carta!",       // Mensagem
                "Sorte",                 // Título da janela
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                cartas[sorte-1],                         // Aqui vai a imagem
                opcoes,                         // Botões disponíveis
                opcoes[0]                       // Botão padrão selecionado
        );

    }

    public static void mostraOptCompra(int pos){
        // Painel principal
        JPanel buyPanel = new JPanel();
        
        // Exibir o JOptionPane com a imagem
        String[] opcoes = {"Comprar", "Ignorar"};



        int escolha = JOptionPane.showOptionDialog(
                buyPanel,
                "Desejas comprar este terreno?",       // Mensagem
                "Permuta",                 // Título da janela
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                construct[pos],                         // Aqui vai a imagem
                opcoes,                         // Botões disponíveis
                opcoes[0]                       // Botão padrão selecionado
        );
        switch(escolha){
        case 0:
            Sistema sistema = Sistema.getSistema();
            sistema.compra();
        break;
        default:

        break;

        }

    }


   public static void mostraDados(int a, int b) {

        // Cria um painel para exibir as imagens
        JPanel painelDados = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Verifica se as imagens estão carregadas
                if (dados[a - 1] != null && dados[b - 1] != null) {
                    g.drawImage(dados[a - 1], 50, 50, 40, 40, null);
                    g.drawImage(dados[b - 1], 150, 50, 40, 40, null);
                } else {
                    g.drawString("Carregando dados...", 100, 100); // Exibe uma mensagem de carregamento
                }
            }
        };
         painelDados.setPreferredSize(new Dimension(250, 150));
        painelDados.repaint();
        // Criar o JOptionPane com o painel de dados e o botão OK
        String[] opcoes = {"OK", "MENU"};
        int escolha = JOptionPane.showOptionDialog(
                null,                // O componente pai (null significa que o painel será centralizado na tela)
                painelDados,         // O painel contendo as imagens
                "Dados",             // Título da janela
                JOptionPane.DEFAULT_OPTION, // Sem botões adicionais
                JOptionPane.PLAIN_MESSAGE,  // O tipo de mensagem
                null,                 // Não usa um ícone
                opcoes,               // Os botões disponíveis
                opcoes[0]            // Botão padrão (OK)
        );
    
        // Aqui você pode tratar a escolha do botão
        if (escolha == JOptionPane.OK_OPTION) {
            System.out.println("O botão OK foi pressionado");
        } else if (escolha == JOptionPane.CLOSED_OPTION) {
            System.out.println("A janela foi fechada");
        } else if (escolha == JOptionPane.NO_OPTION) {
            //Main.iniciarJogo();
                JPanel mainPanel = new JPanel();
                String[] var3 = new String[]{"Continuar", "Salvar Jogo", "Sair"};
                int var4 = JOptionPane.showOptionDialog(mainPanel, "Escolha uma op\u00e7\u00e3o:", "Menu do Jogo", -1, -1, (Icon)null, var3, var3[0]);
                switch (var4) {
                   case 0:
                      System.out.println("Jogo continuado.");
                      break;
                   case 1:
                      System.out.println("Jogo salvo.");
                      Sistema sistema = Sistema.getSistema();
                      sistema.salvarJogo();
                      break;
                   case 2:
                      System.exit(0);
                      break;
                   default:
                      System.out.println("Nenhuma a\u00e7\u00e3o tomada.");
                }
    

            System.out.println("A janela foa");
        }
    }

    public static void construirCasa(int pos){
        // Painel principal
        JPanel buyPanel = new JPanel();
        
        // Exibir o JOptionPane com a imagem
        String[] opcoes = {"Comprar", "Ignorar"};

        int escolha = JOptionPane.showOptionDialog(
                buyPanel,
                "Desejas comprar este terreno?",       // Mensagem
                "Permuta",                 // Título da janela
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                construct[pos],                         // Aqui vai a imagem
                opcoes,                         // Botões disponíveis
                opcoes[0]                       // Botão padrão selecionado
        );
        switch(escolha){
        case 0:
            Sistema sistema = Sistema.getSistema();
            sistema.construcao();

        break;
        default:

        break;

        }

    }

    public static void novaCasa(int pos){
        
    
    }

    public static JPanel configurarMainPanel(JFrame frame) {
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        mainPanel.add(new MenuInicial(mainPanel, cardLayout, frame), "Menu Inicial");
        mainPanel.add(Tela.criarTela(frame, mainPanel, cardLayout), "Jogo");

        cardLayout.show(mainPanel, "Menu Inicial");
        return mainPanel;
    }
}
