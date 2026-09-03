package com.sunrise.dentalclinic.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(uniqueConstraints={@UniqueConstraint(name="uk_bill_number",columnNames="bill_number"),@UniqueConstraint(name="uk_bill_appointment",columnNames="appointment_id")}, indexes=@Index(name="idx_bill_created",columnList="created_at"))
public class Bill {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="bill_number",nullable=false,unique=true,length=30) private String billNumber;
    @OneToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="appointment_id",nullable=false,unique=true) private Appointment appointment;
    @Column(name="treatment_cost",nullable=false,precision=12,scale=2) private BigDecimal treatmentCost;
    @Column(name="consultation_fee",nullable=false,precision=12,scale=2) private BigDecimal consultationFee;
    @Column(name="total_amount",nullable=false,precision=12,scale=2) private BigDecimal totalAmount;
    @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getBillNumber(){return billNumber;} public void setBillNumber(String v){billNumber=v;}
    public Appointment getAppointment(){return appointment;} public void setAppointment(Appointment v){appointment=v;} public BigDecimal getTreatmentCost(){return treatmentCost;}
    public void setTreatmentCost(BigDecimal v){treatmentCost=v;} public BigDecimal getConsultationFee(){return consultationFee;} public void setConsultationFee(BigDecimal v){consultationFee=v;}
    public BigDecimal getTotalAmount(){return totalAmount;} public void setTotalAmount(BigDecimal v){totalAmount=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}
