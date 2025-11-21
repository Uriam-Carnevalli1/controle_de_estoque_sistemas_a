package estoque.testes;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestaConexao {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/estoque_inventario";
            String user = "root";
            String password = "59545799a";

            Connection conexao = DriverManager.getConnection(url, user, password);

            System.out.println("Conectado ao MySQL ");
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
