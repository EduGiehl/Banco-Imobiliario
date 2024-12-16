

public class Sistema {
    private static Sistema instancia;
    private Tabuleiro tabuleiro;
    private Banco banco;
    private Jogador[] jogadores;
    private int proxJogador;
    private Jogador jogadorAtual;
    private int jogadoresVivos;
    private Dado dado1;
    private Dado dado2;

    private Sistema(){
        tabuleiro = Tabuleiro.getTabuleiro();
        banco = Banco.getBanco();
        dado1 = new Dado();
        dado2 = new Dado();
        proxJogador = 0;
        jogadoresVivos = 4;
        jogadores = new Jogador[4];
        for (int i = 0; i < jogadores.length; i++)
            jogadores[i] = new Jogador(i);
        jogadorAtual = jogadores[0];
    }

    public static synchronized Sistema getSistema(){
        if (instancia == null)
            instancia = new Sistema();
        return instancia;
    }

    public int getJogadoresVivos() {
        return jogadoresVivos;
    }

    public void atualizaJogadoresVivos() {
        this.jogadoresVivos--;
    }

    public void setProxJogador(int proxJogador) {
        this.proxJogador = proxJogador;
    }

    public int getProxJogador() {
        return proxJogador;
    }

    public Jogador getJogador(int id){
        return jogadores[id];
    }

