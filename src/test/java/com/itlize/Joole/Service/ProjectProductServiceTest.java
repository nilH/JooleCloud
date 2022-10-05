package com.itlize.Joole.Service;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import com.itlize.Joole.repository.ProductRepository;
import com.itlize.Joole.repository.ProjectProductRepository;
import com.itlize.Joole.repository.ProjectRepository;
import com.itlize.Joole.service.serviceImpl.ProjectProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    ProjectRepository projectRep;
    @Mock
    ProjectProductRepository projectProductRepository;

    @InjectMocks
    ProjectProductServiceImpl projectProductService;

    private Product product;

    private Project project;


    private ProjectProduct pp;

    private List<Product> listOfProduct;
    private List<Project> listOfProject;

    private List<ProjectProduct> listOfPP;

    @BeforeEach
    public void setUp() {
        Set<ProjectProduct> ppSet = new HashSet<ProjectProduct>();
        product = new Product();
        product.setProductName("A");
        product.setProductId(1);

        project = new Project();
        project.setProjectName("B");
        project.setId(2);
        pp = new ProjectProduct();
        ppSet.add(pp);
        pp.setProduct(product);
        pp.setProject(project);
        project.setProjectProduct(ppSet);
        product.setProjectProductSet(ppSet);


        listOfProduct = new ArrayList<Product>();
        listOfProduct.add(product);
        listOfProject = new ArrayList<Project>();
        listOfProject.add(project);
        listOfPP = new ArrayList<ProjectProduct>();
        listOfPP.add(pp);
    }

    @Test
    public void getProjectFromProduct(){
        Product product=new Product();
        List<ProjectProduct> projectProductList=new ArrayList<>();
        ProjectProduct projectProduct=new ProjectProduct();
        projectProduct.setProject(new Project());
        projectProductList.add(projectProduct);
        Mockito.when(projectProductRepository.findByProduct(product)).thenReturn(projectProductList);
        List<Project> projectList=projectProductService.getProjectFromProduct(product);
        assert (projectList.size()>0);
    }
    @Test
    public void addProductToProjectTest()
    {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));
        when(projectRep.findById(any())).thenReturn(Optional.ofNullable(project));
        when(projectProductRepository.save(any())).thenReturn(pp);

        assert(projectProductService.addProductToProject(1,2)==1);
    }


}
