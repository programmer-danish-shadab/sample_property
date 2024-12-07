package com.demo.service;

import com.demo.entity.Property;
import com.demo.payload.PropertyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyService {
    public PropertyDto addProperty(PropertyDto propertyDto);


}
