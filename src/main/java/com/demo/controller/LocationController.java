package com.demo.controller;

import com.demo.payload.LocationDto;
import com.demo.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/addLocation")
    public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto){
        LocationDto dto = locationService.addLocation(locationDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}

