package com.example.book_store.book;

import com.example.book_store.book.controller.BookController;
import com.example.book_store.book.dto.request.CreateBookRequest;
import com.example.book_store.book.dto.response.BookResponse;
import com.example.book_store.book.entity.BookStatus;
import com.example.book_store.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void createBook_shouldReturn201_whenRequestIsValid() throws Exception {

        // Arrange
        BookResponse response = BookResponse.builder()
                .id(1L)
                .isbn("97801346859912")
                .title("Effective Java")
                .price(new BigDecimal("850.00"))
                .status(BookStatus.ACTIVE)
                .build();
        when(bookService.createBook(any(CreateBookRequest.class)))
                .thenReturn(response);

        // Act
        mockMvc.perform(
                post("/api/books")
                        .header("X-API-Version", "1.0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "isbn": "97801346859912",
                                  "title": "Effective Java",
                                  "price": 850.00,
                                  "originalPrice": 1000.00
                                }
                                """)
        )
        // Assert
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.id").value(1))
        .andExpect(jsonPath("$.data.isbn")
                .value("97801346859912"))
        .andExpect(jsonPath("$.data.title")
                .value("Effective Java"))
        .andExpect(jsonPath("$.data.price")
                .value(850.00))
        .andExpect(jsonPath("$.data.status")
                .value("ACTIVE"));

        verify(bookService)
                .createBook(any(CreateBookRequest.class));
    }
}
