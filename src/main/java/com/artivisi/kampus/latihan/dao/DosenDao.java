/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.kampus.latihan.dao;

import com.artivisi.kampus.latihan.entity.Dosen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author randi
 */
public interface DosenDao extends PagingAndSortingRepository<Dosen, String>{
    
    Dosen findBykode(String kode);
    
    Page<Dosen> findByKodeContaining(String kode, Pageable pageable);
}
