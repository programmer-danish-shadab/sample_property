package com.demo.service;

import com.demo.entity.Image;
import com.demo.entity.Property;
import com.demo.repository.ImageRepository;
import com.demo.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    private final PropertyRepository propertyRepository;
    private final ImageRepository imageRepository;
    private BucketService bucketService;

    public ImageServiceImpl(PropertyRepository propertyRepository,
                            ImageRepository imageRepository, BucketService bucketService) {
        this.propertyRepository = propertyRepository;
        this.imageRepository = imageRepository;
        this.bucketService = bucketService;
    }

    @Override
    public ResponseEntity<Image> getImages(int propertyId, MultipartFile file, String bucketName) {
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        Image image = new Image();
        image.setImageUrl(imageUrl);
        image.setProperty(property);
        Image savedImage = imageRepository.save(image);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    @Override
    public List<Image> fetchPropertyImages(int propertyId) {
        List<Image> images = imageRepository.findByPropertyId(propertyId);
        return images;
    }
}
