/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author arthurce
 */

import java.time.LocalDateTime;

public class EntradaEstoque {

    private int id;
    private int idProduto;
    private int idFornecedor;
    private int idFuncionario;
    private LocalDateTime data;
    private int quantidade;
    private double custoTotal;

    
    public EntradaEstoque() {
    }

    
    public EntradaEstoque(int id, int idProduto, int idFornecedor, int idFuncionario,
                          LocalDateTime data, int quantidade, double custoTotal) {
        this.id = id;
        this.idProduto = idProduto;
        this.idFornecedor = idFornecedor;
        this.idFuncionario = idFuncionario;
        this.data = data;
        this.quantidade = quantidade;
        this.custoTotal = custoTotal;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    @Override
    public String toString() {
        return "EntradaEstoque ID: " + id + 
               " | Produto: " + idProduto +
               " | Quantidade: " + quantidade +
               " | Custo Total: " + custoTotal;
    }
}
