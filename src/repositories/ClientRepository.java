package repositories;

import database.InMemory;
import entities.Client;

import java.io.File;

public class ClientRepository extends InMemory<Client> {

    public ClientRepository(File file) {
        super(file, Client.class);
    }
}
