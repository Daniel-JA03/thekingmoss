package com.thekingmoss.application.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadFileService {
    private final Cloudinary cloudinary;

    public String saveImage(MultipartFile file)  {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen", e);
        }
    }

    public void deleteImage(String imagenUrl) {
        try {
            // Extraer public_id desde la URL
            String[] parts = imagenUrl.split("/");
            String fileName = parts[parts.length - 1]; // musgo.webp
            String publicId = fileName.substring(0, fileName.lastIndexOf('.')); // musgo

            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar imagen", e);
        }
    }
}
