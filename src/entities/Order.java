package entities;

import enums.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Order implements Serializable {
    private static final long SerialVersionUID = 1L;

    private Long id;
    private Long clientId;
    private LocalDateTime orderDate;
    private Status status;
    private final List<OrderedItem> items =  new ArrayList<>();

    public Order(Long id, Long clientId) {
        this.id = id;
        this.clientId = Objects.requireNonNull(clientId);
        this.orderDate = LocalDateTime.now();
        this.status = Status.OPEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Status getStatus() {
        return status;
    }

    public List<OrderedItem> getItems() {
        return items;
    }

    public void setStatus(Status newStatus) {
        this.status = Objects.requireNonNull(newStatus);
    }

    public void addItem(OrderedItem item) {
        items.add(item);
    }

    public void RemoveItemByProduct(Long productId) {
        this.items.removeIf(item -> item.getProductId().equals(productId));
    }

    public void changeQuantity(Long productId, int newQuantity){
        for (OrderedItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
        throw new NoSuchElementException("Item com ID de produto " + productId + " não encontrado no pedido.");
        }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(OrderedItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void displayDetails(){
        System.out.println("Order ID: " + id);
        System.out.println("Client ID: " + clientId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        for (OrderedItem item : items) {
            item.displayDetails();
            System.out.println("------------------------------------------------");
        }
        System.out.println("Total do pedido: R$ " + getTotalAmount());
        System.out.println("------------------------------------------------");
    }
}
