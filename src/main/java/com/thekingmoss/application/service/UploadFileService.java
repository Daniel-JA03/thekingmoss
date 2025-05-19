package com.thekingmoss.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {
    private String folder = "imagesProducts//";

    public String saveImage(MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public void deleteImage(String nombre) {
        Path path = Paths.get(folder + nombre);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar la imagen", e);
        }
    }
}
