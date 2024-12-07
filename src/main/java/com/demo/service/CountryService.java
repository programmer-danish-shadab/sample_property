package com.demo.service;

import com.demo.payload.CountryDto;

import java.util.List;

public interface CountryService {

    public CountryDto addCountry(CountryDto countryDto);

    public List<CountryDto> getAllCountries();
}
