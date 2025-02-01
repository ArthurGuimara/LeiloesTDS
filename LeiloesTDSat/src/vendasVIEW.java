import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends javax.swing.JFrame {

    public vendasVIEW() {
        initComponents(); // Inicializa os componentes da interface
        listarProdutosVendidos(); // Carrega os produtos vendidos na tabela
    }

    private void initComponents() {
        // Componentes da interface
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();

        // Configurações da janela
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos Vendidos");

        // Configurações da tabela
        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, // Dados da tabela (inicialmente vazios)
            new String [] { // Nomes das colunas
                "ID", "Nome", "Valor", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabelaVendas); // Adiciona a tabela ao JScrollPane

        // Layout da janela
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack(); // Ajusta o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();

            // Obtém a lista de produtos vendidos
            ArrayList<ProdutosDTO> listagemVendidos = produtosdao.listarProdutosVendidos();

            // Preenche a tabela com os produtos vendidos
            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setNumRows(0); // Limpa a tabela

            for (ProdutosDTO produto : listagemVendidos) {
                model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        }
    }

    // Variáveis de componentes
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaVendas;

    // Método main para teste (opcional)
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vendasVIEW().setVisible(true);
            }
        });
    }
}