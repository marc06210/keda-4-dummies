package com.mgu.service;

import com.mgu.service.db.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizService {

    private final OrderService orderService;

    public BizService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void process() {
        orderService.getNewOrders().parallelStream()
                .forEach(orderService::processOrder);
    }
}
