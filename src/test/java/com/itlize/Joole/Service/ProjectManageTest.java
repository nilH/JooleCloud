package com.itlize.Joole.Service;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import com.itlize.Joole.entity.User;
import com.itlize.Joole.repository.ProductRepository;
import com.itlize.Joole.repository.ProjectProductRepository;
import com.itlize.Joole.repository.ProjectRepository;
import com.itlize.Joole.service.MyUserDetailService;
import com.itlize.Joole.service.ProjectService;
import com.itlize.Joole.service.serviceImpl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectManageTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ProjectRepository projectRep;
    @Mock
    ProjectProductRepository ppRepository;

    @Autowired
    @InjectMocks
    private ProjectServiceImpl projectServiceImp;
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
    public void createProjectTest()
    {
        when(projectRep.save(any())).thenReturn(project);
        when(projectRep.findByProjectName(any())).thenReturn(new ArrayList<>());

        assertThat(projectServiceImp.createProject(project)).isEqualTo(2);
    }

    @Test
    public void findAllProjectTest()
    {
        when(projectRep.findAll()).thenReturn(listOfProject);
        assertThat(projectServiceImp.findAllProject()).isEqualTo(listOfProject);
    }


    @Test
    public void findByNameTest()
    {
        when(projectRep.findByProjectName(any())).thenReturn(listOfProject);
        assertThat(projectServiceImp.findByName("B")).isEqualTo(listOfProject);
    }
    /*@Test
    public void updateProjectTest()
    {
        when(projectRep.findById(any())).thenReturn(project1);
        assertThat(projectService.updateProject(project, 2)).isEqualTo(1);
    }*/

/*    @Test
    public void deleteProject()
    {
        when(projectServiceImpl.simpleDeleteProject(project)).thenReturn(1);
        assertThat(projectService.deleteProject(project)).isEqualTo(1);
    }*/

    @Test
    public void findById(){
        when(projectRep.findById(2)).thenReturn(Optional.of(project));
        Project project2=projectServiceImp.findById(2);
        assert (project2.getId()==2);
    }

}