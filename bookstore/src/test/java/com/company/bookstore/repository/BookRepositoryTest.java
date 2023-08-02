package com.company.bookstore.repository;

import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

        assertTrue(book1.isPresent());
        assertEquals(book1.get(), book);
    }

    @Test
    public void shouldGetAllBooks() {
        Book book = new Book();
        book.setAuthorId(2);
        book.setIsbn("101-222-3333");
        book.setPrice(new BigDecimal("24.99"));
        book.setTitle("A New Novel");
        book.setPublishedDate(LocalDate.of(2020, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        book.setAuthorId(5);
        book.setIsbn("101-543-3333");
        book.setPrice(new BigDecimal("13.99"));
        book.setTitle("An Old Novel");
        book.setPublishedDate(LocalDate.of(1920, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        List<Book> allBooks = bookRepository.findAll();

        assertEquals(allBooks.size(), 2);
    }

    @Test
    public void shouldUpdateBook() {
        Book book = new Book();
        book.setAuthorId(2);
        book.setIsbn("101-222-3333");
        book.setPrice(new BigDecimal("24.99"));
        book.setTitle("A New Novel");
        book.setPublishedDate(LocalDate.of(2020, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        book.setTitle("Uncreative Book");
        bookRepository.save(book);

        Optional<Book> book1 = bookRepository.findById(book.getId());

        assertEquals(book1.get(), book);
    }

    @Test
    public void shouldDeleteBookById() {
        Book book = new Book();
        book.setAuthorId(2);
        book.setIsbn("101-222-3333");
        book.setPrice(new BigDecimal("24.99"));
        book.setTitle("A New Novel");
        book.setPublishedDate(LocalDate.of(2020, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        bookRepository.delete(book);

        Book deletedBook = bookRepository.findById(book.getId()).orElse(null);

        assertNull(deletedBook);
    }

    @Test
    public void shouldSearchBookByAuthorId() {
        Book book = new Book();
        book.setAuthorId(2);
        book.setIsbn("101-222-3333");
        book.setPrice(new BigDecimal("24.99"));
        book.setTitle("A New Novel");
        book.setPublishedDate(LocalDate.of(2020, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        book.setAuthorId(2);
        book.setIsbn("101-543-3333");
        book.setPrice(new BigDecimal("13.99"));
        book.setTitle("An Old Novel");
        book.setPublishedDate(LocalDate.of(1920, 1, 8));
        book.setPublisherId(4);

        book = bookRepository.save(book);

        List<Book> bookList = bookRepository.findBookByAuthorId(2);

        assertEquals(bookList.size(), 2);
    }
}