package com.contact.contactboot.services;

import com.contact.contactboot.db.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * We filter the array by the regular expression
 */
public class FilterRegEx {

    public FilterRegEx() {
    }

    public List<User> getMatchingStrings(List<User> list, String regex) {
        ArrayList<User> matches = new ArrayList<User>();
        Pattern p = Pattern.compile(regex);
        for (User u : list) {
            if (!p.matcher(u.getName()).matches()) {
                matches.add(u);
            }
        }
        return matches;
    }

    public String parseRegexToNotLike(String regex) throws PatternSyntaxException {
        //We use for validation, if there is no valid throw exception
        Pattern.compile(regex);

        regex = regex.replace("^", "%");
        regex = regex.replace("$", "%");
        regex = regex.replace(".*", "%");
        regex = "\'" + regex;
        regex = regex + "\'";
        return regex;
    }

}
