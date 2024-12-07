package com.demo.repository;

import com.demo.entity.AppUser;
import com.demo.entity.Favourite;
import com.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    @Query("select f from Favourite f where f.appUser=:appUser")
    public List<Favourite> getFavourites(@Param("appUser") AppUser user);

    @Query("select f from Favourite f where f.property=:property and f.appUser=:user")
    Favourite fetchUserFavourite(@Param("property") Property property, @Param("user") AppUser user);

    @Query("select f from Favourite f where f.property=:property")
    Favourite updateFav(@Param("property") Property property);
}