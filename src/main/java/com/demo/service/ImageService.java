package com.demo.service;

import com.demo.entity.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    public ResponseEntity<Image> getImages(int propertyId, MultipartFile file, String bucketName);

    public List<Image> fetchPropertyImages(int propertyId);
}
