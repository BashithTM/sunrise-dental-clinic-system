package com.sunrise.dentalclinic.repository;
import com.sunrise.dentalclinic.model.Treatment; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface TreatmentRepository extends JpaRepository<Treatment,Long>{ List<Treatment> findAllByOrderByTreatmentName(); List<Treatment> findByActiveTrueOrderByTreatmentName(); }
