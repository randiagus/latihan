package com.artivisi.kampus.latihan.entity;


import com.artivisi.kampus.latihan.constant.UserType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table
public class User extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String nama;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private UserPassword userPassword;

    private Boolean aktif= Boolean.TRUE;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }
}
