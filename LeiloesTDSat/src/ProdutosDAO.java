import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
    conn = new conectaDAO().connectDB(); // Note o "c" minúsculo

    if (conn != null) {
        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
    }
}
    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB(); // Conecta ao banco de dados
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        if (conn != null) {
            try {
                // Query SQL para buscar todos os produtos
                String sql = "SELECT * FROM produtos";
                prep = conn.prepareStatement(sql);
                resultset = prep.executeQuery();

                // Percorre os resultados e adiciona na lista
                while (resultset.next()) {
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setValor(resultset.getInt("valor"));
                    produto.setStatus(resultset.getString("status"));
                    listagem.add(produto);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
            } finally {
                try {
                    if (resultset != null) resultset.close();
                    if (prep != null) prep.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
        }

        return listagem; // Retorna a lista de produtos
    }
    public void venderProduto(int id) {
    conn = new conectaDAO().connectDB(); // Conecta ao banco de dados

    if (conn != null) {
        try {
            // Query SQL para atualizar o status do produto
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id); // Define o ID do produto
            prep.executeUpdate(); // Executa a atualização

            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
    }
}
}