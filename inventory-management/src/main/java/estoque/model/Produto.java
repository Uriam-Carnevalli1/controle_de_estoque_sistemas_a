/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estoque.model;

/**
 *
 * @author arthurce
 */
public class Produto {

    private int idProduto;
    private String nome;
    private String descricao;
    private int quantidade;
    private double preco;
    private int estoqueMinimo;

    
    public Produto() {
    }

    
    public Produto(int idProduto, String nome, String descricao, int quantidade, double preco, int estoqueMinimo) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.estoqueMinimo = estoqueMinimo;
    }

    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    // Método útil pra debugar
    @Override
    public String toString() {
        return nome + " (Qtd: " + quantidade + ", Preço: " + preco + ")";
    }
}
