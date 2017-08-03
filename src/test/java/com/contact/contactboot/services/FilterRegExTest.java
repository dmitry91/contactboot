package com.contact.contactboot.services;


import com.contact.contactboot.db.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class FilterRegExTest {

    @Test
    public void testGetMatchingStrings(){
        FilterRegEx filterRegEx = new FilterRegEx();
        List<User> user = new ArrayList<>();
        user.add(new User(1,"Vasya"));
        user.add(new User(2,"Ivan"));
        user.add(new User(3,"Petro"));

        //All names that do not contain the first letter V
        List<User> notV = new ArrayList<>(user);
        notV.remove(0);
        Assert.assertEquals(filterRegEx.getMatchingStrings(user,"^V.*$"), notV);

        //All names that do not contain the last letter o
        List<User> notI = new ArrayList<>(user);
        notI.remove(2);
        Assert.assertEquals(filterRegEx.getMatchingStrings(user,"^.*o$"), notI);
    }
}
