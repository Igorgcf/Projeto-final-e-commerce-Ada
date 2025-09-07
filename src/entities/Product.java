package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long id;
    private String name;
    private String sku;
    private BigDecimal basePrice;

    public Product(Long id, String name, String sku, BigDecimal basePrice) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Nome obrigatório!");
        this.sku = Objects.requireNonNull(sku, "SKU obrigatório!");
        this.basePrice = Objects.requireNonNull(basePrice, "Preço obrigatório!");
        ValidateNonNegativePrice(basePrice);
    }

    private void ValidateNonNegativePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = Objects.requireNonNull(sku);
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = Objects.requireNonNull(basePrice);
    }

    public void displayDetails() {
        System.out.println("*****-------------------------------------------******");
        System.out.println("Product ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("SKU: " + sku);
        System.out.println("Base Price: " + basePrice);
        System.out.println("*****-------------------------------------------******");
    }
}
