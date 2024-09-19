package com.mgu.website.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String what;
    @NonNull
    private String email;
    private OrderStatus status = OrderStatus.NEW;
}
