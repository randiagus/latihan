package com.artivisi.kampus.latihan.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;

@Controller
public class ImageController {

    @Value("${app.folder.upload}")
    public String uploadDir;

    @GetMapping("/image/profile/{imageName}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable String imageName){
        try {
            Path filePath = new File(uploadDir).toPath().resolve(imageName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\""
                                + resource.getFilename() + "\"")
                        .body(resource);
            }else{
                return null;
            }

        } catch (MalformedURLException ex) {
            System.out.println("ImageController.showImage - "+ex.getMessage());
            return null;
        }
    }




}
