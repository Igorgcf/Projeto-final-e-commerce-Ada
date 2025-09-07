package services;

import entities.Client;
import repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client save(String name, String document, String email) {
        Client client = new Client(name, document, email);
        if(document == null || document.isBlank()){
            throw new IllegalArgumentException("Documento é obrigatório.");
        }
        repository.save(client);
        return client;
    }

    public void findAll(){
        List<Client> clients = repository.findAll();
        if(clients.isEmpty()){
            System.out.println("Nenhum cliente cadastrado.");
        }

        for(Client client : clients){
            client.displayDetails();
        }
    }

    public Client findById(Long id){
        Optional<Client> obj = repository.findById(id);

        return obj.orElse(null);
        }
    }

