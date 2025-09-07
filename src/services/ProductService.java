package services;

import entities.Product;
import repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product save(String name, String sku, BigDecimal basePrice){
        Product product = new Product(null, name, sku, basePrice);
        repository.save(product);
        return product;
    }


    public void findAll(){

        List<Product> products = repository.findAll();
        if(products.isEmpty()){
            System.out.println("Nenhum produto cadastrado.");
        }
        for(Product product : products){
            product.displayDetails();
        }
    }

    public Product findById(Long id){

        Optional<Product> obj = repository.findById(id);

        return obj.orElse(null);
    }
}
