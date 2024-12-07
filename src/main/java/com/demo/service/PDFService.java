package com.demo.service;


import com.demo.entity.Booking;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PDFService {

    public String generateBookingDetailsPdf(Booking booking) {
        // Create a new Document
        Document document = new Document();
        try {


            // Create a PdfWriter instance to write to a file
            PdfWriter.getInstance(document, new FileOutputStream("E://airbnb-bookings//booking-confirmation" + booking.getId() + ".pdf"));

            // Open the Document
            document.open();

            // Add a title to the PDF
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Booking Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add a space between title and table
            document.add(new Paragraph(" "));

            // Create a table with 2 columns: one for the field name and one for the value
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100); // Set table width to 100% of the page width

            // Add table headers
            PdfPCell cell1 = new PdfPCell(new Phrase("Field"));
            PdfPCell cell2 = new PdfPCell(new Phrase("Value"));
            table.addCell(cell1);
            table.addCell(cell2);

            // Add booking details to the table
            table.addCell("Booking ID");
            table.addCell(booking.getId().toString());

            table.addCell("Guest Name");
            table.addCell(booking.getGuestName());

            table.addCell("Total Nights");
            table.addCell(booking.getTotalNights().toString());

            table.addCell("Total Price");
            table.addCell(String.valueOf(booking.getTotalPrice()));

            table.addCell("Booking Date");
            table.addCell(booking.getBookingDate().toString());

            table.addCell("Check-In Time");
            table.addCell(booking.getCheckInTime().toString());


            // Add the table to the document
            document.add(table);
            return "E://airbnb-bookings//booking-confirmation" + booking.getId() + ".pdf";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document!=null){
                // Close the Document
                document.close();
            }
        }
        return null;
    }
}


//Document from internet itext dependency and document code.
//From internet table code to add in pdf