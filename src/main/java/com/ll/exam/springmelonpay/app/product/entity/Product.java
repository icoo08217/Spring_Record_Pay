package com.ll.exam.springmelonpay.app.product.entity;

import com.ll.exam.springmelonpay.app.base.entity.BaseEntity;
import com.ll.exam.springmelonpay.app.member.entity.Member;
import com.ll.exam.springmelonpay.app.song.entity.Song;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
public class Product extends BaseEntity {

    private String subject;

    @ManyToOne(fetch = LAZY)
    private Member author;

    @ManyToOne(fetch = LAZY)
    private Song song;
    private int price;
}
