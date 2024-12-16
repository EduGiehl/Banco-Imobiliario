//Classe singleton responsavel por operações entre contas e mascarar o ascesso a classe modelo ContaBancaria
public class Banco {
    private static Banco banco;
    private ContaBancaria[] contas;

    private Banco(){
        this.contas = new ContaBancaria[4];
        for (int i = 0; i < contas.length; i++) {
            contas[i] = new ContaBancaria();
        }
    }
    public static synchronized Banco getBanco(){
        if (banco == null)
            banco = new Banco();
        return banco;
    }

    //Metodo usado somante da classe Carregar para inicializar as contas com dados salvos
    //Para qualquer outra parte do codigo acessar atravez dos outros metodos
    public ContaBancaria getConta(int conta){
        return contas[conta];
    }

    //recebe uma conta e retorna o saldo dela
    public int extrato(int conta){
        return getConta(conta).getSaldo();
    }

    //recebe uma conta de origem e uma de destino alem de um valor e remove o valor
    //da conta de origem e adiciona na conta de destino, retorna true em caso de sucesso
    //e false em caso de a conta de origem nao possuir saldo o bastante
    //OBS: esse metodo deve ser chamado sempre que for feito o pagamento de um aluguel ou
    //negociacao entre jogadores
    public boolean transferencia(int origem, int destino, int valor){
        boolean sucesso = false;
        ContaBancaria conta = getConta(origem);
        if (conta.getSaldo() >= valor) {
            conta.removeSaldo(valor);
            conta = getConta(destino);
            conta.adicionaSaldo(valor);
            sucesso = true;
        }
        return sucesso;
    }

    //Metodo a ser chamado sempre que um jogador passar pelo terreno inicial do tabuleiro
    //recebe uma conta e adiciona 200 nela
    public void recebeHonorario(int numConta){
        ContaBancaria conta = getConta(numConta);
        conta.adicionaSaldo(200);
    }

    //Recebe uma conta e um valor e remove o valor da conta, retorna true em caso de sucesso
    //e false caso a conta possua saldo insuficiente
    //OBS: esse metodo deve ser chamado sempre que o jogador realizar alguma compra que nao
    //involva outro jogador, ex: copra de terreno ou contrucao de casa em terreno ou perda
    //no sorte ou reves
    public boolean realizaPagamento(int numConta, int valor){
        boolean suceso = false;
        ContaBancaria conta = getConta(numConta);
        if (conta.getSaldo() >= valor) {
            conta.removeSaldo(valor);
            suceso = true;
        }
        return suceso;
    }

    //Recebe uma conta e um valor e adiciona o valor na conta
    //OBS: esse metodo devera ser chamado sepre que o jogador fizer ipoteca ou ganhar algo
    //no sorte ou reves
    public void recebePagamento(int numConta, int valor){
        ContaBancaria conta = getConta(numConta);
        conta.adicionaSaldo(valor);
    }
}
