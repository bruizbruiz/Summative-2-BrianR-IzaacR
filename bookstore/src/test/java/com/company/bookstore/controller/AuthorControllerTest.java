package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Mock
    private AuthorRepository mockRepo;
    @InjectMocks
    private AuthorController authorController;
    private MockMvc mockMvc;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void shouldAddAuthor() throws Exception {
        Author author = new Author(1, "Chiamaka", "Udechukwu", "Fenimore",
                "Brooklyn", "New york", "11225", "4155703748",
                "chaiamaka@gmailc.om");

        // The repo.save() method will return the same customer object with an ID assigned
        when(mockRepo.save(any(Author.class))).thenReturn(author);

        // Perform the POST request
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{...JSON content for customer...}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.firstName").value(author.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(author.getLastName()))
                .andExpect(jsonPath("$.street").value(author.getStreet()))
                .andExpect(jsonPath("$.city").value(author.getCity()))
                .andExpect(jsonPath("$.state").value(author.getState()))
                .andExpect(jsonPath("$.postalCode").value(author.getPostalCode()))
                .andExpect(jsonPath("$.phone").value(author.getPhone()))
                .andExpect(jsonPath("$.email").value(author.getEmail()));


        // Verify that the repo.save() method was called once
        verify(mockRepo, times(1)).save(any(Author.class));
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldUpdateAuthor() throws Exception {
        Author author = new Author(1, "Chiamaka", "Udechukwu", "Fenimore",
                "Brooklyn", "New york", "11225", "4155703748",
                "chaiamaka@gmailc.om");

        when(mockRepo.save(any(Author.class))).thenReturn(author);
        when(mockRepo.findById(1)).thenReturn(Optional.of(author));


        mockMvc.perform(put("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{...JSON content for updated author...}"))
                .andExpect(status().isNoContent());

        verify(mockRepo, times(1)).findById(1);
        verify(mockRepo, times(1)).save(any(Author.class));
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        Author author = new Author(1, "Chiamaka", "Udechukwu", "Fenimore",
                "Brooklyn", "New york", "11225", "4155703748",
                "chaiamaka@gmailc.om");

        // Simulate the author already existing in the database
        when(mockRepo.findById(1)).thenReturn(Optional.of(author));
        doNothing().when(mockRepo).deleteById(1);

        // Perform the DELETE request
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());

        // Verify that the repo.findById() and repo.deleteById() methods were called
        verify(mockRepo, times(1)).findById(1);
        verify(mockRepo, times(1)).deleteById(1);
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldGetCustomers() throws Exception {
        Author author = new Author(1, "Chiamaka", "Udechukwu", "Fenimore",
                "Brooklyn", "New york", "11225", "4155703748",
                "chaiamaka@gmailc.om");

        Author author1 = new Author(1, "Kenechi", "Udechukwu", "Fenimsjkore",
                "Brooklyn", "New york city", "11225", "4155878703748",
                "kene@gmailc.om");

        List<Author> authors = Arrays.asList(author, author1);
        when(mockRepo.findAll()).thenReturn(authors);

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(authors.size()))
                .andExpect(jsonPath("$[0].id").value(author.getId()))
                .andExpect(jsonPath("$[0].firstName").value(author.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(author.getLastName()))
                .andExpect(jsonPath("$[0].street").value(author.getStreet()))
                .andExpect(jsonPath("$[0].city").value(author.getCity()))
                .andExpect(jsonPath("$[0].state").value(author.getState()))
                .andExpect(jsonPath("$[0].postalCode").value(author.getPostalCode()))
                .andExpect(jsonPath("$[0].phone").value(author.getPhone()))
                .andExpect(jsonPath("$[0].email").value(author.getEmail()));

        verify(mockRepo, times(1)).findAll();
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
        Author author = new Author(1, "Chiamaka", "Udechukwu", "Fenimore",
                "Brooklyn", "New york", "11225", "4155703748",
                "chaiamaka@gmailc.om");

        when(mockRepo.findById(1)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(author.getId()))
                .andExpect(jsonPath("$.firstName").value(author.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(author.getLastName()))
                .andExpect(jsonPath("$.street").value(author.getStreet()))
                .andExpect(jsonPath("$.city").value(author.getCity()))
                .andExpect(jsonPath("$.state").value(author.getState()))
                .andExpect(jsonPath("$.postalCode").value(author.getPostalCode()))
                .andExpect(jsonPath("$.phone").value(author.getPhone()))
                .andExpect(jsonPath("$.email").value(author.getEmail()));

        verify(mockRepo, times(1)).findById(1);
        verifyNoMoreInteractions(mockRepo);
    }
}
