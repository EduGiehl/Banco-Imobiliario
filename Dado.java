public class Dado {
    private int valor;

    public Dado(){
        rolaDado();
    };

    //Atualiza o valor com uma numero aleatorio ente 1 e 6
    public void rolaDado(){
        this.valor = (1+(int)(Math.random()*6));
    }

    //Retorna o valor do dado
    public int getValor(){
        return this.valor;
    }
}