    public void setJogadorAtual(Jogador jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    //Retorna o prox jogador da lista ciclica
    private Jogador proximoJogador(){
        int atual = proxJogador;
        proxJogador = (proxJogador + 1) % 4;
        setJogadorAtual(jogadores[atual]);
        return getJogadorAtual();
    }

    public int getVencedor(){
        Jogador jogador;
        int i = 0;
        jogador = getJogador(i);
        while(jogador.estaFalido()){
            i++;
            jogador = getJogador(i);
        }
        return jogador.getId();
    }

    //Recebe o id de umjogador e retorna a posicao dele no tabuleiro
    public int verificaPosicao(int jogador){
        return jogadores[jogador].getPosicao();
    }

    //Recebe um jogador e um numero, e atualiza a posicao do jogador,
    //chamando Evento.movimenta para colocar o peao na posicao correta
    //na representacao grafica do tabuleiro
    public void moveJogador(Jogador jogador, int posicao){
        jogador.updatePosicao(posicao);
        Tela.playerMovimenta(jogador.getId(), jogador.getPosicao());
    }

    //Metodo atualiza os atributos do jogador
    public void prendeJogador(Jogador jogador){
        jogador.setPreso(true);
        jogador.setRodadasBloqueado(3);
        Tela.mensagem("Jogador " + jogador.getId() + " foi preso.");
    }


    //Metodo rola dois dados 3 vezes ou ate que deem valores distintos
    public void rolaDados(){
        int rolagens = 0;
        do {
            dado1.rolaDado();
            dado2.rolaDado();
            Tela.mostraDados(dado1.getValor(), dado2.getValor());
            rolagens++;
        } while (dado1.getValor() == dado2.getValor() && rolagens < 3);
    }

    //Metodo chamado pela main caso o usuario clique em novo jogo no menu
    public void novoJogo(){
        tabuleiro.inicializa();
    }

    //Metodo chamado pela main caso o usuario clique em carregar jogo no menu
    public boolean carregarJogo(){
        if(Carregar.carregaTabuleiro()){
            if(Carregar.carregaBanco())
                if(Carregar.carregaJogador())
                    return true;
        }
        return false;
    }

    public void salvarJogo(){
        Salvar.salvaTabuleiro();
        Salvar.salvaContas();
        Salvar.salvaJogadores();
    }


    public boolean pagaAluguelCasa(Jogador pagador){
        Jogador destino = getJogador(tabuleiro.verificaProprietario(pagador.getPosicao()));
        int valor = tabuleiro.verificaAluguel(pagador.getPosicao());
        return banco.transferencia(pagador.getConta(), destino.getConta(), valor);
    }

    public boolean pagaAluguelCompanhia(Jogador pagador){
        Jogador destino = getJogador(tabuleiro.verificaProprietario(pagador.getPosicao()));
        int valor = tabuleiro.verificaAluguel(pagador.getPosicao());
        valor *= (dado1.getValor() + dado2.getValor());
        return banco.transferencia(pagador.getConta(), destino.getConta(), valor);
    }

    public void recebeImposto(Jogador jogador){
        banco.recebePagamento(jogador.getConta(), 200000);
    }

    public boolean pagaImposto(Jogador jogador){
        return banco.realizaPagamento(jogador.getConta(), 200000);
    }

    public boolean tentaFugir(Jogador jogador){
        dado1.rolaDado();
        dado2.rolaDado();
        Tela.mostraDados(dado1.getValor(), dado2.getValor());
        if (jogador.temPermissao() || dado1.getValor() == dado2.getValor()) {
            jogador.setPermissao(false);
            jogador.setPreso(false);
            jogador.setRodadasBloqueado(0);
            Tela.mensagem("Jogador " + jogador.getId() +" fugiu da prisao.");

            return true;
        }
        return false;
    }

    public void sorteReves(Jogador jogador){
        int sorteio = (1+(int)(Math.random()*32));
        
        Tela.mostraSorte(sorteio);


        switch(sorteio) {
            // sorte
            case 1:
                banco.recebePagamento(jogador.getConta(), 5000);
            case 2:
                moveJogador(jogador, 3);
            case 3:
                banco.recebePagamento(jogador.getConta(), 1000);
            case 4:
                banco.recebePagamento(jogador.getConta(), 2000);
            case 5:
                moveJogador(jogador, 1);
            case 6:
                banco.recebePagamento(jogador.getConta(), 20000);
            case 7:
                banco.recebePagamento(jogador.getConta(), 20000);
            case 8:
                banco.recebePagamento(jogador.getConta(), 30000);
            case 9:
                banco.recebePagamento(jogador.getConta(), 2000);
            case 10:
                banco.recebePagamento(jogador.getConta(), 15000);
            case 11:
                banco.recebePagamento(jogador.getConta(), 5000);
            case 12:
                banco.recebePagamento(jogador.getConta(), 1000);
            case 13:
                banco.recebePagamento(jogador.getConta(), 1000);
            case 14:
                banco.recebePagamento(jogador.getConta(), 2000);
            case 15:
                banco.recebePagamento(jogador.getConta(), 5000);
            case 16:
                moveJogador(jogador, 4);

            // reves
            case 17:
                banco.realizaPagamento(jogador.getConta(), 1000);
            case 18:
                banco.realizaPagamento(jogador.getConta(), 1000);
            case 19:
                banco.realizaPagamento(jogador.getConta(), 700);
            case 20:
                banco.realizaPagamento(jogador.getConta(), 1200);
            case 21:
                banco.realizaPagamento(jogador.getConta(), 1200);
            case 22:
                banco.realizaPagamento(jogador.getConta(), 10000);
            case 23:
                banco.realizaPagamento(jogador.getConta(), 5000);
            case 24:
                banco.realizaPagamento(jogador.getConta(), 5000);
            case 25:
                banco.realizaPagamento(jogador.getConta(), 1500);
            case 26:
                banco.realizaPagamento(jogador.getConta(), 25000);
            case 27:
                banco.realizaPagamento(jogador.getConta(), 5000);
            case 28:
                banco.realizaPagamento(jogador.getConta(), 10000);
            case 29:
                banco.realizaPagamento(jogador.getConta(), 8000);
            case 30:
                banco.realizaPagamento(jogador.getConta(), 10000);
            case 31:
                banco.realizaPagamento(jogador.getConta(), 5000);
            case 32:
                banco.realizaPagamento(jogador.getConta(), 20000);
        }
    }

    public void compra(){
        Jogador jogador = getJogadorAtual();
        int valor = tabuleiro.verificaPreco(jogador.getPosicao());
        if(!banco.realizaPagamento(jogador.getConta(), valor)){
            Tela.mensagem("Jogador " + jogador.getId() + " Saldo insuficiente");
            }
        else {
            tabuleiro.atualizaProprietario(jogador.getId(), jogador.getPosicao());
            jogador.novoTerreno(tabuleiro.verificaTipo(jogador.getPosicao()));
            Tela.mensagem("Jogador " + jogador.getId() +" comprou terreno " + jogador.getPosicao());

        }
    }


    public void evento(Jogador jogador){
        switch (tabuleiro.verificaTipo(jogador.getPosicao())) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                if (tabuleiro.verificaProprietario(jogador.getPosicao()) == -1){
                    Tela.mostraOptCompra(jogador.getPosicao());
                }else{
                    if (tabuleiro.verificaProprietario(jogador.getPosicao()) != jogador.getId()) {
                        if (!pagaAluguelCasa(jogador)){
                            jogador.setFalido(true);
                            atualizaJogadoresVivos();
                            Tela.mensagem("Jogador " + jogador.getId() + " foi a falencia.");
                        }
                        Tela.mensagem("Jogador "+ jogador.getId() +" pagou "
                                + tabuleiro.verificaAluguel(jogador.getId()) +" ao jogador " 
                                    + tabuleiro.verificaProprietario(jogador.getPosicao()));
                    }else{
                        Tela.construirCasa(jogador.getPosicao());
                    }
                }
                break;

            case 8:
                if (tabuleiro.verificaProprietario(jogador.getPosicao()) == -1) {
                    Tela.mostraOptCompra(jogador.getPosicao());
                }else{
                    if (tabuleiro.verificaProprietario(jogador.getPosicao()) != jogador.getId()) {
                        if (!pagaAluguelCompanhia(jogador)){
                            jogador.setFalido(true);
                            atualizaJogadoresVivos();
                            Tela.mensagem("Jogador " + jogador.getId() + " foi a falencia.");
                        }
                        Tela.mensagem("Jogador "+ jogador.getId() +" pagou "
                                + tabuleiro.verificaAluguel(jogador.getId()) +" ao jogador " 
                                    + tabuleiro.verificaProprietario(jogador.getPosicao()));
                    }
                }
                break;

            case 9:
                sorteReves(jogador);
                break;

            case 10:
                if (!pagaImposto(jogador)){
                    jogador.setFalido(true);
                    atualizaJogadoresVivos();
                    Tela.mensagem("Jogador " + jogador.getId() + " foi a falencia.");
                }
                Tela.mensagem("Jogador " + jogador.getId() + " pagou imposto.");
                break;

            case 11:
                recebeImposto(jogador);
                Tela.mensagem("Jogador " + jogador.getId() + " recebeu imposto.");
                break;

            case 12:
                jogador.setPosicao(10);
                Tela.playerMovimenta(jogador.getId(), jogador.getPosicao());


            case 13:
                prendeJogador(jogador);
                Tela.mensagem("Jogador " + jogador.getId() + " foi preso.");
                break;

            case 14: //caso do Feriado
                Tela.mensagem("Jogador "+ jogador.getId() +" esta de ferias");
                break;

            case 15:
                banco.recebeHonorario(jogador.getConta());
                Tela.mensagem("Jogador " + jogador.getId() + " recebe honorario");
                break;

            default:
                break;
        }

    }

