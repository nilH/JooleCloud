package com.itlize.Joole.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="project_id")
    private Integer id;

    @Column(name="project_name")
    @ColumnDefault(value = "''")
    private String projectName;

    @Column(name="time_update")
    @LastModifiedDate
    private LocalDateTime timeUpdate;

    @Column(name="time_created")
    @CreatedDate
    private LocalDateTime timeCreated;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id")
    @ColumnDefault(value = "0")
    private User user;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="project",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<ProjectProduct> projectProduct = new HashSet<>();


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(LocalDateTime timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }


    public Set<ProjectProduct> getProjectProduct() {
        return projectProduct;
    }

    public void setProjectProduct(Set<ProjectProduct> projectProduct) {
        this.projectProduct = projectProduct;
    }


}
