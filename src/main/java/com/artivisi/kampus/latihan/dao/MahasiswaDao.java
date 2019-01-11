/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.kampus.latihan.dao;

import com.artivisi.kampus.latihan.entity.Mahasiswa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author randi
 */
public interface MahasiswaDao extends PagingAndSortingRepository<Mahasiswa, String>{
    Mahasiswa findBynama(String nama);
    
    Page<Mahasiswa> findBynamaContaining(String nama, Pageable pageable);
}
