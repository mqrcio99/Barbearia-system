package barbearia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ServicoTest {

    @Test
    void calculaPrecoDosServicos() {
        CorteCabelo corteCabelo = new CorteCabelo("Ana");
        CorteBarba corteBarba = new CorteBarba("Bia", 7.5);

        assertEquals(35.0, corteCabelo.calcularPreco(), 0.001);
        assertEquals(27.5, corteBarba.calcularPreco(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejeitaValorAdicionalInvalido(double valorInvalido) {
        assertThrows(IllegalArgumentException.class, () -> new CorteBarba("Bia", valorInvalido));

        CorteBarba corteBarba = new CorteBarba("Bia", 7.0);
        assertThrows(IllegalArgumentException.class, () -> corteBarba.setValorAdicional(valorInvalido));
        assertEquals(27.0, corteBarba.calcularPreco(), 0.001);
    }

    @Test
    void rejeitaDadosObrigatoriosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new CorteCabelo("  "));

        CorteCabelo corteCabelo = new CorteCabelo("Ana");
        assertThrows(IllegalArgumentException.class, () -> corteCabelo.setCliente(null));
        assertThrows(IllegalArgumentException.class, () -> corteCabelo.setPrecoBase(Double.NaN));
        assertEquals(35.0, corteCabelo.calcularPreco(), 0.001);
    }
}