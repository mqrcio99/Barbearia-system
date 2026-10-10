package barbearia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class GerenciadorBarbeariaTest {

    @Test
    void registraServicosCalculaFaturamentoEProtegeLista() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();
        gerenciador.registrarServico(new CorteCabelo("Ana"));
        gerenciador.registrarServico(new CorteBarba("Bia", 7.5));

        assertEquals(2, gerenciador.getServicos().size());
        assertEquals(62.5, gerenciador.calcularFaturamento(), 0.001);
        assertThrows(UnsupportedOperationException.class, () -> gerenciador.getServicos().clear());
        assertEquals(2, gerenciador.getServicos().size());
    }

    @Test
    void rejeitaServicoNulo() {
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();

        assertThrows(NullPointerException.class, () -> gerenciador.registrarServico(null));
    }
}