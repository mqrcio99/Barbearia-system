package barbearia;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável por gerenciar os serviços da barbearia. Aplica
 * Encapsulamento e centraliza a lógica de negócio.
 */
public class GerenciadorBarbearia {

    private final List<Servico> servicos;

    public GerenciadorBarbearia() {
        this.servicos = new ArrayList<>();
    }

    /**
     * Registra um novo serviço na lista.
     *
     * @param servico
     *            Serviço a ser adicionado.
     */
    public void registrarServico(Servico servico) {
        servicos.add(Objects.requireNonNull(servico, "servico não pode ser nulo."));
    }

    /**
     * Calcula o faturamento total somando o preço de todos os serviços.
     *
     * @return Total do faturamento.
     */
    public double calcularFaturamento() {
        double total = 0;
        for (Servico servico : servicos) {
            total += servico.calcularPreco();
        }
        return total;
    }

    /**
     * Retorna a lista de serviços (para uso interno se necessário).
     */
    public List<Servico> getServicos() {
        return List.copyOf(servicos);
    }
}