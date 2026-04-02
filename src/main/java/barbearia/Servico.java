package barbearia;

/**
 * Classe abstrata que representa um serviço da barbearia.
 * Aplica os pilares de POO: Abstração, Encapsulamento e Herança.
 */
public abstract class Servico {

    // Encapsulamento: atributos privados
    private String cliente;
    private String descricao;
    private double precoBase;

    // Construtor
    public Servico(String cliente, String descricao, double precoBase) {
        this.cliente = cliente;
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    // Getters e Setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    // Método abstrato - Polimorfismo
    public abstract double calcularPreco();

    @Override
    public String toString() {
        return String.format("Cliente: %-15s | Serviço: %-15s | Preço: R$ %.2f",
                cliente, descricao, calcularPreco());
    }
}