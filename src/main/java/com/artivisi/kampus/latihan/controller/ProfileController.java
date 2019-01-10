package com.artivisi.kampus.latihan.controller;

import com.artivisi.kampus.latihan.dao.UserProfileDao;
import com.artivisi.kampus.latihan.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.util.StringTokenizer;
import java.util.UUID;

@Controller
public class ProfileController {

    @Autowired
    private UserProfileDao userProfileDao;

    @Value("${app.folder.upload}")
    public String uploadDir;

    @GetMapping("/profile")
    public String showProfile(Principal principal, ModelMap modelMap){

        UserProfile profile = userProfileDao.findByUserUsername(principal.getName());
        if(profile != null){
            modelMap.addAttribute("profile",profile);
        }else{
            return "redirect:/login";
        }

        return "profile";
    }

    @PostMapping("/profile")
    public String saveProfile(UserProfile userProfile, MultipartFile fotoProfile){

        try{
            if(fotoProfile != null && !fotoProfile.isEmpty()){
                System.out.println("ProfileController.saveProfile - ada foto");

                //   home/app-tmp/kampus/
                String folder = uploadDir + File.separator;

                //membuat random id gambar
                String random = UUID.randomUUID().toString().substring(0, 7);
                String finalName = random + "_" + fotoProfile.getOriginalFilename();
                System.out.println("ProfileController.saveProfile - finalName = "+finalName);

                //cek apakah dir. upload sudah ada, jika belum maka akan bikin folder tsb
                File folderImages = new File(folder);
                if (!folderImages.exists()) {
                    Files.createDirectories(folderImages.toPath());
                }

                //upload image
                File originalFile = new File(folder + finalName);
                Files.copy(fotoProfile.getInputStream(), originalFile.toPath());

                //simpan nama foto ke dalam field userProfile.foto
                userProfile.setFoto(originalFile.getName());
                userProfileDao.save(userProfile);
            }
        }catch (Exception e){
            System.out.println("error - "+e.getMessage());
        }

        return "redirect:/profile";
    }

}
