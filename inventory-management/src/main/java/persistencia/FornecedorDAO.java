package persistencia;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import negocio.Fornecedor;

public class FornecedorDAO {

    private Connection getConnection() throws SQLException {
        return Conexao.getConnection();
    }

    public void salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (id, nome, telefone, email, cidade) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getNome());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setString(5, fornecedor.getCidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar fornecedor", e);
        }
    }
}

