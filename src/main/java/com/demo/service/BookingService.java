package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.entity.Booking;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    public ResponseEntity<Booking> createBooking(Booking booking, AppUser user, int propertyId);
}
