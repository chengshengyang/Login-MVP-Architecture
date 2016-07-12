package com.github.mvp.data;

import java.util.UUID;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class User {
    private String userId;
    private String userEmail;
    private String password;

    public User() {
        this.userId = UUID.randomUUID().toString();
    }

    public User(String userEmail, String password) {
        this.userId = UUID.randomUUID().toString();
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;
        if (!userEmail.equals(user.userEmail)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + userEmail.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
