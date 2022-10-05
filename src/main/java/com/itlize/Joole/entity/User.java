package com.itlize.Joole.entity;


import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import java.time.LocalDateTime;



@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="customer_id")
    private Integer id;

    @Column(name="pwd")
    @ColumnDefault(value = "''")
    private String password;

    @Column(name="customer_name",unique = true,nullable = false)
    private String name;

    @Column(name="time_updated")
    @LastModifiedDate
    private LocalDateTime timeUpdated;


    @Column(name="time_created")
    @CreatedDate
    private LocalDateTime timeCreated;


//    @OneToMany(fetch=FetchType.LAZY,
//            mappedBy="user",
//            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    private List<Project> project;

    //user type: customer, consumer, admin

    @ColumnDefault(value = "'customer'")
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

//    public List<Project> getProject() {
//        return project;
//    }
//
//    public void setProject(List<Project> project) {
//        this.project = project;
//    }


}
