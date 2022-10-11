package com.ll.exam.springmelonpay.app.product.repository;

import com.ll.exam.springmelonpay.app.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Long> {
}
