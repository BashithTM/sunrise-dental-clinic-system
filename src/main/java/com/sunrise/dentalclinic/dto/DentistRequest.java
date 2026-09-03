package com.sunrise.dentalclinic.dto;
import jakarta.validation.constraints.*;
public record DentistRequest(@NotBlank @Size(max=150) String name,@NotBlank @Size(max=150) String specialization) {}
