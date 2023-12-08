/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aplicacaodelivery;

/**
 *
 * @author Carlos Emanuel
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// ...

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estabelecimento estabelecimento = new Estabelecimento();
 	Motoboy motoboy = new Motoboy();  // Adicionei a instância do Motoboy

        do {
            System.out.println("Você é a) Estabelecimento ou b) Cliente?");
            String opcao = scanner.nextLine();

            if (opcao.equalsIgnoreCase("a")) {
                System.out.println("Digite o nome do estabelecimento:");
                String nome = scanner.nextLine();
                System.out.println("Digite o endereço do estabelecimento:");
                String enderecoStr = scanner.nextLine();
                Endereco endereco = new Endereco();
                endereco.setRua(enderecoStr);
                estabelecimento.cadastrar(nome, endereco);

                System.out.println("Deseja cadastrar o cardápio? (s/n)");
                String cadastrarCardapio = scanner.nextLine();
                while (cadastrarCardapio.equalsIgnoreCase("s")) {
                    System.out.println("Digite o nome do produto:");
                    String nomeProduto = scanner.nextLine();
                    System.out.println("Digite o preço do produto:");
                    double precoProduto = 0.0;
                    try {
                        precoProduto = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido. Insira um número para o preço.");
                        scanner.next(); // Limpa o buffer do scanner
                        continue;
                    }
                    scanner.nextLine(); // Limpa o buffer do scanner
                    Produto produto = new Produto();
                    produto.setNome(nomeProduto);
                    produto.setPreco(precoProduto);
                    estabelecimento.cadastrarCardapio(produto);
                    System.out.println("Deseja cadastrar outro produto no cardápio? (s/n)");
                    cadastrarCardapio = scanner.nextLine();
                }

                // Exibe os detalhes dos pedidos realizados
                for (Pedido pedido : estabelecimento.getPedidosRealizados()) {
                    System.out.println("Detalhes do pedido para " + estabelecimento.getNome() + ":");
                    System.out.println("Cliente: " + pedido.getCliente().getNome());

                    // Exibe os itens do pedido
                    System.out.println("Itens do pedido:");
                    for (ItemPedido item : pedido.getItens()) {
                        System.out.println("- Produto: " + item.getProduto().getNome() +
                                ", Quantidade: " + item.getQuantidade() +
                                ", Subtotal: " + item.getProduto().getPreco() * item.getQuantidade());
                    }

                    System.out.println("Total do pedido: " + pedido.getTotal());
                }

                // Agora exibe o total de vendas
                System.out.println("O total de vendas é: " + estabelecimento.gerarTotalVendas());
            } else if (opcao.equalsIgnoreCase("b")) {
                // Restante do código...
                Cliente cliente = new Cliente();
                System.out.println("Digite o nome do cliente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o telefone do cliente:");
                String telefone = scanner.nextLine();
                System.out.println("Digite o endereço do cliente:");
                String enderecoStr = scanner.nextLine();
                Endereco endereco = new Endereco();
                endereco.setRua(enderecoStr);
                cliente.cadastrar(nome, telefone, endereco);

                System.out.println("Deseja realizar um pedido? (s/n)");
                String realizarPedido = scanner.nextLine();
                if (realizarPedido.equalsIgnoreCase("s")) {
                    Pedido pedido = new Pedido();
                    pedido.setCliente(cliente);
                    String adicionarItem = "s";
                    while (adicionarItem.equalsIgnoreCase("s")) {
                        System.out.println("Digite o nome do produto que deseja adicionar ao pedido:");
                        String nomeProduto = scanner.nextLine();
                        for (Produto produto : estabelecimento.getCardapio()) {
                            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                                ItemPedido item = new ItemPedido();
                                item.setProduto(produto);
                                System.out.println("Digite a quantidade desse produto:");
                                int quantidade = scanner.nextInt();
                                item.setQuantidade(quantidade);
                                pedido.getItens().add(item);
                                pedido.setTotal(pedido.getTotal() + produto.getPreco() * quantidade);
                            }
                        }
                        
                        
                        System.out.println("Deseja adicionar outro item ao pedido? (s/n)");
                        adicionarItem = scanner.next();
                    }

                    System.out.println("Deseja realizar um pagamento? (s/n)");
                    String realizarPagamento = scanner.next();
                    if (realizarPagamento.equalsIgnoreCase("s")) {
                        System.out.println("Escolha a forma de pagamento: a) Débito ou b) Crédito");
                        String formaPagamento = scanner.next();
                        if (formaPagamento.equalsIgnoreCase("b")) {
                            pedido.setTotal(pedido.getTotal() + 1); // Adicional de 1 real para pagamento no crédito
                        }
                        System.out.println("Pagamento realizado. Valor total: " + pedido.getTotal());
			
			// Agora, após o pagamento, podemos chamar o método realizarEntrega do motoboy
                        motoboy.realizarEntrega(pedido);
                    }
                }
            } else {
                System.out.println("Opção inválida.");
            }

            // Limpa o buffer do scanner antes de ler a opção do usuário
            String respostaUsuario = scanner.nextLine();
            // Pergunta ao usuário se deseja realizar mais alguma ação
            System.out.println("Deseja realizar mais alguma ação? (s/n)");
        } while (scanner.nextLine().equalsIgnoreCase("s"));
        
        System.out.println("Programa encerrado. Obrigado!");
        scanner.close();
    }
}
