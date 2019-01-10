package com.artivisi.kampus.latihan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Table
@Entity
public class Ruangan extends BaseEntity{

    @Column(unique = true)
    @NotEmpty(message = "tidak boleh kosong")
    private String kodeRuangan;

    public String getKodeRuangan() {
        return kodeRuangan;
    }

    public void setKodeRuangan(String kodeRuangan) {
        this.kodeRuangan = kodeRuangan;
    }

}
