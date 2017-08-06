package com.contact.contactboot.db.dao;

import com.contact.contactboot.db.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find all users
    public List<User> findAll() {
        List<User> result = jdbcTemplate.query(
                "SELECT * FROM user",
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("name"))
        );
        return result;
    }

    // Add new user
    public void addUser(String name) {
        jdbcTemplate.update("INSERT INTO user(name) VALUES (?)", name);
    }

}
