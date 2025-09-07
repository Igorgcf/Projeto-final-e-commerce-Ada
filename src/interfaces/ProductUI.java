package interfaces;

import entities.Product;
import services.ProductService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class ProductUI {

        private final ProductService productService;
        private final Scanner sc;

        public ProductUI(ProductService productService, Scanner sc) {
            this.productService = productService;
            this.sc = sc;
        }

        public void menu() {
            while (true) {
                System.out.println("\n--- Produtos ---");
                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Listar produtos");
                System.out.println("3 - Atualizar produto");
                System.out.println("0 - Voltar");
                System.out.print("Escolha: ");
                int op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1 -> register();
                    case 2 -> list();
                    case 3 -> update();
                    case 0 -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }

        private void register() {
            System.out.print("Nome: ");
            String name = sc.nextLine();
            System.out.println("SKU: ");
            String sku = sc.nextLine();
            System.out.print("Preço padrão: ");
            BigDecimal price = sc.nextBigDecimal();
            sc.nextLine();

            productService.save(name, sku, price);
            System.out.println("Produto cadastrado com sucesso!");
        }

        private void list() {
            productService.findAll();
        }

        private void update() {
            System.out.print("ID do produto: ");
            long id = sc.nextLong(); sc.nextLine();
            Optional<Product> opt = Optional.ofNullable(productService.findById(id));
            if (opt.isEmpty()) {
                System.out.println("Produto não encontrado.");
                return;
            }
            Product p = opt.get();
            System.out.print("Novo nome: ");
            p.setName(sc.nextLine());
            System.out.print("Novo SKU: ");
            p.setSku(sc.nextLine());
            System.out.print("Novo preço padrão: ");
            p.setBasePrice(sc.nextBigDecimal());
            productService.save(p.getName(), p.getSku(), p.getBasePrice());
            System.out.println("Produto atualizado!");
        }
    }
