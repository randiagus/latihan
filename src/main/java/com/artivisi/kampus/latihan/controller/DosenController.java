/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.kampus.latihan.controller;

import org.springframework.data.web.PageableDefault;
import com.artivisi.kampus.latihan.dao.DosenDao;
import com.artivisi.kampus.latihan.entity.Dosen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
public class DosenController {
    
    @Autowired private DosenDao dosenDao;
    
    @GetMapping("/dosen/list")
    public String showList(ModelMap modelmap,@RequestParam(required = false) String kode, @PageableDefault Pageable pageable) {
        if (StringUtils.hasText(kode)) {
            modelmap.addAttribute("datas", dosenDao.findByKodeContaining(kode, pageable));
        } else {
            modelmap.addAttribute("datas", dosenDao.findAll(pageable));
        }
        return "dosen/list";
    }
    
    @GetMapping("/dosen/form")
    public String showForm(ModelMap modelMap, @RequestParam(required = false) String id) {
        if (StringUtils.hasText(id)) {
            Dosen dosen = dosenDao.findById(id).get();
            if (dosen != null) {
                modelMap.addAttribute("dosen", dosen);
            } else {
                return "redirect:/404_not_found";
            }
        } else {
            modelMap.addAttribute("dosen",new Dosen());
        }
        
        return "dosen/form";
    }
    
    @PostMapping("/dosen/form")
    public String prosesFormDosen(Dosen dosen, ModelMap modelMap) {
        
        Dosen duplicate = dosenDao.findBykode(dosen.getKode());
        if (duplicate != null) {
            if (!duplicate.getId().equalsIgnoreCase(dosen.getId())) {
                modelMap.addAttribute("errorMessage", "kode sudah digunakan");
                modelMap.addAttribute("dosen", dosen);
                return "dosen/form";
            }
        }
        dosenDao.save(dosen);
        return "redirect:/dosen/list";
    }
    
    
    @GetMapping("/dosen/delete/{id}")
    public String deleteDosen(@PathVariable String id){
        Dosen dosen = dosenDao.findById(id).get();
        dosenDao.delete(dosen);
        return "redirect:/dosen/list";
    }
    
    
}
