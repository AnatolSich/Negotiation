package com.springAdvanced.Negotiation.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.springAdvanced.Negotiation.PdfGenerator;
import com.springAdvanced.Negotiation.model.db.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

@Component
public class UsersPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PdfGenerator.addUsersToPDFDocument(document, (Collection<User>) model.get("users"));
    }
}
