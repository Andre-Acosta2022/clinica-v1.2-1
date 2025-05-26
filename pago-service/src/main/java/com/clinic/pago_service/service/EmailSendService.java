package com.clinic.pago_service.service;

import com.clinic.pago_service.Domain.payment.Payment;
import com.clinic.pago_service.event.Model.Consultation;
import org.springframework.stereotype.Service;
import com.clinic.pago_service.event.published.NotificationSendEvent;
@Service
public class EmailSendService {
    public NotificationSendEvent  createPaidNotificationEvent(Payment payment, Consultation consultation, byte[] pdf) {
        NotificationSendEvent notificationSendEvent = new NotificationSendEvent();
        notificationSendEvent.setTo("luigui.acosta@upeu.edu.pe");
        notificationSendEvent.setSubject("Notificación de Consulta");
        notificationSendEvent.setMessage("Gracias por su pago. Adjunto encontrará el documento en PDF correspondiente a su consulta.");
        notificationSendEvent.setPdfFile(pdf);

        return notificationSendEvent;
    }
}
