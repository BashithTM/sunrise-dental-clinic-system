package com.sunrise.dentalclinic.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name="uk_appointment_number",columnNames="appointment_number"), indexes={
 @Index(name="idx_appointment_date",columnList="appointment_date"), @Index(name="idx_appointment_dentist_slot",columnList="dentist_id,appointment_date,appointment_time"),
 @Index(name="idx_appointment_status",columnList="status")})
public class Appointment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="appointment_number",nullable=false,unique=true,length=30) private String appointmentNumber;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="patient_id",nullable=false) private Patient patient;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="dentist_id",nullable=false) private Dentist dentist;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="treatment_id",nullable=false) private Treatment treatment;
    @Column(name="appointment_date",nullable=false) private LocalDate appointmentDate;
    @Column(name="appointment_time",nullable=false) private LocalTime appointmentTime;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private AppointmentStatus status=AppointmentStatus.SCHEDULED;
    @Column(length=1000) private String notes;
    @Column(name="created_at",nullable=false,updatable=false) private LocalDateTime createdAt;
    @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
    @Version private Long version;
    @PrePersist void create(){createdAt=updatedAt=LocalDateTime.now();} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
    public Long getId(){return id;} public String getAppointmentNumber(){return appointmentNumber;} public void setAppointmentNumber(String v){appointmentNumber=v;}
    public Patient getPatient(){return patient;} public void setPatient(Patient v){patient=v;} public Dentist getDentist(){return dentist;} public void setDentist(Dentist v){dentist=v;}
    public Treatment getTreatment(){return treatment;} public void setTreatment(Treatment v){treatment=v;} public LocalDate getAppointmentDate(){return appointmentDate;}
    public void setAppointmentDate(LocalDate v){appointmentDate=v;} public LocalTime getAppointmentTime(){return appointmentTime;} public void setAppointmentTime(LocalTime v){appointmentTime=v;}
    public AppointmentStatus getStatus(){return status;} public void setStatus(AppointmentStatus v){status=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;} public Long getVersion(){return version;}
}
