package com.demo.payload;

import com.demo.entity.Country;
import com.demo.entity.Location;

public class PropertyDto {

    private long id;
    private String propertyName;
    private long bedrooms;
    private long bathrooms;
    private long beds;
    private long guests;
    private long nightlyPrice;
    private long countryId;
    private long locationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public long getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(long bedrooms) {
        this.bedrooms = bedrooms;
    }

    public long getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(long bathrooms) {
        this.bathrooms = bathrooms;
    }

    public long getBeds() {
        return beds;
    }

    public void setBeds(long beds) {
        this.beds = beds;
    }

    public long getGuests() {
        return guests;
    }

    public void setGuests(long guests) {
        this.guests = guests;
    }

    public long getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(long nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }
}
