package com.artivisi.kampus.latihan.entity;

import com.artivisi.kampus.latihan.constant.JenisKelamin;

import javax.persistence.*;

@Entity
@Table
public class Dosen extends BaseEntity{

    @Column(unique = true)
    private String kode;

    private String nama;

    private String nomorTelepon;

    @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
