package com.clinic.pago_service.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Map;
@AllArgsConstructor
@Service
public class ExportService {
    private final TemplateEngine templateEngine;


    @SneakyThrows
    public byte[] getInvoicePDF(Map<String, Object> invoiceData) {
        Context context = new Context();
        context.setVariables(invoiceData);

        String html = templateEngine.process("invoice/invoice_template", context);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();


        String baseUrl = new ClassPathResource("templates/").getURL().toExternalForm();
        renderer.setDocumentFromString(html, baseUrl);
        renderer.layout();
        renderer.createPDF(baos, false);
        renderer.finishPDF();

        return baos.toByteArray();
    }

    @SneakyThrows
    public byte[] getReceiptPDF(Map<String, Object> invoiceData) {
        Context context = new Context();
        context.setVariables(invoiceData);

        String html = templateEngine.process("receipt/receipt_template", context);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();


        String baseUrl = new ClassPathResource("/templates/").getURL().toExternalForm();
        renderer.setDocumentFromString(html, baseUrl);
        renderer.layout();
        renderer.createPDF(baos, false);
        renderer.finishPDF();

        return baos.toByteArray();
    }
}
