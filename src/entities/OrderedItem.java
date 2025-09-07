package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OrderedItem implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long productId;
    private String productNameSnapshot;
    private BigDecimal salePrice;
    private int quantity;

    public OrderedItem(Long productId, String productNameSnapshot, BigDecimal salePrice, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (salePrice == null || salePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço de venda deve ser maior que zero.");
        }
        this.productId = Objects.requireNonNull(productId);
        this.productNameSnapshot = Objects.requireNonNull(productNameSnapshot);
        this.salePrice = salePrice.setScale(2, RoundingMode.HALF_UP);
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductNameSnapshot() {
        return productNameSnapshot;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return salePrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    public void displayDetails(){
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name Snapshot: " + productNameSnapshot);
        System.out.println("Sale Price: " + salePrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: " + getTotalPrice());
    }
}

