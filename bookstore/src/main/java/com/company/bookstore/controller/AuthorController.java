package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository repo;

    @GetMapping("/authors")
    List<Author> getAuthors() {
        return repo.findAll();
    }

    @GetMapping("/customers/id/{Id}")
    public Author getAuthorById(@PathVariable int id) {
        return repo.findById(id);
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author customer) {
        return repo.save(customer);
    }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Author updateAuthor(@RequestBody Author author) {
        return repo.save(author);
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        repo.deleteById(id);
    }

}

