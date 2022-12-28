package com.example.cruisecompany.entity;

import java.util.Objects;

public class User {

    private int id;
    private String userName;
    private String password;
    private boolean isAdmin;
    private boolean isBanned;
    private int accountBalance;

    public User() {
    }

    public User(int id, String userName, String password, boolean isAdmin, boolean isBanned, int accountBalance) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && isAdmin == user.isAdmin && isBanned == user.isBanned && accountBalance == user.accountBalance && userName.equals(user.userName) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, isAdmin, isBanned, accountBalance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
}
