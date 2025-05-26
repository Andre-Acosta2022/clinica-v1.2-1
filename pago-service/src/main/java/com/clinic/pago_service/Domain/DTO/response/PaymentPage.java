package com.clinic.pago_service.Domain.DTO.response;

import com.clinic.library.dto.response.PageInfo;
import com.clinic.pago_service.event.Model.PaymentModel;
import lombok.Data;

import java.util.List;
@Data
public class PaymentPage {
    List<PaymentModel> payments;
    PageInfo pageInfo;
}
