package com.contact.contactboot.controller;
import com.contact.contactboot.db.dao.UserRepository;
import com.contact.contactboot.db.entities.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController uc;

    @Test
    public void testListAllUsers() throws Exception {
        //add empty list
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<String> s=  uc.listAllUsers();
        //Was the method called .findAll()
        verify(userRepository).findAll();
    }

    @Test
    public void testGetUser() throws Exception {
        List<User> user = new ArrayList<>();
        user.add(new User(1, "Vasya"));
        user.add(new User(2, "Ivan"));
        user.add(new User(3, "Petro"));

        when(userRepository.findAll()).thenReturn(user);
        //Do not output items in which there is 'a'
        ResponseEntity s = uc.getUser(".*a.*$");
        Assert.assertEquals(s.getBody(), user.subList(2,3).toString());
    }
}