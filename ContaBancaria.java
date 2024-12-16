public class ContaBancaria {
    private int saldo;

    public ContaBancaria(){
        this.saldo = 2558000;
    }

    //Esse metodo so deve ser utilizado na classe Carregar para inicializar as contas
    //com valores salvos, sua chamada se logo apos o banco.getConta da classe banco
    public void setSaldo(int saldo){
        this.saldo = saldo;
    }

    public int getSaldo(){
        return this.saldo;
    }

    public void adicionaSaldo(int saldo){
        this.saldo += saldo;
    }

    public void removeSaldo(int saldo){
        this.saldo -= saldo;
    }
}
