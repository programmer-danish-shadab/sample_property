package com.demo.service;

import com.demo.entity.AppUser;
import com.demo.entity.Booking;
import com.demo.entity.Property;
import com.demo.repository.BookingRepository;
import com.demo.repository.PropertyRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private PDFService pdfService;
    private PropertyRepository propertyRepository;
    private  BookingRepository bookingRepository;
    private BucketService bucketService;
    private TwilioService twilioService;


    public BookingServiceImpl(PDFService pdfService, PropertyRepository propertyRepository,
                              BookingRepository bookingRepository, BucketService bucketService, TwilioService twilioService) {
        this.pdfService = pdfService;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
        this.bucketService = bucketService;
        this.twilioService = twilioService;

    }

    @Override
    public ResponseEntity<Booking> createBooking(Booking booking, AppUser user, int propertyId) {
        Optional<Property> byId = propertyRepository.findById(propertyId);
        Property property = byId.get();
        long nightlyPrice = property.getNightlyPrice();
        long totalPrice = nightlyPrice * booking.getTotalNights();
        booking.setTotalPrice((int) totalPrice);
        booking.setProperty(property);
        booking.setAppUser(user);
        Booking savedBooking = bookingRepository.save(booking);
        String filePath = pdfService.generateBookingDetailsPdf(savedBooking);

        //converting filePath to MultiPart
        try {
            MultipartFile fileMultiPart = BookingServiceImpl.convert(filePath);
            String fileUploadedUrl = bucketService.uploadFile(fileMultiPart, "myairbnb888");
            System.out.println(fileUploadedUrl);
            sendMessage(fileUploadedUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }


    public void sendMessage(String url){
        twilioService.sendSMS("", "Your Booking is confirmed. Click here:"+url);
    }

    //Convert String filePath to MultipartFile using below method
    //from internet for converting filePath to Multipart
    public static MultipartFile convert(String filePath) throws IOException {
        //Load the file from the specified path
        File file = new File(filePath);

        //Read the file content into a byte array
        byte[] fileContent = Files.readAllBytes(file.toPath());

        //Convert byte array to a Resource (ByteArrayResource)
        Resource resource = new ByteArrayResource(fileContent);

        //Create MultipartFile from Resource
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getName();
            }

            @Override
            public String getContentType() {
                return null; // You can set appropriate content type here
            }

            @Override
            public boolean isEmpty() {
                return fileContent.length == 0;
            }

            @Override
            public long getSize() {
                return fileContent.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileContent;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return resource.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), fileContent);
            }
        };
        return multipartFile;
    }
}
