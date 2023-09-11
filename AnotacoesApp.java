
package trabalhodepoo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnotacoesApp {
    private List<Anotacao> anotacoes;

    public AnotacoesApp() {
        this.anotacoes = new ArrayList<>();
    }

    public void adicionarAnotacao(Anotacao anotacao) {
        anotacoes.add(anotacao);
    }

    public void removerAnotacao(int indice) {
        if (indice >= 0 && indice < anotacoes.size()) {
            anotacoes.remove(indice);
        }
    }

    public void editarAnotacao(int indice, Anotacao novaAnotacao) {
        if (indice >= 0 && indice < anotacoes.size()) {
            Anotacao anotacaoExistente = anotacoes.get(indice);
            anotacaoExistente.setTitulo(novaAnotacao.getTitulo());
            anotacaoExistente.setTexto(novaAnotacao.getTexto());
        }
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void ordenarPorData() {
        Collections.sort(anotacoes, (a1, a2) -> a1.getData().compareTo(a2.getData()));
    }

    public void ordenarPorTitulo() {
        Collections.sort(anotacoes, (a1, a2) -> a1.getTitulo().compareToIgnoreCase(a2.getTitulo()));
    }

  
    
    
} 

