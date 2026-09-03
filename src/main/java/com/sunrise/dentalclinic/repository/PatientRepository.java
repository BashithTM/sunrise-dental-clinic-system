package com.sunrise.dentalclinic.repository;
import com.sunrise.dentalclinic.model.Patient; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface PatientRepository extends JpaRepository<Patient,Long>{ List<Patient> findByNameContainingIgnoreCaseOrContactNumberContainingIgnoreCaseOrderByName(String name,String contact); }
