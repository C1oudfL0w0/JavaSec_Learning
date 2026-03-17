package com.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class User {
    public String username;
    public String password;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public Object object;

    @Override
    public String toString() {
        return "com.json.User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", object=" + object +
                '}';
    }
}