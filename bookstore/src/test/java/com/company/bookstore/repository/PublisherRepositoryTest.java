package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository repo;

    @BeforeEach
    void setUp(){
        repo.deleteAll();
    }

    @Test
    public void createNewPublisherTest(){
        Publisher publisher = new Publisher(1, "Izaac Ramirez", "Elmcroft", "Norwalk",
                "California", "90650", "(562)846-8623", "izaacramirez1402@gmail.com");
        publisher = repo.save(publisher);

        Optional<Publisher> newPublisher = repo.findById(publisher.getId());

        assertEquals(newPublisher.get(), publisher);
    }

    @Test
    public void getPublisherByIdTest(){
        Publisher publisher = new Publisher(1, "Izaac Ramirez", "Elmcroft", "Norwalk",
                "California", "90650", "(562)846-8623", "izaacramirez1402@gmail.com");
        publisher = repo.save(publisher);

        Optional<Publisher> retrievedPublisher = repo.findById(publisher.getId());

        assertTrue(retrievedPublisher.isPresent());
        assertEquals(retrievedPublisher.get(), publisher);
    }

    @Test
    public void getAllPublishersTest(){

    }

    @Test
    public void updatePublisherTest(){
        Publisher publisher = new Publisher(1, "Izaac Ramirez", "Elmcroft", "Norwalk",
                "California", "90650", "(562)846-8623", "izaacramirez1402@gmail.com");
        publisher = repo.save(publisher);

        publisher.setCity("Downey");

        repo.save(publisher);

        Optional<Publisher> newPublisher = repo.findById(publisher.getId());

        assertEquals(newPublisher.get(), publisher);
    }

    @Test
    public void deletePublisherTest(){
        Publisher publisher = new Publisher(1, "Izaac Ramirez", "Elmcroft", "Norwalk",
                "California", "90650", "(562)846-8623", "izaacramirez1402@gmail.com");
        publisher = repo.save(publisher);

        repo.delete(publisher);

        Publisher deletedPublisher = repo.findById(publisher.getId()).orElse(null);

        assertNull(deletedPublisher);
    }

}