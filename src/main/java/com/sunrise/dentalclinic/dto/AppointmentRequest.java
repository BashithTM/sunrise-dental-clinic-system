package com.sunrise.dentalclinic.dto;
import jakarta.validation.constraints.*; import java.time.*;
public record AppointmentRequest(@NotNull Long patientId,@NotNull Long dentistId,@NotNull Long treatmentId,@NotNull LocalDate appointmentDate,@NotNull LocalTime appointmentTime,@Size(max=1000) String notes) {}
