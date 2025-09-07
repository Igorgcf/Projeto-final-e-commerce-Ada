package interfaces;

import java.util.Optional;
import java.util.Scanner;

import entities.Client;
import services.ClientService;

public class ClientUI {

        private final ClientService clientService;
        private final Scanner sc;

        public ClientUI(ClientService clientService, Scanner sc) {
            this.clientService = clientService;
            this.sc = sc;
        }

        public void menu() {
            while (true) {
                System.out.println("\n--- Clientes ---");
                System.out.println("1 - Cadastrar cliente");
                System.out.println("2 - Listar clientes");
                System.out.println("3 - Atualizar cliente");
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
            System.out.print("Documento: ");
            String doc = sc.nextLine();
            System.out.println("Email: ");
            String email = sc.nextLine();


            clientService.save(name, doc, email);
            System.out.println("Cliente cadastrado com sucesso!");
        }

        private void list() {
            clientService.findAll();
        }

        private void update() {
            System.out.print("ID do cliente: ");
            long id = sc.nextLong(); sc.nextLine();
            Optional<Client> opt = Optional.ofNullable(clientService.findById(id));

            if(opt.isEmpty()){
                System.out.println("Cliente não encontrado.");
                return;
            }

            Client c = opt.get();
            System.out.print("Novo nome: ");
            c.setName(sc.nextLine());
            System.out.print("Novo documento: ");
            c.setDocument(sc.nextLine());
            System.out.print("Novo email: ");
            c.setEmail(sc.nextLine());
            clientService.save(c.getName(), c.getDocument(), c.getEmail());
            System.out.println("Cliente atualizado!");
        }
    }
