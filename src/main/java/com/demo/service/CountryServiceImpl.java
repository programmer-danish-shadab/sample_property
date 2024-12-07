package com.demo.service;

import com.demo.entity.Country;
import com.demo.payload.CountryDto;
import com.demo.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService{
    
    private CountryRepository countryRepository;
    private ModelMapper modelMapper; //add dependency use for converting entity to dto in one line
                                     //now create bean manually in config file or main class

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country country = new Country();
        country.setCountryName(countryDto.getCountryName());
        Country savedCountry = countryRepository.save(country);
        CountryDto cdto = new CountryDto();
        cdto.setId(country.getId());
        cdto.setCountryName(country.getCountryName());
        return cdto;
    }

    @Override
    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDto> dtos = countries.stream().map(c -> convertToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    // 1 way
//    CountryDto convertToDto(Country country){
//        CountryDto countryDto = new CountryDto();
//        countryDto.setId(countryDto.getId());
//        countryDto.setCountryName(country.getCountryName());
//        return countryDto;
//    }

// 2way
     CountryDto convertToDto(Country country){
        CountryDto countryDto = modelMapper.map(country,CountryDto.class);
        return countryDto;
     }
}
