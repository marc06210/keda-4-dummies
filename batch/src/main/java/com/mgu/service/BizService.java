package com.mgu.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BizService {

    private final OrderService orderService;

    public BizService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 1000)
    public void process() {
        orderService.getNewOrders().parallelStream()
                .forEach(orderService::processOrder);
    }
}
