package com.demo.service;

import com.demo.entity.Location;
import com.demo.payload.LocationDto;
import com.demo.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationDto addLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setLocationName(locationDto.getLocationName());
        Location savedLocation = locationRepository.save(location);
        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setLocationName(location.getLocationName());
        return dto;
    }
}
