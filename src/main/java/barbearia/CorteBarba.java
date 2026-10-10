package barbearia;

/**
 * Classe concreta que representa um serviço de Corte de Barba. Herda de Servico
 * e adiciona o valor adicional do serviço.
 */
public class CorteBarba extends Servico {

    private double valorAdicional;

    public CorteBarba(String cliente, double valorAdicional) {
        super(cliente, "Corte de Barba", 20.0);
        this.valorAdicional = validarValorMonetario(valorAdicional, "valor adicional");
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = validarValorMonetario(valorAdicional, "valor adicional");
    }

    /**
     * Para corte de barba, o preço é o preço base mais o valor adicional.
     */
    @Override
    public double calcularPreco() {
        return validarValorMonetario(getPrecoBase() + valorAdicional, "preço total");
    }
}