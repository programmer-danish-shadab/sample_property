package com.demo.service;

import com.demo.entity.Property;
import com.demo.payload.PropertyDto;
import com.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;



@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDto addProperty(PropertyDto propertyDto) {
        Property property = new Property();
        property.setPropertyName(propertyDto.getPropertyName());
        property.setBedrooms(propertyDto.getBedrooms());
        property.setBeds(propertyDto.getBeds());
        property.setBathrooms(propertyDto.getBathrooms());
        property.setGuests(propertyDto.getGuests());
        property.setNightlyPrice(propertyDto.getNightlyPrice());
        Property savedProperties = propertyRepository.save(property);
        PropertyDto dto = new PropertyDto();
        dto.setId(property.getId());
        dto.setPropertyName(property.getPropertyName());
        dto.setBedrooms(property.getBedrooms());
        dto.setBathrooms(property.getBathrooms());
        dto.setBeds(property.getBeds());
        dto.setGuests(property.getGuests());
        dto.setNightlyPrice(property.getNightlyPrice());
        return dto;
    }



}
