package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.entity.Favourite;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavouriteService {

    public ResponseEntity<?> addFavourite(AppUser user, int propertyId);

    public ResponseEntity<List<Favourite>> getAllFavourite(AppUser user);

    void deleteFavourite(long favouriteId);


}
