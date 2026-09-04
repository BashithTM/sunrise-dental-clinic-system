package com.sunrise.dentalclinic.service;

import com.sunrise.dentalclinic.dto.DentistRequest;
import com.sunrise.dentalclinic.exception.BusinessException;
import com.sunrise.dentalclinic.exception.ResourceNotFoundException;
import com.sunrise.dentalclinic.model.Dentist;
import com.sunrise.dentalclinic.repository.AppointmentRepository;
import com.sunrise.dentalclinic.repository.DentistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DentistService {
    private final DentistRepository repo;
    private final AppointmentRepository appointments;

    public DentistService(DentistRepository repo, AppointmentRepository appointments) {
        this.repo = repo;
        this.appointments = appointments;
    }

    @Transactional(readOnly = true) public List<Dentist> all() { return repo.findAllByOrderByName(); }
    @Transactional(readOnly = true) public List<Dentist> active() { return repo.findByActiveTrueOrderByName(); }
    @Transactional(readOnly = true) public Dentist get(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dentist not found")); }
    @Transactional public Dentist create(DentistRequest request) { return repo.save(map(new Dentist(), request)); }
    @Transactional public Dentist update(Long id, DentistRequest request) { return repo.save(map(get(id), request)); }

    @Transactional
    public Dentist toggle(Long id) {
        Dentist dentist = get(id);
        dentist.setActive(!dentist.isActive());
        return repo.save(dentist);
    }

    @Transactional
    public void delete(Long id) {
        Dentist dentist = get(id);
        if (appointments.existsByDentistId(id)) {
            throw new BusinessException("This dentist cannot be deleted because appointment or billing records exist.");
        }
        repo.delete(dentist);
    }

    private Dentist map(Dentist dentist, DentistRequest request) {
        dentist.setName(request.name().trim());
        dentist.setSpecialization(request.specialization().trim());
        return dentist;
    }
}
