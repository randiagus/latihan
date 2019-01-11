package com.artivisi.kampus.latihan.controller;

import com.artivisi.kampus.latihan.dao.RuanganDao;
import com.artivisi.kampus.latihan.entity.Ruangan;
import com.artivisi.kampus.latihan.service.RuanganCsvService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RuanganController {

    @Autowired
    private RuanganDao ruanganDao;

    @Autowired private RuanganCsvService ruanganCsvService;

    @GetMapping("/ruangan/list")
    public String showList(ModelMap modelMap,
                           @RequestParam(required = false) String kode,
                           @PageableDefault Pageable pageable) {
        if (StringUtils.hasText(kode)) {
            modelMap.addAttribute("datas", ruanganDao.findByKodeRuanganContaining(kode, pageable));
        } else {
            modelMap.addAttribute("datas", ruanganDao.findAll(pageable));
        }
        return "ruangan/list";
    }

    @GetMapping("/ruangan/form")
    public String showForm(ModelMap modelMap, @RequestParam(required = false) String id) {
//        cek apakah id ada / tidak
        if (StringUtils.hasText(id)) {
//            jika ada, maka akan mengambil data dari database
            Ruangan ruangan = ruanganDao.findById(id).get();

//            cek apakah object ruangan null
            if (ruangan != null) {
                modelMap.addAttribute("ruangan", ruangan);
            } else {
                return "redirect:/404_not_found";
            }
        } else {
//            jika tidak, maka akan membuat data baru
            modelMap.addAttribute("ruangan", new Ruangan());
        }

        return "ruangan/form";
    }

    @PostMapping("/ruangan/form")
    public String prosesFormRuangan(Ruangan ruangan, ModelMap modelMap) {

//        cek ke database apakah ada ruangan dengan kode yang sama
        Ruangan duplicate = ruanganDao.findByKodeRuangan(ruangan.getKodeRuangan());
        if(duplicate != null ){
//            jika id tidak sama maka lempar error kode sudah digunakan
            if(!duplicate.getId().equalsIgnoreCase(ruangan.getId())){
                modelMap.addAttribute("errorMessage","Kode sudah digunakan");
                modelMap.addAttribute("ruangan",ruangan);
                return "ruangan/form";
            }
        }

        ruanganDao.save(ruangan);
        return "redirect:/ruangan/list";
    }

    @GetMapping("/ruangan/delete/{id}")
    public String deleteRuangan(@PathVariable String id){
        Ruangan ruangan = ruanganDao.findById(id).get();
        ruanganDao.delete(ruangan);
        return "redirect:/ruangan/list";
    }

    @PostMapping("/ruangan/import")
    public String importRuangan(MultipartFile file) throws Exception {
        ruanganCsvService.mappingDataRuangan(file);
        return "redirect:/ruangan/list";
    }

    @GetMapping(value = "/ruangan/list", params = {"export"})
    public void exportRuangan(@RequestParam(required = false) String kode, HttpServletResponse response) throws Exception {

        List<Ruangan> listRuangan;
        if(StringUtils.hasText(kode)){
            listRuangan = ruanganDao.findByKodeRuanganContainingIgnoreCase(kode);
        }else{
            listRuangan = Lists.newArrayList(ruanganDao.findAll());
        }

//        csv header/title
        List<String> listHeader = new ArrayList<>();
        listHeader.add("kode_ruangan");

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Ruangan ruangan:listRuangan) {
            Map<String, Object> map = new HashMap<>();

            map.put("kode_ruangan",ruangan.getKodeRuangan());
            mapList.add(map);
        }

        CsvMapWriter csvWriter = null;
        final CsvPreference PIPE_DELIMITED = new CsvPreference.Builder('"', '|', "\n").build();

        try {
            response.setContentType("text/csv");
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    "ruangan" + ".csv");
            response.setHeader(headerKey, headerValue);
            response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
            response.setStatus(200);

            csvWriter = new CsvMapWriter(response.getWriter(), PIPE_DELIMITED);
            if (mapList.size() > 0) {
                String[] csvHeader = listHeader.toArray(new String[listHeader.size()]);
                csvWriter.writeHeader(csvHeader);
                for (Map<String, Object> map : mapList) {
                    csvWriter.write(map, csvHeader);
                }
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (csvWriter != null) {
                try {
                    csvWriter.close();
                } catch (IOException ex) {
                    throw new Exception(ex.getMessage(),ex);
                }
            }
        }
    }
}
