package com.artivisi.kampus.latihan.dao;

import com.artivisi.kampus.latihan.entity.Ruangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RuanganDao extends PagingAndSortingRepository<Ruangan,String> {

    Ruangan findByKodeRuangan(String kode);

//    keyword ruangan boleh huruf besar/kecil
    Page<Ruangan> findByKodeRuanganIgnoreCase(String kode, Pageable pageable);

//    keyword ruangan boleh tidak lengkap dan boleh huruf besar/kecil
    Page<Ruangan> findByKodeRuanganContaining(String kode, Pageable pageable);

//    keyword ruangan boleh tidak lengkap dan boleh huruf besar/kecil
    Page<Ruangan> findByKodeRuanganContainingIgnoreCase(String kode, Pageable pageable);

    List<Ruangan> findByKodeRuanganContainingIgnoreCase(String kode);

}
