package barbearia;

/**
 * Classe concreta que representa um serviço de Corte de Cabelo.
 * Herda de Servico e implementa calcularPreco() - Polimorfismo.
 */
public class CorteCabelo extends Servico {

    // Construtor
    public CorteCabelo(String cliente) {
        super(cliente, "Corte de Cabelo", 35.0);
    }

    /**
     * Para corte de cabelo, o preço é igual ao preço base.
     */
    @Override
    public double calcularPreco() {
        return getPrecoBase();
    }
}