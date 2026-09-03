package com.sunrise.dentalclinic.service;
import com.sunrise.dentalclinic.dto.PatientRequest; import com.sunrise.dentalclinic.exception.ResourceNotFoundException; import com.sunrise.dentalclinic.model.Patient; import com.sunrise.dentalclinic.repository.PatientRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service public class PatientService {
 private final PatientRepository repo; public PatientService(PatientRepository repo){this.repo=repo;}
 @Transactional(readOnly=true) public List<Patient> search(String q){return q==null||q.isBlank()?repo.findAll():repo.findByNameContainingIgnoreCaseOrContactNumberContainingIgnoreCaseOrderByName(q,q);}
 @Transactional(readOnly=true) public Patient get(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient not found"));}
 @Transactional public Patient create(PatientRequest r){return repo.save(map(new Patient(),r));}
 @Transactional public Patient update(Long id,PatientRequest r){return repo.save(map(get(id),r));}
 private Patient map(Patient p,PatientRequest r){p.setName(r.name().trim());p.setAddress(r.address().trim());p.setContactNumber(r.contactNumber().trim());return p;}
}
