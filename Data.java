
package trabalhodepoo1;


public class Data {
    private final int dia;
    private final int mes;
    private final int ano;
    private static final String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril",
                                           "Maio", "Junho", "Julho", "Agosto",
                                           "Setembro", "Outubro", "Novembro", "Dezembro"};
    
    public Data(int dia, int mes, int ano) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }
    
    public int getMes() {
        return mes;
    }
    
    public int getAno() {
        return ano;
    }
    
    @Override
    public String toString() {
        return (dia < 10 ? "0"+dia : dia) + "/" + (mes < 10 ? "0"+mes : mes) + "/" + ano;
    }
    
    public String porExtenso() {
        return (dia < 10 ? "0"+dia : dia) + " de " + nomeDoMes(mes) + " de " + ano;
    }
    
    public static String nomeDoMes(int mes) {
        if (mes > 0 && mes < 13)
            return meses[mes-1];
        return null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {  
           return true;  
        }  
        if (o instanceof Data) {  
           Data outraData = (Data) o;  
           if (this.ano == outraData.ano && this.mes == outraData.mes && this.dia == outraData.dia) {  
           return true;  
           }  
        }  
       return false;              
    }

    public int compareTo(Data outraData) {
    if (this.ano != outraData.ano) {
        return Integer.compare(this.ano, outraData.ano);
    }
    if (this.mes != outraData.mes) {
        return Integer.compare(this.mes, outraData.mes);
    }
    return Integer.compare(this.dia, outraData.dia);
    }

    public static Data getDataAtual() {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(date);
        String[] partes = dataFormatada.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return new Data(dia, mes, ano);
    }
}
