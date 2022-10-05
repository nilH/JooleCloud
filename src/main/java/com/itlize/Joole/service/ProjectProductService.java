package com.itlize.Joole.service;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;

import java.util.List;

public interface ProjectProductService {
    List<Project> getProjectFromProduct(Product product);
    int addProductToProject(int productId, int projectId);
    int deleteProductFromProject(int productId, int projectId);
    List<Product> getProductFromProject(int projectId);
}
