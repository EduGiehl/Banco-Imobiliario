//Tododos os metodos sets dessa classe serao utilizados apenas no tabuleiro.inicializar
//ou em Carregar.carregarTabuleiro para inicializar os atributos de cada terreno
//conforme necessario
public class Terreno {
    private int dono;
    private int tipo;
    private int preco;
    private int aluguelBase;
    private int casas;
    private int aluguelCasa;
    private int precoCasa;

    public Terreno(){
        this.dono = -1;
        this.casas = 0;
    };

    public void setDono(int dono) {
        this.dono = dono;
    }
    public int getDono(){
        return this.dono;
    }
    public int getPreco(){
        return this.preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public void setPrecoCasa(int precoCasa) {
        this.precoCasa = precoCasa;
    }
    public int getPrecoCasa() {
        return precoCasa;
    }
    public void setAluguelCasa(int aluguelCasa) {
        this.aluguelCasa = aluguelCasa;
    }
    public int getAluguelCasa() {
        return aluguelCasa;
    }

    //retorna o preco do aluguelBase do terreno + 50 reais por casa construida
    public int getAluguel(){
        return this.aluguelBase + this.casas*getAluguelCasa();
    }
    public void setAluguelBase(int aluguelBase) {
        this.aluguelBase = aluguelBase;
    }
    public int getAluguelBase() {
        return aluguelBase;
    }
    public int getCasas(){
        return this.casas;
    }
    public void setCasas(int casas) {
        this.casas = casas;
    }
    public int getTipo(){
        return this.tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    //Chamado atraves do tabuleiro sempre que o jogador contruir uma casa nova num terreno
    public void novaCasa(){
        this.casas++;
    }
}
