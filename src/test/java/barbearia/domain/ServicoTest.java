package barbearia.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import barbearia.exception.ClienteInvalidoException;
import barbearia.exception.ServicoInvalidoException;

class ServicoTest {

    @Test
    void calculaPrecoDosServicos() {
        CorteCabelo corteCabelo = new CorteCabelo("Ana");
        CorteBarba corteBarba = new CorteBarba("Bia", new BigDecimal("7.5"));

        assertEquals(new BigDecimal("35.0"), corteCabelo.calcularPreco());
        assertEquals(new BigDecimal("27.5"), corteBarba.calcularPreco());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1.0", "-0.01", "-100"})
    void rejeitaValorAdicionalInvalido(String valorInvalido) {
        assertThrows(ServicoInvalidoException.class, () -> new CorteBarba("Bia", new BigDecimal(valorInvalido)));

        CorteBarba corteBarba = new CorteBarba("Bia", new BigDecimal("7.0"));
        assertThrows(ServicoInvalidoException.class, () -> corteBarba.setValorAdicional(new BigDecimal(valorInvalido)));
        assertEquals(new BigDecimal("27.0"), corteBarba.calcularPreco());
    }

    @Test
    void rejeitaDadosObrigatoriosInvalidos() {
        assertThrows(ClienteInvalidoException.class, () -> new CorteCabelo("  "));

        CorteCabelo corteCabelo = new CorteCabelo("Ana");
        assertThrows(ClienteInvalidoException.class, () -> corteCabelo.setCliente(null));
        assertThrows(ServicoInvalidoException.class, () -> corteCabelo.setPrecoBase(new BigDecimal("-1.0")));
        assertEquals(new BigDecimal("35.0"), corteCabelo.calcularPreco());
    }

    @Test
    void clienteComApenasEspacosDeveLancarExcecao() {
        assertThrows(ClienteInvalidoException.class, () -> new CorteCabelo("   "));
    }

    @Test
    void descricaoComApenasEspacosDeveLancarExcecao() {
        assertThrows(ServicoInvalidoException.class, () -> new CorteCabelo("Ana").setDescricao("   "));
    }

    @Test
    void precoZeroDeveSerAceito() {
        CorteCabelo corte = new CorteCabelo("Ana");
        corte.setPrecoBase(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, corte.calcularPreco());
    }

    @Test
    void toStringDeveConterClienteEValorFormatado() {
        CorteCabelo corte = new CorteCabelo("Ana");
        String texto = corte.toString();

        assertTrue(texto.contains("Ana"));
        assertTrue(texto.contains("35.00"));
    }
}