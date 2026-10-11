package com.example.book_store.book.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateBookRequest {
    @NotBlank
    @Size(max = 20)
    private String isbn;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String subtitle;

    private String description;

    private String language;

    private String edition;

    private LocalDate publicationDate;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal originalPrice;

    @Min(1)
    private Integer pages;

    private String format;
}
