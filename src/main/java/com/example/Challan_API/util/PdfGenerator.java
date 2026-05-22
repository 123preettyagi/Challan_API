package com.example.Challan_API.util;

import java.io.ByteArrayOutputStream;

import com.example.Challan_API.dto.ChallanDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PdfGenerator {

    public static byte[] generateChallanPDF(ChallanDTO challan) 
    {
    	
        try 
        {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();
            
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            document.add(new Paragraph("CHALLAN RECEIPT", titleFont));
            document.add(new Paragraph("----------------------------------------------------"));
            document.add(new Paragraph("Challan ID: " + challan.getId(), normalFont));
            document.add(new Paragraph("User ID: " + challan.getUserId(), normalFont));
            document.add(new Paragraph("Amount: ₹" + challan.getAmount(), normalFont));
            document.add(new Paragraph("Status: " + challan.getStatus(), normalFont));
            document.add(new Paragraph("Payment Date: " + challan.getPaymentDate(), normalFont));
            document.add(new Paragraph("----------------------------------------------------"));

            document.close();
            return out.toByteArray();

        }
        
        catch (Exception e) 
        {
            throw new RuntimeException("Error generating PDF");
        }
        
    }
}