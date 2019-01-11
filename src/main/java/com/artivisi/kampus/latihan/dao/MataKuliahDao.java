/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.kampus.latihan.dao;

import com.artivisi.kampus.latihan.entity.MataKuliah;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author randi
 */
public interface MataKuliahDao extends PagingAndSortingRepository<MataKuliah, String>{
    MataKuliah findBykode(String kode);
    
    Page<MataKuliah> findByKodeContaining(String kode, Pageable pageable);
}
