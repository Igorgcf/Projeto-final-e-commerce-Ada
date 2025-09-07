package repositories;

import database.InMemory;
import entities.Order;

import java.io.File;

public class OrderRepository extends InMemory<Order> {

    public OrderRepository(File file) {
        super(file, Order.class);
    }
}
