package com.contact.contactboot.db.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class UserRepositoryTest {

    private EmbeddedDatabase embeddedDatabase;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // create database for testing
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addScript("db/create-db.sql") // add scripts schema.sql и data.sql
                .addScript("db/insert-data.sql")
                .setType(EmbeddedDatabaseType.H2)// use base H2
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        userRepository = new UserRepository(jdbcTemplate);
    }

    @Test
    public void testFindAll() throws Exception {
        Assert.assertNotNull(userRepository.findAll());
    }

    @Test
    public void testAddCustomer() throws Exception {
        Assert.assertEquals(3,userRepository.findAll().size());
        userRepository.addUser("Vitya");
        Assert.assertEquals(4,userRepository.findAll().size());
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

}