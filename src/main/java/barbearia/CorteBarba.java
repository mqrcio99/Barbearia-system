package barbearia;

/**
 * Classe concreta que representa um serviço de Corte de Barba.
 * Herda de Servico e adiciona o atributo valorBarba - Herança.
 */
public class CorteBarba extends Servico {

    // Atributo específico de CorteBarba - Encapsulamento
    private double valorBarba;

    // Construtor
    public CorteBarba(String cliente, double valorBarba) {
        super(cliente, "Corte de Barba", 20.0);
        this.valorBarba = valorBarba;
    }

    public double getValorBarba() {
        return valorBarba;
    }

    public void setValorBarba(double valorBarba) {
        this.valorBarba = valorBarba;
    }

    /**
     * Para corte de barba, o preço é preço base + valorBarba adicional.
     */
    @Override
    public double calcularPreco() {
        return getPrecoBase() + valorBarba;
    }
}