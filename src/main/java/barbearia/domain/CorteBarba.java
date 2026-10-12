package barbearia.domain;

import java.math.BigDecimal;

import barbearia.exception.ServicoInvalidoException;

/**
 * Classe concreta que representa um serviço de corte de barba.
 */
public class CorteBarba extends Servico {

    private BigDecimal valorAdicional;

    public CorteBarba(String cliente, BigDecimal valorAdicional) {
        super(cliente, "Corte de Barba", new BigDecimal("20.0"));
        this.valorAdicional = validarValorMonetario(valorAdicional, "valor adicional");
    }

    public BigDecimal getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(BigDecimal valorAdicional) {
        this.valorAdicional = validarValorMonetario(valorAdicional, "valor adicional");
    }

    /**
     * Para corte de barba, o preço é o preço base mais o valor adicional.
     */
    @Override
    public BigDecimal calcularPreco() {
        BigDecimal total = getPrecoBase().add(valorAdicional);
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new ServicoInvalidoException("preço total deve ser não negativo.");
        }
        return total;
    }
}