package com.contact.contactboot.controller;

import com.contact.contactboot.db.dao.UserRepository;
import com.contact.contactboot.db.entities.User;
import com.contact.contactboot.services.FilterRegEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/hello")
public class UserController {


@Autowired
UserRepository userRepository;

    FilterRegEx filterRegEx;

    public UserController() {
        filterRegEx = new FilterRegEx();
    }



    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/contacts/", method = RequestMethod.GET)
    public ResponseEntity<String> listAllUsers() {
        return new ResponseEntity<String>(String.valueOf(userRepository.findAll()), HttpStatus.OK);
    }


    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam String nameFilter) {
        List<User> list = filterRegEx.getMatchingStrings(userRepository.findAll(),nameFilter);
        return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
    }
}
