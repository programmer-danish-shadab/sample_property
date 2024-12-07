package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.entity.Favourite;
import com.demo.entity.Property;
import com.demo.repository.FavouriteRepository;
import com.demo.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService{
    private PropertyRepository propertyRepository;
    private final FavouriteRepository favouriteRepository;

    public FavouriteServiceImpl(PropertyRepository propertyRepository,
                                FavouriteRepository favouriteRepository) {
        this.propertyRepository = propertyRepository;
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public ResponseEntity<?> addFavourite(AppUser user, int propertyId) {
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        Favourite favourite = new Favourite();
        favourite.setAppUser(user);
        favourite.setProperty(property);
        favourite.setIsFavourite(true);
        Favourite f = favouriteRepository.fetchUserFavourite(property, user);
        if(f!=null){
            return new ResponseEntity<>("Same favourite exists", HttpStatus.BAD_REQUEST);
        }
        Favourite savedFavourite = favouriteRepository.save(favourite);
        return new ResponseEntity<>(savedFavourite, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Favourite>> getAllFavourite(AppUser user) {
       List<Favourite> listFavourites =  favouriteRepository.getFavourites(user);
        return new ResponseEntity<>(listFavourites,HttpStatus.OK);
    }

    @Override
    public void deleteFavourite(long favouriteId) {
       favouriteRepository.deleteById(favouriteId);
    }



}
