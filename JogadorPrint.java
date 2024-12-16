import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JogadorPrint {
    private BufferedImage player;
    private int playerX;
    private int playerY;
    private int id;
    private int pos;
    private ListaCasas casas;

    public JogadorPrint(BufferedImage player, ListaCasas casas, int id) {
            this.player = player;
            this.pos = 0;
            this.id = id;
            this.playerX = (casas.getCasaId(this.pos)).getX();
            this.playerY = (casas.getCasaId(this.pos)).getY();
            this.casas = casas;
    }
    
    public int getX(){
        return this.playerX;
    }

    public void setX(int x){
        this.playerX = x;
    }


    public int getY(){
        return this.playerY;
    }

    public void setY(int y){
        this.playerY = y;
    }


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }


    public int getPos(){
        return this.pos;
    }

    public void setPos(int pos){
        this.pos = pos;
    }


    public void atualizarPosicao(int pos) {
        setPos(pos);  
        setX(casas.getCasaId(pos).getX());
        setY(casas.getCasaId(pos).getY());
    }

    public void desenhar(Graphics g, int tabX, int tabY, int panelWidth, int panelHeight) {
        if (player != null) {
            double playerAspect = (double) player.getWidth() / player.getHeight();
            int playerXPos; 
            int playerYPos;

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


            //tamanho novo do player
            int newPlayerWidth = (int) (newTabWidth * 0.045);
            int newPlayerHeight = (int) (newPlayerWidth / playerAspect);

            double x;
            double y;
            int espace = 43;


            // Ajusta a posição do jogador (pode ser centralizado ou ajustado conforme a necessidade)
            if((getPos() < 10) || ((getPos() > 20) && (getPos() < 30))){
                x = (this.playerX  * (newTabWidth / (double) tabX));
                y = ((this.playerY + (this.id * (espace-11)))* (newTabHeight / (double) tabY));
            }
            else{
                x = ((this.playerX + (this.id * espace)) * (newTabWidth / (double) tabX));
                y = (this.playerY * (newTabHeight / (double) tabY));


            }


            playerXPos = (int) (((panelWidth - newTabWidth)/2) + x);// Exemplo de centralização
            playerYPos = (int) (((panelHeight - newTabHeight)/2) + y);// Posição na parte inferior do painel
                    


            g.drawImage(player, playerXPos, playerYPos, newPlayerWidth, newPlayerHeight, null);
        }
    }
}
