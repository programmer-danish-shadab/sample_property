package com.demo.controller;

import com.demo.entity.AppUser;
import com.demo.entity.Favourite;
import com.demo.payload.FavouriteDto;
import com.demo.service.FavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteController {
    private FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping("/addFav")
    public ResponseEntity<?> addFavourite(@AuthenticationPrincipal AppUser user,
                                               @RequestParam int propertyId
                                               ){
        return favouriteService.addFavourite(user, propertyId);
    }

    @GetMapping("/favList")
    public ResponseEntity<List<Favourite>> getAllFavouritesOfUser(@AuthenticationPrincipal AppUser user){
       return favouriteService.getAllFavourite(user);
    }


    @DeleteMapping("/deleteFav")
    public ResponseEntity<String> deleteFavourite(@RequestParam long favouriteId){
        favouriteService.deleteFavourite(favouriteId);
        return new ResponseEntity<>("Favourite is deleted", HttpStatus.OK);
    }


}
