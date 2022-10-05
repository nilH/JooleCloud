package com.itlize.Joole.service;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    int addProduct(Product product);
    int deleteProduct(Product product);
    int updateProduct(Product product, int productId);

    Product findById(Integer productId);

    List<Product> findAllProduct();
    List<Product> findByName(String productName);

    List<Project> getProjectFromProduct(int productId);
    List<Product> searchByType(String name);
    List<Product> filter(String userType, String application, String mountingLocation, String accessories, int modelYear, double airflow, double maxPower, double soundAtMaxSpeed, double fanSweepDiameter, double height);

}
