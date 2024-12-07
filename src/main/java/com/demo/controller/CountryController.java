package com.demo.controller;

import com.demo.payload.CountryDto;
import com.demo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto){
      CountryDto cdto =  countryService.addCountry(countryDto);
      return new ResponseEntity<>(cdto, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries(){
        return new ResponseEntity<>(countryService.getAllCountries(),HttpStatus.OK);
    }

}
