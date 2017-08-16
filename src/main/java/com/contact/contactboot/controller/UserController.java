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
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/hello")
public class UserController {

@Autowired
UserRepository userRepository;

    private FilterRegEx filterRegEx;

    public UserController() {
        filterRegEx = new FilterRegEx();
    }

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/contacts/", method = RequestMethod.GET)
    public ResponseEntity<String> listAllUsers() {
        List<User> list = userRepository.findAll();
        //print Json string from List
        return new ResponseEntity<String>(String.valueOf(list.stream().map(User::toJson).collect(Collectors.toList())), HttpStatus.OK);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam String nameFilter) {
        try {
            List<User> list = userRepository.findLike(filterRegEx.parseRegexToNotLike(nameFilter));
            if (!nameFilter.isEmpty() && !list.isEmpty()) {
                return new ResponseEntity<String>(String.valueOf(list.stream().map(User::toJson).collect(Collectors.toList())), HttpStatus.OK);
            } else {
                //regular expression is empty or not match, return empty result
                return new ResponseEntity<String>("empty result", HttpStatus.OK);
            }
        } catch (PatternSyntaxException exception) {
            return new ResponseEntity<String>(String.valueOf("Regular expression is not valid"), HttpStatus.OK);
        }
    }
}
