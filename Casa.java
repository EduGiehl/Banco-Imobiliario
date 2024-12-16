import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Casa {
    private int id;
    private int x, y; // Coordenadas para posicionar a casa no painel
    private int qtd;
    private BufferedImage[] casas; // Torne o atributo não estático (para instâncias individuais)

    public Casa(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.qtd = 0; // Inicializa a quantidade

        casas = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            try {
                casas[i] = ImageIO.read(new File("./imagens/dados/dado_" + (i + 1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
                casas[i] = null; // Define como nulo se a imagem não for carregada
            }
        }
    }

    public BufferedImage getImagem(int index) {
        if (index >= 0 && index < casas.length) {
            return casas[index];
        }
        return null; // Retorna nulo se o índice for inválido
    }


 public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getQtd() {
        return this.qtd ;
    }


    public void addQtd() {
        this.qtd = this.qtd+1;
    }












    // Getters e setters omitidos por brevidade
}
