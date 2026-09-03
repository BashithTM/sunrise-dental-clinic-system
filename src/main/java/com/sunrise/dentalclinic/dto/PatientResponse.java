package com.sunrise.dentalclinic.dto;
import com.sunrise.dentalclinic.model.Patient;
public record PatientResponse(Long id,String name,String address,String contactNumber){ public static PatientResponse from(Patient p){return new PatientResponse(p.getId(),p.getName(),p.getAddress(),p.getContactNumber());} }
