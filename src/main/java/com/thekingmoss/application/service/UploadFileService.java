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

    public String saveImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return (String) uploadResult.get("secure_url");
        }
        return "default.jpg";
    }

    public void deleteImage(String imagenUrl) {
        try {
            String publicId = imagenUrl
                    .substring(imagenUrl.indexOf("/upload/") + 8)
                    .replaceAll("v\\d+/", "") // elimina versión
                    .substring(0, imagenUrl.lastIndexOf("."));

            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar imagen", e);
        }
    }
}
