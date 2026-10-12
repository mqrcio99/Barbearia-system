package barbearia.domain;

import java.math.BigDecimal;

/**
 * Classe concreta que representa um serviço de corte de cabelo.
 */
public class CorteCabelo extends Servico {

    public CorteCabelo(String cliente) {
        super(cliente, "Corte de Cabelo", new BigDecimal("35.0"));
    }

    /**
     * Para corte de cabelo, o preço é igual ao preço base.
     */
    @Override
    public BigDecimal calcularPreco() {
        return getPrecoBase();
    }
}