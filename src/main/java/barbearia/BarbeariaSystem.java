package barbearia;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class BarbeariaSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorBarbearia gerenciadorBarbearia = new GerenciadorBarbearia();
        int opcaoMenu = -1;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTÃO DE BARBEARIA     ║");
        System.out.println("╚══════════════════════════════════════╝");

        try {
            do {
                exibirOpcoesMenu();
                System.out.print("  Escolha uma opção: ");
                try {
                    opcaoMenu = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("  ⚠ Opção inválida! Digite um número.");
                    continue;
                }
                switch (opcaoMenu) {
                    case 1 :
                        registrarCorteCabelo(scanner, gerenciadorBarbearia);
                        break;
                    case 2 :
                        registrarCorteBarba(scanner, gerenciadorBarbearia);
                        break;
                    case 3 :
                        exibirServicos(gerenciadorBarbearia);
                        break;
                    case 4 :
                        System.out.println("\n========================================");
                        System.out.printf("  💰 Faturamento Total: R$ %.2f%n",
                                gerenciadorBarbearia.calcularFaturamento());
                        System.out.println("========================================");
                        break;
                    case 0 :
                        System.out.println("\n  Até logo! ✂");
                        break;
                    default :
                        System.out.println("  ⚠ Opção inválida!");
                }
            } while (opcaoMenu != 0);
        } catch (NoSuchElementException e) {
            System.out.println("\n  Entrada encerrada.");
        } finally {
            scanner.close();
        }
    }

    private static void exibirOpcoesMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("  [1] Registrar Corte de Cabelo");
        System.out.println("  [2] Registrar Corte de Barba");
        System.out.println("  [3] Listar Serviços");
        System.out.println("  [4] Ver Faturamento Total");
        System.out.println("  [0] Sair");
        System.out.println("----------------------------------------");
    }

    private static void exibirServicos(GerenciadorBarbearia gerenciadorBarbearia) {
        var servicos = gerenciadorBarbearia.getServicos();
        System.out.println("\n========================================");
        System.out.println("       SERVIÇOS REGISTRADOS");
        System.out.println("========================================");

        if (servicos.isEmpty()) {
            System.out.println("  Nenhum serviço registrado ainda.");
        } else {
            for (int indice = 0; indice < servicos.size(); indice++) {
                System.out.printf("  [%d] %s%n", indice + 1, servicos.get(indice));
            }
        }

        System.out.println("========================================");
    }

    private static void registrarCorteCabelo(Scanner scanner, GerenciadorBarbearia gerenciador) {
        System.out.print("\n  Nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();
        if (nomeCliente.isEmpty()) {
            System.out.println("  ⚠ Nome vazio!");
            return;
        }
        CorteCabelo corte = new CorteCabelo(nomeCliente);
        System.out.printf("  Preço: R$ %.2f%n", corte.calcularPreco());
        gerenciador.registrarServico(corte);
        System.out.println("\n✔ Serviço registrado com sucesso!");
    }

    private static void registrarCorteBarba(Scanner scanner, GerenciadorBarbearia gerenciador) {
        System.out.print("\n  Nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();
        if (nomeCliente.isEmpty()) {
            System.out.println("  ⚠ Nome vazio!");
            return;
        }
        double valorAdicional = 0;
        System.out.print("  Valor adicional da barba: R$ ");
        try {
            double valorInformado = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            if (!Double.isFinite(valorInformado) || valorInformado < 0) {
                throw new NumberFormatException("O valor adicional deve ser finito e não negativo.");
            }
            valorAdicional = valorInformado;
        } catch (NumberFormatException e) {
            System.out.println("  ⚠ Valor inválido! Usando R$ 0.00.");
        }
        CorteBarba corte = new CorteBarba(nomeCliente, valorAdicional);
        System.out.printf("  Preço: R$ %.2f%n", corte.calcularPreco());
        gerenciador.registrarServico(corte);
        System.out.println("\n✔ Serviço registrado com sucesso!");
    }
}