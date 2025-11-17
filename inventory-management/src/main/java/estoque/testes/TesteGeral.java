/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estoque.testes;

/**
 *
 * @author arthurce
 */
import estoque.model.Produto;
import estoque.model.Fornecedor;
import estoque.model.Funcionario;
import estoque.model.EntradaEstoque;

import java.time.LocalDateTime;

public class TesteGeral {
    
    public static void main(String[] args) {

        System.out.println("TESTE\n");

       
        Produto p = new Produto(1, "Mouse Gamer", "Mouse RGB", 50, 120.00, 10);
        System.out.println("Produto criado:");
        System.out.println(p);

        
        Fornecedor f = new Fornecedor(1, "Tech Supplies", "21988887777", "contato", "Rio de Janeiro");
        System.out.println("\nFornecedor criado:");
        System.out.println(f);

        
        Funcionario func = new Funcionario(1, "Arthur", "arthur", "123456");
        System.out.println("\nFuncionário criado:");
        System.out.println(func);

        
        EntradaEstoque entrada = new EntradaEstoque(
            1,
            p.getIdProduto(),
            f.getId(),
            func.getId(),
            LocalDateTime.now(),
            20,
            2400.00
        );

        System.out.println("\nEntrada de Estoque criada:");
        System.out.println(entrada);

        
    }
}