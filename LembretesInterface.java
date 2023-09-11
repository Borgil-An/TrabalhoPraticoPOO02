
package trabalhodepoo1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class LembretesInterface extends JFrame {
    private AnotacoesApp anotacoesApp;
    private JTextArea textoAnotacao;
    private JTextField campoTitulo;
    private JList<String> listaAnotacoes;
    private DefaultListModel<String> listModel;

    public LembretesInterface() {
        
        anotacoesApp = new AnotacoesApp();
        listModel = new DefaultListModel<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sua Agenda");
        setSize(800, 580);
        setLayout(new BorderLayout());

        listaAnotacoes = new JList<>(listModel);
        listaAnotacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAnotacoes.addListSelectionListener(e -> exibirAnotacaoSelecionada());
        JScrollPane listaScrollPane = new JScrollPane(listaAnotacoes);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 6)); 
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(e -> exibirDialogoAdicao());
        JButton botaoRemover = new JButton("Remover");
        botaoRemover.addActionListener(e -> removerAnotacao());
        JButton botaoEditar = new JButton("Editar");
        botaoEditar.addActionListener(e -> editarAnotacao());
        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(e -> encerrarPrograma());
        JButton botaoOrdenarData = new JButton("Ordenar por Data");
        botaoOrdenarData.addActionListener(e -> ordenarPorData());
        JButton botaoOrdenarTitulo = new JButton("Ordenar por Título");
        botaoOrdenarTitulo.addActionListener(e -> ordenarPorTitulo());

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoSair);
        painelBotoes.add(botaoOrdenarData);
        painelBotoes.add(botaoOrdenarTitulo);

        campoTitulo = new JTextField();
        campoTitulo.setPreferredSize(new Dimension(200, 30));
        textoAnotacao = new JTextArea();
        JScrollPane textoScrollPane = new JScrollPane(textoAnotacao);

        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BorderLayout());
        painelFormulario.add(campoTitulo, BorderLayout.NORTH);
        painelFormulario.add(textoScrollPane, BorderLayout.CENTER);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(listaScrollPane, BorderLayout.WEST);
        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }
    
    private void exibirDialogoAdicao() {
        
        JDialog dialogo = new JDialog(this, "Adicionar Anotação", true);
        JTextField tituloAnotacao = new JTextField(20);
        JTextArea lore = new JTextArea(10, 30);
        JTextField campoDia = new JTextField(2);
        JTextField campoMes = new JTextField(2);
        JTextField campoAno = new JTextField(4);
        JButton botaoSalvar = new JButton("Salvar");
        JButton botaoCancelar = new JButton("Cancelar");

        botaoSalvar.addActionListener(e -> {
            String titulo = tituloAnotacao.getText();
            String texto = lore.getText();
            String diaTexto = campoDia.getText();
            String mesTexto = campoMes.getText();
            String anoTexto = campoAno.getText();

            if (!titulo.isEmpty() && !texto.isEmpty() && !diaTexto.isEmpty() && !mesTexto.isEmpty() && !anoTexto.isEmpty()) {
                try {
                    int dia = Integer.parseInt(diaTexto);
                    int mes = Integer.parseInt(mesTexto);
                    int ano = Integer.parseInt(anoTexto);

                    if (validarData(dia, mes, ano)) {
                        Data data = new Data(dia, mes, ano);
                        Anotacao anotacao = new Anotacao(titulo, texto, data);
                        anotacoesApp.adicionarAnotacao(anotacao);
                        listModel.addElement(titulo);
                        dialogo.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialogo, "Data inválida.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialogo, "Insira somente números válidos.");
                }
            } else {
                JOptionPane.showMessageDialog(dialogo, "Preencha todos os campos obrigatórios.");
                   }
        });

        botaoCancelar.addActionListener(e -> {
            dialogo.dispose();
        });

        JPanel painelDialogo = new JPanel();
        painelDialogo.setLayout(new GridLayout(6, 1)); 
        painelDialogo.add(tituloAnotacao);
        painelDialogo.add(lore);
        painelDialogo.add(new JLabel("Dia:"));
        painelDialogo.add(campoDia);
        painelDialogo.add(new JLabel("Mês:"));
        painelDialogo.add(campoMes);
        painelDialogo.add(new JLabel("Ano:"));
        painelDialogo.add(campoAno);

        JPanel painelBotoesDialogo = new JPanel();
        painelBotoesDialogo.add(botaoSalvar);
        painelBotoesDialogo.add(botaoCancelar);

        dialogo.add(painelDialogo, BorderLayout.CENTER);
        dialogo.add(painelBotoesDialogo, BorderLayout.SOUTH);
        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void removerAnotacao() {
        int indiceSelecionado = listaAnotacoes.getSelectedIndex();
        if (indiceSelecionado != -1) {
            anotacoesApp.removerAnotacao(indiceSelecionado);
            listModel.removeElementAt(indiceSelecionado);
            limparCampos();
        }
    }

    private void editarAnotacao() {
        int indiceSelecionado = listaAnotacoes.getSelectedIndex();
        if (indiceSelecionado != -1) {
            String novoTitulo = campoTitulo.getText();
            String novoTexto = textoAnotacao.getText();
            Anotacao anotacao = anotacoesApp.getAnotacoes().get(indiceSelecionado);
            anotacao.setTitulo(novoTitulo);
            anotacao.setTexto(novoTexto);
              atualizarListaAnotacoes();
        }
    }

    private void exibirAnotacaoSelecionada() {
        int indiceSelecionado = listaAnotacoes.getSelectedIndex();
        if (indiceSelecionado != -1) {
            String tituloAnotacaoSelecionada = listModel.getElementAt(indiceSelecionado);
            campoTitulo.setText(tituloAnotacaoSelecionada);
            Anotacao anotacao = anotacoesApp.getAnotacoes().get(indiceSelecionado);
            textoAnotacao.setText(anotacao.getTexto());
        }
    }

    private void encerrarPrograma() {
        System.exit(0);
    }

    private void limparCampos() {
        campoTitulo.setText("");
        textoAnotacao.setText("");
    }

public void ordenarPorData() {
    Collections.sort(anotacoesApp.getAnotacoes(), (Anotacao a1, Anotacao a2) -> {
        Data data1 = a1.getData();
        Data data2 = a2.getData();

        if (data1 == null && data2 == null) {
            return 0;
        } else if (data1 == null) {
            return 1; 
        } else if (data2 == null) {
            return -1; 
        } else {
            return data1.compareTo(data2);
        }
    });

    atualizarListaAnotacoes();
}
   

    private void ordenarPorTitulo() {
        anotacoesApp.ordenarPorTitulo();
        atualizarListaAnotacoes(); 
    }

    private void atualizarListaAnotacoes() {
        listModel.clear();
        for (Anotacao anotacao : anotacoesApp.getAnotacoes()) {
            listModel.addElement(anotacao.getTitulo());
        }
    }

    private boolean validarData(int dia, int mes, int ano) {
        return (dia > 0 && dia <= 31) && (mes >= 1 && mes <= 12);
    }

    public void adicionarAnotacao(Anotacao anotacao) {
        listModel.addElement(anotacao.getTitulo());
        anotacoesApp.adicionarAnotacao(anotacao);
    }

}
