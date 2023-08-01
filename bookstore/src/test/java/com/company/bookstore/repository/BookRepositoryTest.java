package com.company.bookstore.repository;

import com.company.bookstore.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void shouldAddBook() {
        Book book = new Book();
        book.setAuthorId(2);
        book.setIsbn("101-222-3333");
        book.setPrice(new BigDecimal("24.99"));
        book.setTitle("A New Novel");
        book.setPublishedDate(LocalDate.of(2020, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());

        assertEquals(book1.get(), book);
    }

    @Test
    public void shouldGetBookById() {
        Book book = new Book();
        book.setAuthorId(2);
        book.setIsbn("101-222-3333");
        book.setPrice(new BigDecimal("24.99"));
        book.setTitle("A New Novel");
        book.setPublishedDate(LocalDate.of(2020, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());

        assertEquals(book1.get(), book);
    }
}