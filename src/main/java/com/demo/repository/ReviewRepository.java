package com.demo.repository;

import com.demo.entity.AppUser;
import com.demo.entity.Property;
import com.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.property=:property and r.appUser=:appUser")
    Review fetchUserReview(@Param("property") Property property, @Param("appUser") AppUser user);

    @Query("select r from Review r where r.appUser=:appUser")
    public List<Review> getUserReviews(@Param("appUser") AppUser user);
}