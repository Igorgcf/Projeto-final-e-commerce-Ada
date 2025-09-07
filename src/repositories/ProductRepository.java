package repositories;

import database.InMemory;
import entities.Product;

import java.io.File;

public class ProductRepository extends InMemory<Product> {

    public ProductRepository(File file) {
        super(file, Product.class);
    }
}
