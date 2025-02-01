import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;

        try {
            // URL de conexão com o banco de dados (usando a porta 3306)
            String url = "jdbc:mysql://localhost:3306/uc11?user=root&password=root";
            conn = DriverManager.getConnection(url);

            JOptionPane.showMessageDialog(null, "Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + erro.getMessage());
        }
        return conn;
    }
}