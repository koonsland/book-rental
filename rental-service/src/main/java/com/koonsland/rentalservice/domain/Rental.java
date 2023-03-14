package com.koonsland.rentalservice.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "rental")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rental implements Serializable {
    // 렌탈 식별자
    @Id
    @SequenceGenerator(
            name = "rental_id_sequence",
            sequenceName = "rental_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rental_id_sequence"
    )
    private Long id;

    // 사용자 식별자
    @Column(name = "user_id")
    private Long userId;

    // 대출 가능 여부
    @Enumerated(value = EnumType.STRING)
    @Column(name = "rental_status")
    private RentalStatus rentalStatus;

    // 연체료
    @Column(name = "late_fee")
    private Long lateFee;

    // 대출 아이템
}
