package com.ll.exam.springmelonpay.app.order.entity;

import com.ll.exam.springmelonpay.app.base.entity.BaseEntity;
import com.ll.exam.springmelonpay.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "product_order") // JPA를 사용시 Order라는 테이블 명을 사용할 수 없다.
public class Order extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    private Member buyer;

    @Builder.Default
    @OneToMany(mappedBy = "order" , cascade = ALL , orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);

        orderItems.add(orderItem);
    }

    public int calculatePayPrice() {
        int payPrice = 0;

        for (OrderItem orderItem : orderItems) {
            payPrice += orderItem.getPayPrice();
        }

        return payPrice;
    }

    public void setPaymentDone() {
        for (OrderItem orderItem : orderItems) {
            orderItem.setPaymentDone();
        }
    }

    public void setRefundDone(){
        for (OrderItem orderItem : orderItems) {
            orderItem.setRefundDone();
        }
    }

    public int getPayPrice() {
        int payPrice = 0;
        for (OrderItem orderItem : orderItems) {
            payPrice += orderItem.getPayPrice();
        }
        return payPrice;
    }
}
