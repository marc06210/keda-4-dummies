package com.mgu.service;

import com.mgu.service.db.Order;
import com.mgu.service.db.OrderRepository;
import com.mgu.service.db.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getNewOrders() {
        Order newOrder = new Order();

        return repository.findAll(Example.of(newOrder));
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void processOrder(Order order) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        order.setStatus(OrderStatus.TREATED);
        repository.save(order);
    }
}
