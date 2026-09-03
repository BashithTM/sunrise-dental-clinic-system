package com.sunrise.dentalclinic.repository;
import com.sunrise.dentalclinic.model.Dentist; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface DentistRepository extends JpaRepository<Dentist,Long>{ List<Dentist> findAllByOrderByName(); List<Dentist> findByActiveTrueOrderByName(); }
