package com.clinic.pago_service.Mapper;

import com.clinic.pago_service.event.Model.Consultation;
import com.clinic.pago_service.event.Model.PaymentModel;
import com.clinic.pago_service.Domain.payment.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", source = "id")  // especifica el campo exacto
    @Mapping(target = "consultation", expression = "java(this.getConsultation(payment))")
    @Mapping(target = "patient", ignore = true) // o mapea si tienes fuente
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "paymentDate", source = "paymentDate")
    @Mapping(target = "canceled", source = "canceled")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    PaymentModel toModel(Payment payment);

    default Consultation getConsultation(Payment payment) {
        return new Consultation(payment.getConsultationId());
    }
}