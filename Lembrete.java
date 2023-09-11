
package trabalhodepoo1;

public class Lembrete implements Comparable<Lembrete> {
    private String descricao;
    private int dia;
    private Data data;

    public Lembrete(String descricao, Data data) {
        this.descricao = descricao;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDia() {
        return dia;
    }
    
    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return dia + " - " + descricao + "\n";
    }

    @Override
    public int compareTo(Lembrete outro) {
     return this.data.compareTo(outro.data);
    }
}
