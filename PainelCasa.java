import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PainelCasa extends JPanel {
    private ListaCasas casas;

    public PainelCasa(ListaCasas casas) {
        this.casas = casas;
    }

    public void desenhar(Graphics g, int tabX, int tabY , int panelWidth, int panelHeight) {

        if (casas != null) {
            double tabAspect = (double) tabX / tabY;
            double panelAspect = (double) panelWidth / panelHeight;


            //calcula tamanho do tabuleiro
            int newTabWidth, newTabHeight;

            if (panelAspect > tabAspect) {
                newTabHeight = panelHeight;
                newTabWidth = (int) (newTabHeight * tabAspect);

            } else {
                newTabWidth = panelWidth;
                newTabHeight = (int) (newTabWidth / tabAspect);
            }
            int newCasaWidth = (int) (newTabWidth * 0.045);
            int newCasaHeight = (int) (newCasaWidth);


            double x;
            double y;

            int casaXPos;
            int casaYPos;


            for (int i = 0; i < 40; i++) {
                Casa casa = casas.getCasa(i); // Obtém a casa pelo índice

               //tamanho novo do player

                int espace = 43;


                for (int j = 0; j < casa.getQtd(); j++) {
                    // Ajusta a posição do jogador (pode ser centralizado ou ajustado conforme a necessidade)
                    if((i < 10) || ((i > 20) && (i < 30))){
                        x = (casa.getX()  * (newTabWidth / (double) tabX));
                        y = ((casa.getY() + (j * (espace-11)))* (newTabHeight / (double) tabY));
                    }
                    else{
                        x = ((casa.getX() + (j * espace)) * (newTabWidth / (double) tabX));
                        y = (casa.getY() * (newTabHeight / (double) tabY));
                    }


                    // Calcula posição da casa
                    casaXPos = (int) (((panelWidth - newTabWidth)/2) + x);// Exemplo de centralização
                    casaYPos = (int) (((panelHeight - newTabHeight)/2) + y);// Posição na parte inferior do painel
                    // Desenha a imagem da casa
                    BufferedImage imagemCasa = casa.getImagem(j);
                    if (imagemCasa != null) {
                        g.drawImage(imagemCasa, casaXPos, casaYPos, newCasaWidth, newCasaHeight, null);
                    }
                }
            }
        }
    }
}






 


