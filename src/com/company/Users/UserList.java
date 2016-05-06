package com.company.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class UserList extends HashMap<Integer, User> {

    public static UserList userList;

    public UserList(BufferedReader buf) {
        String line;
        try {
            line = buf.readLine();
            while (line != null) {
                String[] columns = line.split("\\|");
                put(Integer.valueOf(columns[0]), new User(columns));
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        userList = this;
    }

}