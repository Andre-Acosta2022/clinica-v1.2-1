package com.clinic.pago_service.event.published;

import lombok.Data;

@Data
public class NotificationSendEvent {
    private String to;
    private String subject;
    private String message;
    private byte[] pdfFile;
}
