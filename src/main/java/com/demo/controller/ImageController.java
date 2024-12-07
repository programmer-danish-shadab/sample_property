package com.demo.controller;

import com.demo.entity.Image;
import com.demo.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/addImage")
    public ResponseEntity<Image> getImages(@RequestParam int propertyId,
                                           MultipartFile file,
                                           @RequestParam String bucketName
                                           ){
        return imageService.getImages(propertyId, file, bucketName);
    }

    @GetMapping("/getImages")
    public ResponseEntity<List<Image>> fetchPropertyImages(@RequestParam int propertyId){
        List<Image> images = imageService.fetchPropertyImages(propertyId);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
