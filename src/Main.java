import interfaces.ClientUI;
import interfaces.OrderUI;
import interfaces.ProductUI;
import repositories.ClientRepository;
import repositories.OrderRepository;
import repositories.ProductRepository;
import services.ClientService;
import services.NotificationService;
import services.OrderService;
import services.ProductService;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        File baseDir = new File("./ada-commerce-data");
        if (!baseDir.exists()) baseDir.mkdirs();
        File clientsDat = new File(baseDir, "clients.dat");
        File productsDat = new File(baseDir, "products.dat");
        File ordersDat = new File(baseDir, "orders.dat");

        ClientRepository clientRepo = new ClientRepository(clientsDat);
        ProductRepository productRepo = new ProductRepository(productsDat);
        OrderRepository requestRepo = new OrderRepository(ordersDat);

        ClientService clientService = new ClientService(clientRepo);
        ProductService productService = new ProductService(productRepo);
        NotificationService notificationService = new NotificationService();
        OrderService orderService = new OrderService(requestRepo, clientRepo, notificationService, productRepo);

        ClientUI clientUI = new ClientUI(clientService, sc);
        ProductUI productUI = new ProductUI(productService, sc);
        OrderUI orderUI = new OrderUI(orderService, sc);

        while (true) {
            System.out.println("\n=== ADA COMMERCE ===");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Gerenciar Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> clientUI.menu();
                case 2 -> productUI.menu();
                case 3 -> orderUI.menu();
                case 0 -> {
                    System.out.println("Saindo... até logo!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

}