public class Jogador{
    private int id;
    private int posicao;
    private boolean preso;
    private int rodadasBloqueado;
    private int contaBancaria;
    private boolean falido;
    private int[] propriedades;
    private boolean permissao;

    public Jogador(int id){
        this.id = id;
        this.posicao = 0;
        this.preso = false;
        this.rodadasBloqueado = 0;
        this.contaBancaria = id;
        this.falido = false;
        this.permissao = false;
        this.propriedades = new int[9];
        for (int i = 0; i < 9; i++)
            propriedades[i] = 0;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPosicao(int posicao){
        this.posicao = posicao;
    }
    public void updatePosicao(int posicao){
        this.posicao = (this.posicao + posicao) % 40;
    }
    public int getPosicao(){
        return this.posicao;
    }
    public void setPreso(boolean preso){
        this.preso = preso;
    }
    public boolean estaPreso(){
        return this.preso;
    }
    public void setPermissao(boolean permissao){
        this.permissao = permissao;
    }
    public boolean temPermissao(){
        return permissao;
    }
    public void setFalido(boolean falido){
        this.falido = falido;
    }
    public boolean estaFalido(){
        return falido;
    }
    public void setRodadasBloqueado(int x){
        this.rodadasBloqueado = x;
    }
    public void updateRodadasBloqueado(){
        this.rodadasBloqueado--;
    }
    public int getRodadasBloqueado(){
        return this.rodadasBloqueado;
    }
    public void setConta(int conta){
        this.contaBancaria = conta;
    }
    public int getConta(){
        return this.contaBancaria;
    }
    public void novoTerreno(int tipo){
        propriedades[tipo]++;
    }
    public int getNumPropriedades(int tipo){
        return propriedades[tipo];
    }
    public void setNumPropriedade(int tipo, int qtd){
        propriedades[tipo] = qtd;
    }
}
