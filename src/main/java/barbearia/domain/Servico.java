package barbearia.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import barbearia.exception.ClienteInvalidoException;
import barbearia.exception.ServicoInvalidoException;

/**
 * Classe abstrata que representa um serviço da barbearia.
 * Centraliza os atributos comuns e as validações do domínio.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CorteCabelo.class, name = "CorteCabelo"),
        @JsonSubTypes.Type(value = CorteBarba.class, name = "CorteBarba")
})
public abstract class Servico {

    private String cliente;
    private String descricao;
    private BigDecimal precoBase;

    public Servico(String cliente, String descricao, BigDecimal precoBase) {
        this.cliente = validarCliente(cliente);
        this.descricao = validarDescricao(descricao);
        this.precoBase = validarValorMonetario(precoBase, "preço base");
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = validarCliente(cliente);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = validarDescricao(descricao);
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = validarValorMonetario(precoBase, "preço base");
    }

    /**
     * Calcula o valor do serviço conforme a regra específica de cada tipo.
     *
     * @return preço calculado do serviço
     */
    public abstract BigDecimal calcularPreco();

    protected static String validarCliente(String valor) {
        String clienteValidado = validarTextoObrigatorio(valor, "nome do cliente");
        if (clienteValidado == null || clienteValidado.isBlank()) {
            throw new ClienteInvalidoException("nome do cliente não pode ser vazio.");
        }
        return clienteValidado;
    }

    protected static String validarDescricao(String valor) {
        String descricaoValidada = validarTextoObrigatorio(valor, "descrição do serviço");
        if (descricaoValidada == null || descricaoValidada.isBlank()) {
            throw new ServicoInvalidoException("descrição do serviço não pode ser vazia.");
        }
        return descricaoValidada;
    }

    protected static String validarTextoObrigatorio(String valor, String nomeCampo) {
        if (valor == null) {
            throw new ClienteInvalidoException(nomeCampo + " não pode ser nulo.");
        }
        String valorNormalizado = valor.trim();
        if (valorNormalizado.isBlank()) {
            if ("nome do cliente".equals(nomeCampo)) {
                throw new ClienteInvalidoException(nomeCampo + " não pode ser vazio.");
            }
            throw new ServicoInvalidoException(nomeCampo + " não pode ser vazio.");
        }
        return valorNormalizado;
    }

    protected static BigDecimal validarValorMonetario(BigDecimal valor, String nomeCampo) {
        if (valor == null) {
            throw new ServicoInvalidoException(nomeCampo + " não pode ser nulo.");
        }
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ServicoInvalidoException(nomeCampo + " deve ser não negativo.");
        }
        return valor;
    }

    @Override
    public String toString() {
        BigDecimal valor = calcularPreco().setScale(2, RoundingMode.HALF_UP);
        return String.format("Cliente: %-15s | Serviço: %-15s | Preço: R$ %.2f", cliente, descricao, valor.doubleValue());
    }
}