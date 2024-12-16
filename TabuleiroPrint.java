import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TabuleiroPrint {
    private BufferedImage tabuleiro;

    public TabuleiroPrint(BufferedImage tabuleiro) {
        this.tabuleiro = tabuleiro;
    }
    
    public int getX(){
        return this.tabuleiro.getWidth();
    }

    public int getY(){
        return this.tabuleiro.getHeight();
    }

    public void desenhar(Graphics g, int panelWidth, int panelHeight) {
        // Redimensiona a imagem do tabuleiro para se ajustar ao tamanho do painel
        if (tabuleiro != null) {
            int tabWidth = panelWidth;
            int tabHeight = panelHeight;

            double tabAspect = (double) tabuleiro.getWidth() / tabuleiro.getHeight();
            double panelAspect = (double) panelWidth / panelHeight;

            int newTabWidth, newTabHeight;

            if (panelAspect > tabAspect) {
                newTabHeight = panelHeight;
                newTabWidth = (int) (newTabHeight * tabAspect);
            } else {
                newTabWidth = panelWidth;
                newTabHeight = (int) (newTabWidth / tabAspect);
            }

            int tabX = (panelWidth - newTabWidth) / 2;
            int tabY = (panelHeight - newTabHeight) / 2;

            g.drawImage(tabuleiro, tabX, tabY, newTabWidth, newTabHeight, null);
        }
    }
}
