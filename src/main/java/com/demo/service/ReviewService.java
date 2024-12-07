package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.entity.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    public ResponseEntity<String> addReview(AppUser user, int propertyId, Review review);

    public void deleteReview(long reviewId);

    public ResponseEntity<List<Review>> getAllReviews(AppUser user);


}
