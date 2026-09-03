package com.sunrise.dentalclinic.dto;
import jakarta.validation.constraints.*;
public record PatientRequest(@NotBlank @Size(max=150) String name,@NotBlank @Size(max=300) String address,@NotBlank @Pattern(regexp="^[+]?[0-9][0-9 ()-]{6,18}$",message="Enter a valid telephone number") String contactNumber) {}
