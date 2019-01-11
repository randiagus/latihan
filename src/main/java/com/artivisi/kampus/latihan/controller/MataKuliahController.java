/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.kampus.latihan.controller;

import com.artivisi.kampus.latihan.dao.DosenDao;
import com.artivisi.kampus.latihan.dao.MataKuliahDao;
import com.artivisi.kampus.latihan.dao.RuanganDao;
import com.artivisi.kampus.latihan.entity.MataKuliah;
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
public class MataKuliahController {

    @Autowired
    private MataKuliahDao mataKuliahDao;
    @Autowired
    private DosenDao dosenDao;
    @Autowired
    private RuanganDao ruanganDao;

    @GetMapping("/matakuliah/list")
    public String showList(ModelMap modelmap, @RequestParam(required = false) String kode, @PageableDefault Pageable pageable) {
        if (StringUtils.hasText(kode)) {
            modelmap.addAttribute("datas", mataKuliahDao.findByKodeContaining(kode, pageable));
        } else {
            modelmap.addAttribute("datas", mataKuliahDao.findAll(pageable));
        }

        return "matakuliah/list";
    }

    @GetMapping("/matakuliah/form")
    public String showForm(ModelMap modelMap, @RequestParam(required = false) String id) {
        if (StringUtils.hasText(id)) {
            MataKuliah mataKuliah = mataKuliahDao.findById(id).get();
            if (mataKuliah != null) {
                modelMap.addAttribute("mataKuliah", mataKuliah);
            } else {
                return "redirect:/404_not_found";
            }
        } else {
            modelMap.addAttribute("mataKuliah", new MataKuliah());

        }
        modelMap.addAttribute("listDosen", dosenDao.findAll());
        modelMap.addAttribute("listRuangan", ruanganDao.findAll());
        return "matakuliah/form";
    }

    @PostMapping("/matakuliah/form")
    public String prosesFormMatakuliah(MataKuliah mataKuliah, ModelMap modelMap) {

        MataKuliah duplicate = mataKuliahDao.findBykode(mataKuliah.getKode());
        if (duplicate != null) {
            if (!duplicate.getId().equalsIgnoreCase(mataKuliah.getId())) {
                modelMap.addAttribute("errorMessage", "kode sudah digunakan");
                modelMap.addAttribute("matakuliah", mataKuliah);
                return "matakuliah/form";
            }
        }
        mataKuliahDao.save(mataKuliah);
        return "redirect:/matakuliah/list";
    }

    @GetMapping("/matakuliah/delete/{id}")
    public String deleteMataKuliah(@PathVariable String id) {
        MataKuliah mataKuliah = mataKuliahDao.findById(id).get();
        mataKuliahDao.delete(mataKuliah);
        return "redirect:/matakuliah/list";
    }
}
