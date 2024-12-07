package com.demo.controller;

import com.demo.entity.AppUser;
import com.demo.entity.Property;
import com.demo.entity.Review;
import com.demo.repository.PropertyRepository;
import com.demo.repository.ReviewRepository;
import com.demo.service.ReviewService;
import com.demo.service.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(@AuthenticationPrincipal AppUser user,
                                            @RequestParam int propertyId, @RequestBody Review review){
            return reviewService.addReview(user,propertyId,review);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@RequestParam long reviewId){
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review is deleted",HttpStatus.ACCEPTED);
    }

    @GetMapping("/listReview")
    public ResponseEntity<List<Review>> getAllReviews(@AuthenticationPrincipal AppUser user){
       return reviewService.getAllReviews(user);
    }

}
