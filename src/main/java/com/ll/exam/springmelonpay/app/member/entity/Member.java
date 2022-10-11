package com.ll.exam.springmelonpay.app.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.springmelonpay.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean emailVerified;

    private long restCash; // 잔여 캐시

    public String getName() {
        return username;
    }

    public Member(long id) {
        super(id);
    }
}
