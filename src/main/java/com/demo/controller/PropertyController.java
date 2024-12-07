package com.demo.controller;


import com.demo.entity.Property;
import com.demo.exceptions.ResourceNotFound;
import com.demo.payload.PropertyDto;
import com.demo.repository.PropertyRepository;
import com.demo.service.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private PropertyRepository propertyRepository;

    private PropertyService propertyService;


    public PropertyController(PropertyRepository propertyRepository, PropertyService propertyService) {
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;

    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto){
        PropertyDto dto = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Property>> getPropertyListings(@RequestParam String locationName){
      List<Property> propertiesListing =  propertyRepository.listPropertyByLocationNameOrCountryName(locationName);
      return new ResponseEntity<>(propertiesListing, HttpStatus.OK);
    }


    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(
            @PathVariable int propertyId
    ){
        Property property = propertyRepository.findById(propertyId).orElseThrow(   //throw exception using supplierFucnctionalInterface
             ()->new ResourceNotFound("Property not found with id " + propertyId) // which means it will not take input but produces output
        );

        return new ResponseEntity<>(property,HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/properties/all?pageSize=2&pageNo=0&sortBy=propertyName&sortDir=desc
    @GetMapping("/all")
    public ResponseEntity<List<Property>> getAllProperty(
            @RequestParam (name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam (name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam (name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam (name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        Pageable pageable = null;
        if(sortDir.equalsIgnoreCase("asc")){
            pageable  = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else if(sortDir.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }

        Page<Property> all = propertyRepository.findAll(pageable);
        List<Property> properties = all.getContent();
        int pageNumber = pageable.getPageNumber();
        int pageCapacity = pageable.getPageSize();
        int totalPages = all.getTotalPages();
        long totalElements = all.getTotalElements();
        boolean hasNext = all.hasNext();
        boolean hasPrevious = all.hasPrevious();
        boolean last = all.isLast();
        boolean first = all.isFirst();
        return new ResponseEntity<>(properties,HttpStatus.OK);
    }
}
