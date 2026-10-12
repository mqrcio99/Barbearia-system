package barbearia.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import barbearia.domain.Servico;

/**
 * Classe responsável por gerenciar os serviços da barbearia.
 * Centraliza o registro de serviços e o cálculo do faturamento total.
 */
public class GerenciadorBarbearia {

    private static final String ARQUIVO_PADRAO = "dados.json";

    private final List<Servico> servicos;
    private final PersistenciaService persistenciaService;
    private final String caminhoArquivo;

    public GerenciadorBarbearia() {
        this(new PersistenciaService(), ARQUIVO_PADRAO);
    }

    public GerenciadorBarbearia(PersistenciaService persistenciaService) {
        this(persistenciaService, ARQUIVO_PADRAO);
    }

    public GerenciadorBarbearia(PersistenciaService persistenciaService, String caminhoArquivo) {
        this.persistenciaService = Objects.requireNonNull(persistenciaService, "persistenciaService não pode ser nulo.");
        this.caminhoArquivo = caminhoArquivo;
        try {
            this.servicos = new ArrayList<>(persistenciaService.carregar(caminhoArquivo));
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível carregar os dados salvos da barbearia.", e);
        }
    }

    /**
     * Registra um novo serviço na lista e persiste a alteração imediatamente.
     *
     * @param servico serviço a ser adicionado
     */
    public void registrarServico(Servico servico) {
        servicos.add(Objects.requireNonNull(servico, "servico não pode ser nulo."));
        try {
            persistenciaService.salvar(servicos, caminhoArquivo);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível salvar os dados da barbearia.", e);
        }
    }

    /**
     * Calcula o faturamento total somando o valor de todos os serviços registrados.
     *
     * @return faturamento total acumulado
     */
    public BigDecimal calcularFaturamento() {
        BigDecimal total = BigDecimal.ZERO;
        for (Servico servico : servicos) {
            total = total.add(servico.calcularPreco());
        }
        return total;
    }

    /**
     * Retorna o faturamento total atual do sistema.
     *
     * @return faturamento total acumulado
     */
    public BigDecimal getFaturamentoTotal() {
        return calcularFaturamento();
    }

    /**
     * Retorna uma visão somente leitura da lista de serviços registrados.
     *
     * @return lista imutável de serviços
     */
    public List<Servico> getServicos() {
        return List.copyOf(servicos);
    }
}