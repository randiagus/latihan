package com.artivisi.kampus.latihan.entity;

import com.artivisi.kampus.latihan.constant.JenisKelamin;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table
@Entity
public class Mahasiswa extends BaseEntity {

    private String nama;

    @Column(unique = true)
    private String nim;

    private JenisKelamin jenisKelamin;

    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @ManyToMany
    @JoinTable(
            name="mata_kuliah_mahasiswa",
            joinColumns=@JoinColumn(name="id_mahasiswa", nullable=false),
            inverseJoinColumns=@JoinColumn(name="id_mata_kuliah", nullable=false)
    )
    private Set<MataKuliah> mataKuliahSet;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Set<MataKuliah> getMataKuliahSet() {
        return mataKuliahSet;
    }

    public void setMataKuliahSet(Set<MataKuliah> mataKuliahSet) {
        this.mataKuliahSet = mataKuliahSet;
    }
}
