package com.sunrise.dentalclinic.dto;
import com.sunrise.dentalclinic.model.*; import java.time.*;
public record AppointmentResponse(Long id,String appointmentNumber,Long patientId,String patientName,Long dentistId,String dentistName,Long treatmentId,String treatmentName,LocalDate appointmentDate,LocalTime appointmentTime,AppointmentStatus status,String notes){
 public static AppointmentResponse from(Appointment a){return new AppointmentResponse(a.getId(),a.getAppointmentNumber(),a.getPatient().getId(),a.getPatient().getName(),a.getDentist().getId(),a.getDentist().getName(),a.getTreatment().getId(),a.getTreatment().getTreatmentName(),a.getAppointmentDate(),a.getAppointmentTime(),a.getStatus(),a.getNotes());}
}
