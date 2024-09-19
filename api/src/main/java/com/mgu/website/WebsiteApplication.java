package com.mgu.website;

import com.mgu.website.db.Order;
import com.mgu.website.db.OrderRepository;
import com.mgu.website.db.OrderStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class WebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

}

@Controller
@RequestMapping("/orders")
@ResponseBody
class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping({ "", "/" })
    public List<Order> getAll(@RequestParam(name = "status", required = false) String status) {
        return Optional.ofNullable(status)
                .map(String::toUpperCase)
                .map(OrderStatus::valueOf)
                .map( s -> {
                    var order = new Order();
                    order.setStatus(s);
                    return order;
                })
                .map(Example::of)
                .map(repository::findAll)
                .orElse(repository.findAll());
    }

    @PostMapping({ "", "/" })
    public Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}
