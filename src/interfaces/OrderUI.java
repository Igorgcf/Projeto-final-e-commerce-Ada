package interfaces;

import entities.Order;
import services.OrderService;

import java.math.BigDecimal;
import java.util.Scanner;

public class OrderUI {


        private final OrderService orderService;
        private final Scanner sc;

        public OrderUI(OrderService orderService, Scanner sc) {
            this.orderService = orderService;
            this.sc = sc;
        }

        public void menu() {
            while (true) {
                System.out.println("\n--- Pedidos ---");
                System.out.println("1 - Criar pedido");
                System.out.println("2 - Listar pedidos");
                System.out.println("3 - Adicionar item");
                System.out.println("4 - Remover item");
                System.out.println("5 - Alterar quantidade de item");
                System.out.println("6 - Finalizar pedido");
                System.out.println("7 - Pagar pedido");
                System.out.println("8 - Entregar pedido");
                System.out.println("0 - Voltar");
                System.out.print("Escolha: ");
                int op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1 -> ToCreate();
                    case 2 -> list();
                    case 3 -> addItem();
                    case 4 -> removeItem();
                    case 5 -> changeQuantity();
                    case 6 -> finish();
                    case 7 -> pay();
                    case 8 -> deliver();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }

        private void ToCreate() {
            System.out.print("ID do cliente: ");
            long clientId = sc.nextLong(); sc.nextLine();
            try {
                Order order = orderService.createOrder(clientId);
                System.out.println("Pedido criado: " + order.getId());
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        private void list() {
            orderService.findAll();
        }

        private void addItem() {
            System.out.print("ID do pedido: ");
            long orderId = sc.nextLong();
            System.out.print("ID do produto: ");
            long productId = sc.nextLong();
            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();
            System.out.print("Preço de venda: ");
            BigDecimal salePrice = sc.nextBigDecimal();
            sc.nextLine();
            try {
                orderService.addItemToOrder(orderId, productId, salePrice, qtd);
                System.out.println("Item adicionado!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        private void removeItem() {
            System.out.print("ID do pedido: ");
            long orderId = sc.nextLong();
            System.out.print("ID do produto: ");
            long productId = sc.nextLong();
            sc.nextLine();
            try {
                orderService.removeItemFromOrder(orderId, productId);
                System.out.println("Item removido!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        private void changeQuantity() {
            System.out.print("ID do pedido: ");
            long orderId = sc.nextLong();
            System.out.print("ID do produto: ");
            long productId = sc.nextLong();
            System.out.print("Nova quantidade: ");
            int qtd = sc.nextInt();
            sc.nextLine();
            try {
                orderService.changeQuantity(orderId, productId, qtd);
                System.out.println("Quantidade atualizada!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        private void finish() {
            System.out.print("ID do pedido: ");
            long orderId = sc.nextLong(); sc.nextLine();
            try {
                orderService.finalizeOrder(orderId);
                System.out.println("Pedido finalizado e aguardando pagamento!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        private void pay() {
            System.out.print("ID do pedido: ");
            long orderId = sc.nextLong(); sc.nextLine();
            try {
                orderService.pay(orderId);
                System.out.println("Pedido pago!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        private void deliver() {
            System.out.print("ID do pedido: ");
            long orderId = sc.nextLong(); sc.nextLine();
            try {
                orderService.deliver(orderId);
                System.out.println("Pedido entregue!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
