package com.sunrise.dentalclinic.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(indexes=@Index(name="idx_dentist_active",columnList="active"))
public class Dentist {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=150) private String name;
    @Column(nullable=false,length=150) private String specialization;
    @Column(nullable=false) private boolean active=true;
    @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
    @PrePersist void create(){createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getSpecialization(){return specialization;} public void setSpecialization(String v){specialization=v;}
    public boolean isActive(){return active;} public void setActive(boolean v){active=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}
