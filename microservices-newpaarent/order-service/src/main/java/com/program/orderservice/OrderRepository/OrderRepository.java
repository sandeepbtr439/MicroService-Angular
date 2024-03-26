package com.program.orderservice.OrderRepository;

import com.program.orderservice.OrderModel.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
