package com.itlize.Joole.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="ProductInfo")
public class ProductInfo {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="productInfo_id")
    private Integer productInfoId;


    @OneToOne(mappedBy="productInfo",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private Product product;


    @Column(name="product_details")
    @ColumnDefault(value = "''")
    private String productDetails;

    @Column(name="contact")
    @ColumnDefault(value = "''")
    private String contact;

    @Column(name="documentation")
    @ColumnDefault(value = "''")
    private String documentation;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductInfo() {

    }


    public Integer getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Integer productInfoId) {
        this.productInfoId = productInfoId;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }



}
