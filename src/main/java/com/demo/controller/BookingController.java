package com.demo.controller;

import com.demo.entity.AppUser;
import com.demo.entity.Booking;
import com.demo.service.BookingService;
import com.demo.service.PDFService;
import com.demo.service.TwilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private PDFService pdfService;

    private BookingService bookingService;

    private TwilioService twilioService;

    public BookingController(PDFService pdfService, BookingService bookingService, TwilioService twilioService) {
        this.pdfService = pdfService;
        this.bookingService = bookingService;
        this.twilioService = twilioService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking,
                                                 @AuthenticationPrincipal AppUser user,
                                                 @RequestParam int propertyId
                                                 ){
       return bookingService.createBooking(booking,user,propertyId);

    }



    public void sendMessage(String url){
        twilioService.sendSMS("+918123056159", "Your Booking is confirmed. Click here:"+url);
    }



}
