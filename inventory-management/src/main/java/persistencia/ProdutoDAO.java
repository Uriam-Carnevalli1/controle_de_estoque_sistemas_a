package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Produto;

public class ProdutoDAO implements BasicoDAO {

    private Connection getConnection() throws SQLException {
        return Conexao.getConnection();
    }

    @Override
    public void salvar(Object bean) {
        Produto p = (Produto) bean;

        String sql = "INSERT INTO produto (nome, descricao, preco, estoque_minimo) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getEstoqueMinimo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    @Override
    public void atualizar(Object bean) {
        Produto p = (Produto) bean;

        String sql = "UPDATE produto SET nome=?, descricao=?, quantidade=?, preco=?, estoque_minimo=? "
                   + "WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoqueMinimo());
            stmt.setInt(6, p.getIdProduto());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }
    public void adicionarEstoque(int idProduto, int quantidadeAdicionar) {
    String sql = "UPDATE produto SET quantidade = quantidade + ? WHERE id = ?";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, quantidadeAdicionar);
        stmt.setInt(2, idProduto);
        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao adicionar estoque", e);
    }
}


    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }

    @Override
    public Object getById(int id) {
        String sql = "SELECT * FROM produto WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarProduto(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID", e);
        }
    }

    @Override
    public List<Object> getAll() {
        String sql = "SELECT * FROM produto";
        List<Object> lista = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montarProduto(rs));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
    }

    private Produto montarProduto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        int quantidade = rs.getInt("quantidade");
        double preco = rs.getDouble("preco");
        int estoqueMinimo = rs.getInt("estoque_minimo");

        return new Produto(id, nome, descricao, quantidade, preco, estoqueMinimo);
    }

    public List<Produto> getByNome(String nomeBusca) {
        String sql = "SELECT * FROM produto WHERE nome LIKE ?";
        List<Produto> lista = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarProduto(rs));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por nome", e);
        }
    }
    public List<Produto> listarProdutosAbaixoDoEstoque() {
    String sql = "SELECT * FROM produto WHERE quantidade < estoque_minimo";
    List<Produto> lista = new ArrayList<>();

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Produto p = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("quantidade"),
                rs.getDouble("preco"),
                rs.getInt("estoque_minimo")
            );

            lista.add(p);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar produtos abaixo do estoque mínimo", e);
    }

    return lista;
}

}