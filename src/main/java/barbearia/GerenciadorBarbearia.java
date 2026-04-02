package barbearia;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar os serviços da barbearia.
 * Aplica Encapsulamento e centraliza a lógica de negócio.
 */
public class GerenciadorBarbearia {

    // Lista de serviços armazenados em memória
    private List<Servico> servicos;

    // Construtor
    public GerenciadorBarbearia() {
        this.servicos = new ArrayList<>();
    }

    /**
     * Registra um novo serviço na lista.
     *
     * @param servico Serviço a ser adicionado.
     */
    public void registrarServico(Servico servico) {
        servicos.add(servico);
        System.out.println("\n✔ Serviço registrado com sucesso!");
    }

    /**
     * Lista todos os serviços registrados.
     */
    public void listarServicos() {
        System.out.println("\n========================================");
        System.out.println("       SERVIÇOS REGISTRADOS");
        System.out.println("========================================");

        if (servicos.isEmpty()) {
            System.out.println("  Nenhum serviço registrado ainda.");
        } else {
            for (int i = 0; i < servicos.size(); i++) {
                System.out.printf("  [%d] %s%n", i + 1, servicos.get(i));
            }
        }

        System.out.println("========================================");
    }

    /**
     * Calcula o faturamento total somando o preço de todos os serviços.
     *
     * @return Total do faturamento.
     */
    public double calcularFaturamento() {
        double total = 0;
        for (Servico s : servicos) {
            total += s.calcularPreco();
        }
        return total;
    }

    /**
     * Retorna a lista de serviços (para uso interno se necessário).
     */
    public List<Servico> getServicos() {
        return servicos;
    }
}