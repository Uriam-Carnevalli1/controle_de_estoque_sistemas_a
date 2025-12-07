package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import negocio.EntradaEstoque;

public class EntradaEstoqueDAO {

    private Connection getConnection() throws SQLException {
        return Conexao.getConnection();
    }

    // ------------------------------------------
    // SALVAR UMA NOVA ENTRADA
    // ------------------------------------------
    public void salvar(EntradaEstoque entrada) {
        String sql = "INSERT INTO entrada_estoque "
                   + "(produto_id, fornecedor_id, funcionario_id, data, quantidade, custo_total) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrada.getIdProduto());
            stmt.setInt(2, entrada.getIdFornecedor());
            stmt.setInt(3, entrada.getIdFuncionario());
            stmt.setString(4, entrada.getData().toString()); // LocalDateTime → String
            stmt.setInt(5, entrada.getQuantidade());
            stmt.setDouble(6, entrada.getCustoTotal());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar entrada de estoque", e);
        }
    }

    public void registrarEntrada(
        int idProduto,
        int idFornecedor,
        int idFuncionario,
        int quantidade,
        double custoTotal
) {

    String sql = "INSERT INTO entrada_estoque "
               + "(idProduto, idFornecedor, idFuncionario, data, quantidade, custo_total) "
               + "VALUES (?, ?, ?, NOW(), ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idProduto);
        stmt.setInt(2, idFornecedor);
        stmt.setInt(3, idFuncionario);
        stmt.setInt(4, quantidade);
        stmt.setDouble(5, custoTotal);

        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao registrar entrada de estoque", e);
    }
}

    // ------------------------------------------
    // LISTAR TODAS AS ENTRADAS
    // ------------------------------------------
    public List<EntradaEstoque> listarTodos() {
        String sql = "SELECT * FROM entrada_estoque";
        List<EntradaEstoque> lista = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EntradaEstoque entrada = new EntradaEstoque();

                entrada.setId(rs.getInt("id"));
                entrada.setIdProduto(rs.getInt("produto_id"));
                entrada.setIdFornecedor(rs.getInt("fornecedor_id"));
                entrada.setIdFuncionario(rs.getInt("funcionario_id"));
                entrada.setQuantidade(rs.getInt("quantidade"));
                entrada.setCustoTotal(rs.getDouble("custo_total"));

                // Convertendo string SQL --> LocalDateTime
                String dataStr = rs.getString("data").replace(" ", "T");
                entrada.setData(LocalDateTime.parse(dataStr));

                lista.add(entrada);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar entradas de estoque", e);
        }

        return lista;
    }

    // ------------------------------------------
    // BUSCAR UMA ENTRADA POR ID
    // ------------------------------------------
    public EntradaEstoque buscarPorId(int id) {
        String sql = "SELECT * FROM entrada_estoque WHERE id = ?";
        EntradaEstoque entrada = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                entrada = new EntradaEstoque();

                entrada.setId(rs.getInt("id"));
                entrada.setIdProduto(rs.getInt("produto_id"));
                entrada.setIdFornecedor(rs.getInt("fornecedor_id"));
                entrada.setIdFuncionario(rs.getInt("funcionario_id"));
                entrada.setQuantidade(rs.getInt("quantidade"));
                entrada.setCustoTotal(rs.getDouble("custo_total"));

                String dataStr = rs.getString("data").replace(" ", "T");
                entrada.setData(LocalDateTime.parse(dataStr));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entrada de estoque por ID", e);
        }

        return entrada;
    }

    // ------------------------------------------
    // DELETAR UMA ENTRADA
    // ------------------------------------------
    public void deletar(int id) {
        String sql = "DELETE FROM entrada_estoque WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar entrada de estoque", e);
        }
    }
}
