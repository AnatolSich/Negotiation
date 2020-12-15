package com.springAdvanced.Negotiation;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springAdvanced.Negotiation.model.db.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;

@Service
public class PdfGenerator {
    public ByteArrayInputStream generateUsersPDF(Collection<User> users) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            addUsersToPDFDocument(document, users);

            document.close();
        } catch (DocumentException e) {
            throw  new RuntimeException("Exception happened during generating Users PDF file: ", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static void addUsersToPDFDocument(Document document, Collection<User> users) throws DocumentException {
        document.add(new Phrase("Users:"));
        document.add(takeUsersTable(users));
    }

    private static PdfPTable takeUsersTable(Collection<User> users) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80);
        table.setWidths(new int[] {1,3} );

        for (User user : users) {
            table.addCell(user.getFirst_name());
            table.addCell(user.getLast_name());
        }
        return table;
    }
}
