package ru.zvo.datalayer.dto;

import org.springframework.stereotype.Component;

public class User {

    private long id;
    private String nick;
    private Role role;
    private Status status;

    public User() {
    }

    public User(long id, String nick, Role role, Status status) {
        this.id = id;
        this.nick = nick;
        this.role = role;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
