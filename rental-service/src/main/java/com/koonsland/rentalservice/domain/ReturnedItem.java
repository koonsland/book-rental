package com.koonsland.rentalservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "returned_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReturnedItem implements Serializable {
    @Id
    @SequenceGenerator(
            name = "returned_item_id_sequence",
            sequenceName = "returned_item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "returned_item_id_sequence"
    )
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @Column(name = "book_title")
    private String bookTitle;

    private ReturnedItem(
            Long bookId,
            String bookTitle,
            LocalDate now
    ) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.returnedDate = now;
    }


    // 생성 메서드
    private static ReturnedItem createdReturnedItem(
            Long bookId,
            String bookTitle,
            LocalDate now
    ) {
        return new ReturnedItem(bookId, bookTitle, now);
    }
}
