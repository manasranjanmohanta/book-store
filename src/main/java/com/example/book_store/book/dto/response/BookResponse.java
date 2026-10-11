package com.example.book_store.book.dto.response;

import com.example.book_store.book.entity.BookStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class BookResponse {
    private Long id;

    private String isbn;

    private String title;

    private String subTitle;

    private String description;

    private String language;

    private String edition;

    private LocalDate publicationDate;

    private BigDecimal price;

    private BigDecimal originalPrice;

    private Integer pages;

    private String format;

    private BookStatus status;

    private LocalDateTime createdAt;
}
