package com.sunrise.dentalclinic.repository;
import com.sunrise.dentalclinic.model.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.time.*; import java.util.*;
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
 Optional<Appointment> findByAppointmentNumberIgnoreCase(String number);
 boolean existsByAppointmentNumber(String number);
 boolean existsByDentistIdAndAppointmentDateAndAppointmentTimeAndStatusNotAndIdNot(Long dentistId,LocalDate date,LocalTime time,AppointmentStatus status,Long id);
 List<Appointment> findByAppointmentDateOrderByAppointmentTime(LocalDate date);
 List<Appointment> findByAppointmentDateGreaterThanEqualOrderByAppointmentDateAscAppointmentTimeAsc(LocalDate date);
 @Query("select a from Appointment a where a.appointmentDate between :from and :to and (:dentistId is null or a.dentist.id=:dentistId) and (:status is null or a.status=:status) order by a.appointmentDate,a.appointmentTime")
 List<Appointment> report(@Param("from") LocalDate from,@Param("to") LocalDate to,@Param("dentistId") Long dentistId,@Param("status") AppointmentStatus status);
}
