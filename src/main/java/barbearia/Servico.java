package barbearia;

/**
 * Classe abstrata que representa um serviço da barbearia. Aplica os pilares de
 * POO: Abstração, Encapsulamento e Herança.
 */
public abstract class Servico {

    private String cliente;
    private String descricao;
    private double precoBase;

    public Servico(String cliente, String descricao, double precoBase) {
        this.cliente = validarTextoObrigatorio(cliente, "nome do cliente");
        this.descricao = validarTextoObrigatorio(descricao, "descrição do serviço");
        this.precoBase = validarValorMonetario(precoBase, "preço base");
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = validarTextoObrigatorio(cliente, "nome do cliente");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = validarTextoObrigatorio(descricao, "descrição do serviço");
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = validarValorMonetario(precoBase, "preço base");
    }

    public abstract double calcularPreco();

    protected static String validarTextoObrigatorio(String valor, String nomeCampo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(nomeCampo + " não pode ser vazio.");
        }
        return valor;
    }

    protected static double validarValorMonetario(double valor, String nomeCampo) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(nomeCampo + " deve ser finito e não negativo.");
        }
        return valor;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %-15s | Serviço: %-15s | Preço: R$ %.2f", cliente, descricao, calcularPreco());
    }
}