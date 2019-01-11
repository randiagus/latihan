/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.kampus.latihan.controller;

import com.artivisi.kampus.latihan.dao.MahasiswaDao;
import com.artivisi.kampus.latihan.dao.MataKuliahDao;
import com.artivisi.kampus.latihan.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author randi
 */
@Controller
public class MahasiswaController {

    @Autowired
    private MahasiswaDao mahasiswaDao;
    @Autowired
    private MataKuliahDao mataKuliahDao;

    @GetMapping("/mahasiswa/list")
    public String showList(ModelMap modelmap, @RequestParam(required = false) String nama, @PageableDefault Pageable pageable) {
        if (StringUtils.hasText(nama)) {
            modelmap.addAttribute("datas", mahasiswaDao.findBynamaContaining(nama, pageable));
        } else {
            modelmap.addAttribute("datas", mahasiswaDao.findAll(pageable));
        }
        return "mahasiswa/list";
    }

    @GetMapping("/mahasiswa/form")
    public String showForm(ModelMap modelMap, @RequestParam(required = false) String id) {
        if (StringUtils.hasText(id)) {
            Mahasiswa mahasiswa = mahasiswaDao.findById(id).get();
            if (mahasiswa != null) {
                modelMap.addAttribute("mahasiswa", mahasiswa);
            } else {
                return "redirect:/404_not_found";
            }
        } else {
            modelMap.addAttribute("mahasiswa", new Mahasiswa());
        }
        modelMap.addAttribute("listMataKuliah", mataKuliahDao.findAll());
        return "mahasiswa/form";
    }

    @PostMapping("mahasiswa/form")
    public String prosesFormMahasiswa(Mahasiswa mahasiswa, ModelMap modelMap) {
        Mahasiswa duplicate = mahasiswaDao.findBynama(mahasiswa.getNama());
        if (duplicate != null) {
            if (!duplicate.getId().equalsIgnoreCase(mahasiswa.getId())) {
                modelMap.addAttribute("errorMessage", "kode sudah digunakan");
                modelMap.addAttribute("mahasiswa", mahasiswa);
                return "mahasiswa/form";
            }
        }
        mahasiswaDao.save(mahasiswa);
        return "redirect:/mahasiswa/list";
    }
    
    @GetMapping("/mahasiswa/delete/{id}")
    public String deleteMahasiswa(@PathVariable String  id){
        Mahasiswa mahasiswa = mahasiswaDao.findById(id).get();
        mahasiswaDao.delete(mahasiswa);
        return "redirect:/mahasiswa/list";
    }

}
