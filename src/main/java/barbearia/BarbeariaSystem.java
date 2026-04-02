package barbearia;

import java.util.Scanner;

public class BarbeariaSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorBarbearia gerenciador = new GerenciadorBarbearia();
        int opcao = -1;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTÃO DE BARBEARIA     ║");
        System.out.println("╚══════════════════════════════════════╝");

        do {
            exibirMenu();
            System.out.print("  Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Opção inválida! Digite um número.");
                continue;
            }
            switch (opcao) {
                case 1: registrarCorteCabelo(scanner, gerenciador); break;
                case 2: registrarCorteBarba(scanner, gerenciador); break;
                case 3: gerenciador.listarServicos(); break;
                case 4:
                    System.out.println("\n========================================");
                    System.out.printf("  💰 Faturamento Total: R$ %.2f%n", gerenciador.calcularFaturamento());
                    System.out.println("========================================");
                    break;
                case 0: System.out.println("\n  Até logo! ✂"); break;
                default: System.out.println("  ⚠ Opção inválida!");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("  [1] Registrar Corte de Cabelo");
        System.out.println("  [2] Registrar Corte de Barba");
        System.out.println("  [3] Listar Serviços");
        System.out.println("  [4] Ver Faturamento Total");
        System.out.println("  [0] Sair");
        System.out.println("----------------------------------------");
    }

    private static void registrarCorteCabelo(Scanner scanner, GerenciadorBarbearia gerenciador) {
        System.out.print("\n  Nome do cliente: ");
        String cliente = scanner.nextLine().trim();
        if (cliente.isEmpty()) { System.out.println("  ⚠ Nome vazio!"); return; }
        CorteCabelo corte = new CorteCabelo(cliente);
        System.out.printf("  Preço: R$ %.2f%n", corte.calcularPreco());
        gerenciador.registrarServico(corte);
    }

    private static void registrarCorteBarba(Scanner scanner, GerenciadorBarbearia gerenciador) {
        System.out.print("\n  Nome do cliente: ");
        String cliente = scanner.nextLine().trim();
        if (cliente.isEmpty()) { System.out.println("  ⚠ Nome vazio!"); return; }
        double valorAdicional = 0;
        System.out.print("  Valor adicional da barba: R$ ");
        try {
            valorAdicional = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("  ⚠ Valor inválido! Usando R$ 0.00.");
        }
        CorteBarba corte = new CorteBarba(cliente, valorAdicional);
        System.out.printf("  Preço: R$ %.2f%n", corte.calcularPreco());
        gerenciador.registrarServico(corte);
    }
}