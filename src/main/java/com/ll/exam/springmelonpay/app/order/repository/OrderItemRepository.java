package com.ll.exam.springmelonpay.app.order.repository;

import com.ll.exam.springmelonpay.app.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
}
