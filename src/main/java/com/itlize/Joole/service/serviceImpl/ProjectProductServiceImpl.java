package com.itlize.Joole.service.serviceImpl;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import com.itlize.Joole.repository.ProductRepository;
import com.itlize.Joole.repository.ProjectProductRepository;
import com.itlize.Joole.repository.ProjectRepository;
import com.itlize.Joole.service.ProjectProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProjectProductServiceImpl implements ProjectProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProjectRepository projectRep;
    @Autowired
    ProjectProductRepository projectProductRepository;
    @Override
    public List<Project> getProjectFromProduct(Product product) {
        List<ProjectProduct> projectProductList=projectProductRepository.findByProduct(product);
        List<Project> projectList=new ArrayList<>();
        for(ProjectProduct projectProduct:projectProductList){
            projectList.add(projectProduct.getProject());
        }
        return projectList;
    }
    @Override
    public int addProductToProject(int productId, int projectId)
    {

        Product product=productRepository.findById(productId).orElse(null);
        Project project = projectRep.findById(projectId).orElse(null);

        if(product == null || project ==null)
        {
            return 1;
        }

        ProjectProduct pp = new ProjectProduct();
        pp.setProduct(product);
        pp.setProject(project);
        if(project.getProjectProduct().contains(pp)){
            return 1;
        }
        try {
            projectProductRepository.save(pp);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return 0;
        }

        return 0;

    }
    @Override
    public int deleteProductFromProject(int productId, int projectId) {

        Project project = projectRep.findById(projectId).orElse(null);
        Product product=productRepository.findById(productId).orElse(null);

        if(product == null || project ==null)
        {
            return 0;
        }

        List<ProjectProduct> ppList = projectProductRepository.findByProject(project);

        Iterator<ProjectProduct> it = ppList.iterator();  // Traverse the set

        while (it.hasNext()) {               // Delete every related ProjectProducts
            ProjectProduct pp = it.next();
            if(pp.getProduct().getProductId() == productId)
            {
                simpleDeleteProjectProduct(pp);     // This is a method in this class
            }
        }

        return 1;
    }
    @Override
    public List<Product> getProductFromProject(int projectId) {

        List<Product> output = new ArrayList<Product>();

        Project project = projectRep.findById(projectId).orElse(null);

        if(project ==null)
        {
            return null;
        }

        List<ProjectProduct> ppList = projectProductRepository.findByProject(project);

        Iterator<ProjectProduct> it = ppList.iterator();  // Traverse the set

        while (it.hasNext()) {
            ProjectProduct pp = it.next();
            output.add(pp.getProduct());     // Add every product
        }

        return output;
    }
    public int simpleDeleteProject(Project project)   // To the facilitate unit testing
    {
        projectRep.delete(project);
        for(ProjectProduct projectProduct:project.getProjectProduct()){
            projectProductRepository.delete(projectProduct);
        }
        return 1;
    }

    public int simpleDeleteProjectProduct(ProjectProduct pp) // To the facilitate unit testing
    {
        projectProductRepository.delete(pp);
        return 1;
    }
}
