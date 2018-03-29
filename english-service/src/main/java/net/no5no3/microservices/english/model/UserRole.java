package net.no5no3.microservices.english.model;

import java.io.Serializable;

public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private User user;
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
