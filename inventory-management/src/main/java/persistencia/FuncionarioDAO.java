/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import negocio.Funcionario;
/**
 *
 * @author arthurce
 */
public class FuncionarioDAO implements BasicoDAO{
    
    private Connection getConnection() throws SQLException{
        return Conexao.getConnection();
    }
    
    @Override
    // salvar recebe uma classe  instanciada 
    public void salvar(Object funcionario) {
        Funcionario f = (Funcionario) funcionario;
        
        String sql = "INSERT INTO FUNCIONARIO (nome, usuario, senha)" 
                + "VALUES (?, ?,?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getUsuario());
            stmt.setString(3, f.getSenha());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new  RuntimeException("Erro ao salvar produto",e);
        }
    }
    
    @Override
    public void atualizar(Object funcionario){
        Funcionario f = (Funcionario) funcionario;
        
        String sql = "UPDATE funcionario SET nome=?, usuario=?, senha=? "
           + "WHERE id=?";

                
        
        try (Connection conn = getConnection();
                
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,f.getNome());
            stmt.setString(2,f.getUsuario());
            stmt.setString(3,f.getSenha());
            stmt.setInt(4,f.getId());
            
            stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar funcionario",e);
        }
       
            
        }
     
    
    
   @Override
    public void deletar(int id) {
        String sql = "DELETE FROM funcionario WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar funcionario", e);
        }
    }

    
    @Override
    public Object getById(int id) {
        String sql = "SELECT * FROM funcionario WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return montarFuncionario(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionario por ID", e);
        }
    }

    
    @Override
    public List<Object> getAll() {
        String sql = "SELECT * FROM funcionario";
        List<Object> lista = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montarFuncionario(rs));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionarios", e);
        }
    }

   
    private Funcionario montarFuncionario(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String usuario = rs.getString("usuario");
        String senha = rs.getString("senha");

        return new Funcionario(id, nome, usuario, senha);
    }

   
    public List<Funcionario> getByNome(String nomeBusca) {
        String sql = "SELECT * FROM funcionario WHERE nome LIKE ?";
        List<Funcionario> lista = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(montarFuncionario(rs));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionario por nome", e);
        }
    }
   public Funcionario buscarPorUsuarioESenha(String usuario, String senha) {
    String sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usuario);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Funcionario f = new Funcionario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("usuario"),
                rs.getString("senha")
            );
            return f; // Login OK
        }

    } catch (Exception e) {
        throw new RuntimeException("Erro ao buscar funcionario por usuario e senha", e);
    }

    

    return null; // Não achou ninguém -> login inválido
}

}