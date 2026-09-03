package com.sunrise.dentalclinic.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", indexes = @Index(name = "idx_user_username", columnList = "username", unique = true))
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=80) private String username;
    @Column(name="password_hash", nullable=false, length=100) private String passwordHash;
    @Column(name="full_name", nullable=false, length=150) private String fullName;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private Role role;
    @Column(nullable=false) private boolean enabled = true;
    @Column(name="created_at", nullable=false, updatable=false) private LocalDateTime createdAt;
    @PrePersist void create(){ if(createdAt==null) createdAt=LocalDateTime.now(); }
    public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public Role getRole(){return role;} public void setRole(Role v){role=v;} public boolean isEnabled(){return enabled;}
    public void setEnabled(boolean v){enabled=v;} public LocalDateTime getCreatedAt(){return createdAt;}
}
