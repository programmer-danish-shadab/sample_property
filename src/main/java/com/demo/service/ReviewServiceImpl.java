package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.entity.Property;
import com.demo.entity.Review;
import com.demo.repository.PropertyRepository;
import com.demo.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    public ResponseEntity<String> addReview(AppUser user, int propertyId,Review review){
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        review.setAppUser(user);
        review.setProperty(property);
        Review r = reviewRepository.fetchUserReview(property, user);
        if(r!=null){
            return new ResponseEntity<>("Review is already given", HttpStatus.BAD_REQUEST);
        }
        reviewRepository.save(review);
        return new ResponseEntity<>("Review is added", HttpStatus.CREATED);
    }



    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public ResponseEntity<List<Review>> getAllReviews(AppUser user){
        List<Review> userReviews = reviewRepository.getUserReviews(user);
        return new ResponseEntity<>(userReviews,HttpStatus.OK);
    }


}

