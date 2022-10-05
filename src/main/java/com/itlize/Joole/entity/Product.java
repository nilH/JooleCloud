package com.itlize.Joole.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product",indexes = @Index(name = "lineSearch",columnList = "type"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name="product_name")
    @ColumnDefault("''")
    private String productName;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="product",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<ProjectProduct> projectProductSet = new HashSet<>();


    @Column(name="time_created")
    @CreatedDate
    private LocalDateTime timeCreated;


    @Column(name = "model_year")
    @ColumnDefault("0")
    private Integer modelYear;
    @ColumnDefault("''")
    private String brand;
    @ColumnDefault("''")
    private String certificate;

    @ColumnDefault("''")
    private String category;

    //type is used for search. like "HVAC fans"
    @Column(nullable = false,length = 50)
    @ColumnDefault("''")
    private String type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "product_info_id")
    private ProductInfo productInfo;

    @ColumnDefault("''")
    private String manufacturer;

    @Column(name = "user_type")
    @ColumnDefault("''")
    private String userType;
    @ColumnDefault("''")
    private String application;
    @Column(name = "mounting_location")
    @ColumnDefault("''")
    private String mountingLocation;
    @ColumnDefault("0")
    private double airflow;
    @ColumnDefault("0")
    @Column(name = "max_power")
    private double maxPower;
    @ColumnDefault("0")
    @Column(name = "sound_at_max_speed")
    private double soundAtMaxSpeed;
    @ColumnDefault("0")
    @Column(name = "fan_sweep_diameter")
    private double fanSweepDiameter;
    @ColumnDefault("0")
    private double height;
    @ColumnDefault("''")
    private String accessories;


    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getCategory() {
        return category;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getManufacturer() {
        return manufacturer;
    }


    public double getAirflow() {
        return airflow;
    }

    public void setAirflow(double airflow) {
        this.airflow = airflow;
    }

    public double getFanSweepDiameter() {
        return fanSweepDiameter;
    }

    public void setFanSweepDiameter(double fanSweepDiameter) {
        this.fanSweepDiameter = fanSweepDiameter;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower = maxPower;
    }

    public double getSoundAtMaxSpeed() {
        return soundAtMaxSpeed;
    }

    public void setSoundAtMaxSpeed(double soundAtMaxSpeed) {
        this.soundAtMaxSpeed = soundAtMaxSpeed;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getMountingLocation() {
        return mountingLocation;
    }

    public void setMountingLocation(String mountingLocation) {
        this.mountingLocation = mountingLocation;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }



    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProjectProductSet(Set<ProjectProduct> set) {
        this.projectProductSet = set;
    }

    public Set<ProjectProduct> getProjectProductSet() {
        return projectProductSet;
    }


}
