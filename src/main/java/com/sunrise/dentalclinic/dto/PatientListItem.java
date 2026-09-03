package com.sunrise.dentalclinic.dto;
import com.sunrise.dentalclinic.model.Patient;
public record PatientListItem(Long id,String name,String contactNumber,String address,long appointmentCount){public static PatientListItem from(Patient p,long count){return new PatientListItem(p.getId(),p.getName(),p.getContactNumber(),p.getAddress(),count);}}
