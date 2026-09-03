package com.sunrise.dentalclinic.service;
import com.sunrise.dentalclinic.dto.DentistRequest; import com.sunrise.dentalclinic.exception.ResourceNotFoundException; import com.sunrise.dentalclinic.model.Dentist; import com.sunrise.dentalclinic.repository.DentistRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service public class DentistService { private final DentistRepository repo; public DentistService(DentistRepository r){repo=r;}
 @Transactional(readOnly=true) public List<Dentist> all(){return repo.findAllByOrderByName();} @Transactional(readOnly=true) public List<Dentist> active(){return repo.findByActiveTrueOrderByName();}
 @Transactional(readOnly=true) public Dentist get(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Dentist not found"));}
 @Transactional public Dentist create(DentistRequest r){return repo.save(map(new Dentist(),r));} @Transactional public Dentist update(Long id,DentistRequest r){return repo.save(map(get(id),r));}
 @Transactional public Dentist toggle(Long id){var d=get(id);d.setActive(!d.isActive());return repo.save(d);} private Dentist map(Dentist d,DentistRequest r){d.setName(r.name().trim());d.setSpecialization(r.specialization().trim());return d;}}
