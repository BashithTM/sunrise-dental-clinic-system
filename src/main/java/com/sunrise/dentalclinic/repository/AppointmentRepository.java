package com.sunrise.dentalclinic.repository;
import com.sunrise.dentalclinic.model.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.time.*; import java.util.*;
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
 @Override @EntityGraph(attributePaths={"patient","dentist","treatment"}) List<Appointment> findAll();
 @Override @EntityGraph(attributePaths={"patient","dentist","treatment"}) Optional<Appointment> findById(Long id);
 @EntityGraph(attributePaths={"patient","dentist","treatment"})
 Optional<Appointment> findByAppointmentNumberIgnoreCase(String number);
 boolean existsByAppointmentNumber(String number);
 boolean existsByDentistIdAndAppointmentDateAndAppointmentTimeAndStatusNotAndIdNot(Long dentistId,LocalDate date,LocalTime time,AppointmentStatus status,Long id);
 @EntityGraph(attributePaths={"patient","dentist","treatment"}) List<Appointment> findByAppointmentDateOrderByAppointmentTime(LocalDate date);
 @EntityGraph(attributePaths={"patient","dentist","treatment"}) List<Appointment> findByAppointmentDateGreaterThanEqualOrderByAppointmentDateAscAppointmentTimeAsc(LocalDate date);
 @Query("select a from Appointment a where a.appointmentDate between :from and :to and (:dentistId is null or a.dentist.id=:dentistId) and (:status is null or a.status=:status) order by a.appointmentDate,a.appointmentTime")
 @EntityGraph(attributePaths={"patient","dentist","treatment"}) List<Appointment> report(@Param("from") LocalDate from,@Param("to") LocalDate to,@Param("dentistId") Long dentistId,@Param("status") AppointmentStatus status);
}
