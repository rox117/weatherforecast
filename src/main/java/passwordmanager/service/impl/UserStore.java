package passwordmanager.service.impl;

import passwordmanager.service.Storage;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserStore implements Storage {
    /**
     *
     * Implementation of storage abstraction using a hashmap.
     * Credentials would normally be stored in a secured database.
     * For this demo a hashmap is used.
     *
     * */

    private static Map<String, String> users = new HashMap<>();

    @Override
    public boolean saveUser(String userName, String password) {
        if (!users.containsKey(userName)){
            users.put(userName,password);
            return true;
        }
        return false;
    }

    @Override
    public boolean userExists(String userName) {
        if (users.containsKey(userName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyUserPassword(String userName, String password) {
        if (users.get(userName).equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public boolean clearUsers() {
        users.clear();
        return true;
    }

    @Override
    public Map<String, String> intializeUsers() {
        return  Stream.of(new String[][] {
                { "john",    "thef1tZ!" },
                { "lyndon",  "Baine$99" },
                { "richard", "ThelmA&!" },
                { "gerald",  "L3slie?H" },
                { "james",   "jimn0bel" },
                { "ronald",  "DrWils0n" },
                { "george",  "sup3rse7" },
                { "william", "nocig4ar" },
                { "georgew", "t0matoes" },
                { "barrack", "hawaii%+" }
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }
}