    public void construcao(){
        Jogador jogador = getJogadorAtual();
        if (tabuleiro.podeConstruir(jogador.getPosicao(), tabuleiro.verificaCasas(jogador.getPosicao()))){
            int valor = tabuleiro.verificaPrecoCasa(jogador.getPosicao());
            if (!banco.realizaPagamento(jogador.getConta(), valor)){
                Tela.mensagem("Jogador " + jogador.getId() +" Saldo insuficiente");
            }
            else{
                tabuleiro.constroiCasa(jogador.getPosicao());
                Tela.novaCasa(jogadorAtual.getPosicao());
            }
        }
        Tela.mensagem("Jogador " + jogador.getId() +" Constuiu uma casa.");
    }

    //Metodo pega o proximo jogador e rola os dados para ele, o
    //movimenta/prende e com base na posicao do jogador chama
    //um evento especifico para mostrar as opcoes de acao do jogador
    //ou para mostrar a ele o que esta acontecendo, se for evento de
    //escolha retorna ao main um int representando a escolha do jogador
    //Ainda ta incompleto e precisa pensar em todos os casos
    public void proximoTurno(){
        Jogador jogador;
        do {
            jogador = proximoJogador();
        } while (jogador.estaFalido());
        Tela.mensagem("Turno do jogador " + jogador.getId() + "\nSaldo: " + banco.extrato(jogador.getConta()));
        if (jogador.estaPreso()) {
            if (!tentaFugir(jogador)) {
                jogador.updateRodadasBloqueado();
                if (jogador.getRodadasBloqueado() != 0){
                    Tela.mensagem("Jogador " + jogador.getId() + " continua preso.");
                    return;
                }
                if (!banco.realizaPagamento(jogador.getConta(), 50000)){
                    jogador.setFalido(true);
                    atualizaJogadoresVivos();
                    Tela.mensagem("Jogador " + jogador.getId() + " foi a falencia.");
                    return;
                }
                jogador.setPreso(false);
                Tela.mensagem("Jogador " + jogador.getId() + " saiu da prisao.");

            }
        }
        rolaDados();
        if (dado1.getValor() == dado2.getValor()) {
            jogador.setPosicao(10);
            Tela.playerMovimenta(jogador.getId(), jogador.getPosicao());
            prendeJogador(jogador);
            //Tela.mensagem("Jogador " + jogador.getId() + " foi preso.");
            return;
        }
        moveJogador(jogador, dado1.getValor() + dado2.getValor());
        evento(jogador);
    }
}
