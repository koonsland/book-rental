package com.koonsland.rentalservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "rented_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RentedItem implements Serializable {
    @Id
    @SequenceGenerator(
            name = "rented_item_id_sequence",
            sequenceName = "rented_item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rented_item_id_sequence"
    )
    private Long id;

    // 대출한 도서 번호
    @Column(name = "book_id")
    private Long bookId;

    // 대출 시작일
    @Column(name = "rented_date")
    private LocalDate rentedDate;

    // 반납 예정일
    @Column(name = "due_date")
    private LocalDate dueDate;

    // 대출한 도서명
    @Column(name = "book_title")
    private String bookTitle;

    // 연관관계 Rental
    @ManyToOne
    @JsonIgnoreProperties("rentedItems")
    private Rental rental;

    // 생성자
    private RentedItem(
            Long bookId,
            String bookTitle,
            LocalDate rentedDate
    ) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.rentedDate = rentedDate;
        this.dueDate = rentedDate.plusWeeks(2);
    }

    // 생성메서드
    public static RentedItem createRentedItem(
            Long bookId,
            String bookTitle,
            LocalDate rentedDate
    ) {
        return new RentedItem(bookId, bookTitle, rentedDate);
    }
}
