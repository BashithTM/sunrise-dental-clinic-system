package com.sunrise.dentalclinic.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(indexes=@Index(name="idx_treatment_active",columnList="active"))
public class Treatment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="treatment_name",nullable=false,length=150) private String treatmentName;
    @Column(name="treatment_cost",nullable=false,precision=12,scale=2) private BigDecimal treatmentCost;
    @Column(nullable=false) private boolean active=true;
    @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getTreatmentName(){return treatmentName;} public void setTreatmentName(String v){treatmentName=v;}
    public BigDecimal getTreatmentCost(){return treatmentCost;} public void setTreatmentCost(BigDecimal v){treatmentCost=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}
