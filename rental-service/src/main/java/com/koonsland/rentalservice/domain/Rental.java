package com.koonsland.rentalservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rental")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rental implements Serializable {
    // 렌탈 식별자
    @Id
    @SequenceGenerator(
            name = "rental_id_sequence",
            sequenceName = "rental_id_sequence",
            allocationSize = 1
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
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RentedItem> rentedItems = new HashSet<>();

    // 반납 아이템
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReturnedItem> returnedItems = new HashSet<>();


}
