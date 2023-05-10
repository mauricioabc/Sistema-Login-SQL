package com.auth.Entities;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author mauricio.rodrigues
 */
@Entity
@Table(name = "[auth].[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private UUID id;
    
    @Column(name = "Nome")
    private String nome;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "PasswordHash")
    private String passwordHash;
    
    @Column(name = "UserTypeId", columnDefinition = "uniqueidentifier")
    private UUID userTypeId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "InsertDate")
    private Date insertDate;

    // construtores, getters e setters

    public User() {
        // construtor vazio necessário para o Hibernate
    }

    public User(String nome, String email, String passwordHash, UUID userTypeId, Date insertDate) {
        this.nome = nome;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userTypeId = userTypeId;
        this.insertDate = insertDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UUID getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UUID userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
    
}