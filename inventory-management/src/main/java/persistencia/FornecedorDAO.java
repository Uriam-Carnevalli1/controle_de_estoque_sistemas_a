package persistencia;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    public List<Fornecedor> listarTodos() {
    String sql = "SELECT * FROM fornecedor";
    List<Fornecedor> lista = new ArrayList<>();

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Fornecedor f = new Fornecedor();
            f.setId(rs.getInt("id"));
            f.setNome(rs.getString("nome"));
            f.setTelefone(rs.getString("telefone"));
            f.setEmail(rs.getString("email"));
            f.setCidade(rs.getString("cidade"));
            lista.add(f);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar fornecedores", e);
    }

    return lista;
}
    public void deletar(int id) {
    String sql = "DELETE FROM fornecedor WHERE id = ?";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao deletar fornecedor", e);
    }
}
    
    

}

