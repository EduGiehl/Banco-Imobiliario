import java.util.ArrayList;
import java.util.List;

public class ListaCasas{
    private List<Casa> casas; // Lista que armazena todas as casas

    public ListaCasas() {
        casas = new ArrayList<>();
        inicializarCasas();
    }

    // Inicializa as casas (você pode modificar isso para ter as casas reais do seu jogo)
    private void inicializarCasas() {
        casas.add(new Casa(0, 830, 823));
        casas.add(new Casa(1, 758, 823));
        casas.add(new Casa(2, 686, 823));
        casas.add(new Casa(3, 614, 823));
        casas.add(new Casa(4, 542, 823));
        casas.add(new Casa(5, 470, 823));
        casas.add(new Casa(6, 398, 823));
        casas.add(new Casa(7, 326, 823));
        casas.add(new Casa(8, 254, 823));
        casas.add(new Casa(9, 182, 823));
        casas.add(new Casa(10, 0, 823));
        casas.add(new Casa(11, 0, 751));
        casas.add(new Casa(12, 0, 679));
        casas.add(new Casa(13, 0, 607));
        casas.add(new Casa(14, 0, 535));
        casas.add(new Casa(15, 0, 463));
        casas.add(new Casa(16, 0, 391));
        casas.add(new Casa(17, 0, 319));
        casas.add(new Casa(18, 0, 247));
        casas.add(new Casa(19, 0, 175));
        casas.add(new Casa(20, 0, 0));
        casas.add(new Casa(21, 175, 0));
        casas.add(new Casa(22, 247, 0));
        casas.add(new Casa(23, 319, 0));
        casas.add(new Casa(24, 391, 0));
        casas.add(new Casa(25, 463, 0));
        casas.add(new Casa(26, 535, 0));
        casas.add(new Casa(27, 607, 0));
        casas.add(new Casa(28, 679, 0));
        casas.add(new Casa(29, 751, 0));
        casas.add(new Casa(30, 823, 0));
        casas.add(new Casa(31, 823, 175));
        casas.add(new Casa(32, 823, 247));
        casas.add(new Casa(33, 823, 319));
        casas.add(new Casa(34, 823, 391));
        casas.add(new Casa(35, 823, 463));
        casas.add(new Casa(36, 823, 535));
        casas.add(new Casa(37, 823, 607));
        casas.add(new Casa(38, 823, 679));
        casas.add(new Casa(39, 823, 751));
        casas.add(new Casa(39, 823, 823));

        // Adicione quantas casas precisar
    }

    

    //Retorna um elemento da lista casa
    public Casa getCasaId(int id){
        return casas.get(id);
    }

    // Retorna o número total de casas no tabuleiro
    public int getTotalCasas() {
        return casas.size();
    }

    public Casa getCasa(int index) {
        if (index >= 0 && index < casas.size()) {
            return casas.get(index);  // Usando casas.get(index) para acessar elementos em uma lista
        }
        return null; // Retorna nulo se o índice for inválido
    }



}
