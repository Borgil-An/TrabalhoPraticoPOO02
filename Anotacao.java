
package trabalhodepoo2;


public class Anotacao implements Comparable<Anotacao> {
    private String titulo;
    private String texto;
    private Data data;

    public Anotacao(String titulo, String texto, Data data) {
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        Data.getDataAtual();
    }
    
    public void setDataCriacao(Data dataCriacao) {
    }
    
    public Data getData() {
        return data;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int compareTo(Anotacao o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}    

    

