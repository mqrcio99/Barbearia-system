package barbearia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import barbearia.domain.CorteBarba;
import barbearia.domain.CorteCabelo;
import barbearia.exception.ServicoInvalidoException;

class GerenciadorBarbeariaTest {

    @Test
    void registraServicosCalculaFaturamentoEProtegeLista() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();
        gerenciador.registrarServico(new CorteCabelo("Ana"));
        gerenciador.registrarServico(new CorteBarba("Bia", new BigDecimal("7.5")));

        assertEquals(2, gerenciador.getServicos().size());
        assertEquals(new BigDecimal("62.5"), gerenciador.calcularFaturamento());
        assertThrows(UnsupportedOperationException.class, () -> gerenciador.getServicos().clear());
        assertEquals(2, gerenciador.getServicos().size());
    }

    @Test
    void faturamentoComListaVaziaDeveRetornarZero() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();
        assertEquals(BigDecimal.ZERO, gerenciador.calcularFaturamento());
    }

    @Test
    void registrarDoisServicosDeveSomarFaturamentoCorretamente() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();
        gerenciador.registrarServico(new CorteCabelo("Ana"));
        gerenciador.registrarServico(new CorteBarba("Bia", new BigDecimal("5.0")));

        assertEquals(new BigDecimal("60.0"), gerenciador.calcularFaturamento());
    }

    @Test
    void getServicosDeveRetornarListaImutavel() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();
        gerenciador.registrarServico(new CorteCabelo("Ana"));

        assertThrows(UnsupportedOperationException.class, () -> gerenciador.getServicos().add(new CorteCabelo("Bia")));
    }

    @Test
    void rejeitaServicoNulo() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();

        assertThrows(NullPointerException.class, () -> gerenciador.registrarServico(null));
    }

    @Test
    void rejeitaValorAdicionalInvalido() {
        assertThrows(ServicoInvalidoException.class, () -> new CorteBarba("Bia", new BigDecimal("-1.0")));
    }
}