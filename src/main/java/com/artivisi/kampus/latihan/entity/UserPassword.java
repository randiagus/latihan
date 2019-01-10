package com.artivisi.kampus.latihan.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class UserPassword extends BaseEntity{

    @OneToOne
    @NotNull
    @MapsId
    private User user;

    @Column(nullable = false)
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
