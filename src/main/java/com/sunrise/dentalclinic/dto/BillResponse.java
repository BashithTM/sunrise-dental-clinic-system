package com.sunrise.dentalclinic.dto;
import com.sunrise.dentalclinic.model.Bill; import java.math.BigDecimal; import java.time.LocalDateTime;
public record BillResponse(Long id,String billNumber,String appointmentNumber,String patientName,String dentistName,String treatmentName,BigDecimal treatmentCost,BigDecimal consultationFee,BigDecimal totalAmount,LocalDateTime createdAt){
 public static BillResponse from(Bill b){var a=b.getAppointment();return new BillResponse(b.getId(),b.getBillNumber(),a.getAppointmentNumber(),a.getPatient().getName(),a.getDentist().getName(),a.getTreatment().getTreatmentName(),b.getTreatmentCost(),b.getConsultationFee(),b.getTotalAmount(),b.getCreatedAt());}
}
