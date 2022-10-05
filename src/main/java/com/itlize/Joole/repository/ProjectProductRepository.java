package com.itlize.Joole.repository;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectProductRepository extends JpaRepository<ProjectProduct,Integer> {

    List<ProjectProduct> findByProject (Project project);

    List<ProjectProduct> findByProduct(Product product);

    void deleteByProjectAndProduct(Project project, Product product);


}
