package com.sunrise.dentalclinic.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal;
public record TreatmentRequest(@NotBlank @Size(max=150) String treatmentName,@NotNull @DecimalMin(value="0.00") @Digits(integer=10,fraction=2) BigDecimal treatmentCost) {}
