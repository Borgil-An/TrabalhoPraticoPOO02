
package trabalhodepoo1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BlocoDeLembretes {
    private List<Lembrete> lista;

    public BlocoDeLembretes() {
        lista = new ArrayList<>();
    }

    public void inserir(Lembrete l) {
        lista.add(l);
    }

   
    public static Lembrete remover(BlocoDeLembretes bloco, int posicao) {
        return bloco.getLembretes().remove(posicao);
    }

    public Lembrete buscar(int posicao) {
        return lista.get(posicao);
    }

    public List<Lembrete> buscarPorPalavra(String substring) {
        List<Lembrete> resultados = new ArrayList<>();
        for (Lembrete lembrete : lista) {
            if (lembrete.getDescricao().contains(substring)) {
                resultados.add(lembrete);
            }
        }
        return resultados;
    }

    public List<Lembrete> buscarPorMes(int mes) {
        List<Lembrete> resultados = new ArrayList<>();
        for (Lembrete lembrete : lista) {
            if (lembrete.getData().getMes() == mes) {
                resultados.add(lembrete);
            }
        }
        return resultados;
    }

    public List<Lembrete> buscarPorDia(int dia){
        List<Lembrete> resultados = new ArrayList<>();
        for (Lembrete lembrete : lista) {
            if (lembrete.getData().getDia() == dia) {
                resultados.add(lembrete);
            }
        }
        return resultados;
    }

    public void ordenar(){
    lista.sort(new Comparator<Lembrete>() {
        @Override
        public int compare(Lembrete lembrete1, Lembrete lembrete2) {
            return lembrete1.getData().compareTo(lembrete2.getData());
        }
    }); 
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            sb.append((i + 1)).append(" - ").append(lista.get(i)).append("\n");
        }
        return sb.toString();
    }
    
    public List<Lembrete> getLembretes() {
           ordenar();
        return lista;
    }

}

