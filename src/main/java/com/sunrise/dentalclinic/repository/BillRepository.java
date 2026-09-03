package com.sunrise.dentalclinic.repository;
import com.sunrise.dentalclinic.model.Bill; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.math.BigDecimal; import java.time.*; import java.util.Optional;
public interface BillRepository extends JpaRepository<Bill,Long>{
 @Override @EntityGraph(attributePaths={"appointment","appointment.patient","appointment.dentist","appointment.treatment"}) Optional<Bill> findById(Long id);
 Optional<Bill> findByBillNumberIgnoreCase(String number); Optional<Bill> findByAppointmentId(Long id); boolean existsByAppointmentId(Long id); boolean existsByBillNumber(String number);
 @Query("select coalesce(sum(b.totalAmount),0) from Bill b where b.createdAt>=:from and b.createdAt<:to") BigDecimal revenue(@Param("from") LocalDateTime from,@Param("to") LocalDateTime to);
}
