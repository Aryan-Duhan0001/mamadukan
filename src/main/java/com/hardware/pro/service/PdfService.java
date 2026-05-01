package com.hardware.pro.service;

import com.hardware.pro.model.Transaction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public byte[] generateBillPdf(List<Transaction> items, String customerName) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        // Bill Header
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        document.add(new Paragraph("HARDWARE PRO - INVOICE", boldFont));
        document.add(new Paragraph("Customer: " + customerName));
        document.add(new Paragraph("--------------------------------------------------"));

        // Items List
        double grandTotal = 0;
        for (Transaction t : items) {
            document.add(new Paragraph(t.getProductName() + " x " + t.getQuantity() + " = ₹" + t.getTotalAmount()));
            grandTotal += t.getTotalAmount();
        }

        document.add(new Paragraph("--------------------------------------------------"));
        document.add(new Paragraph("GRAND TOTAL: ₹" + grandTotal, boldFont));
        document.add(new Paragraph("\nDhanyawad! Wapas Zaroor Aayein."));

        document.close();
        return out.toByteArray();
    }
}