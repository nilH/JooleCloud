package com.itlize.Joole.Service;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import com.itlize.Joole.repository.ProductRepository;
import com.itlize.Joole.repository.ProjectProductRepository;
import com.itlize.Joole.service.ProductService;
import com.itlize.Joole.service.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductManageTest {

    @Autowired
    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Mock
    ProductRepository productRepository;
    @Mock
    ProjectProductRepository projectProductRepository;

    @Test
    public void addProductTest(){
        Product product1=new Product();
        product1.setProductId(1);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product1);
        Product product=new Product();
        product.setProductName("productName");
        product.setType("type");
        int id=productServiceImpl.addProduct(product);
        assert(id==1);
    }
    @Test
    public void productSearchByTypeTest(){
        List<Product> productList=new ArrayList<>();
        productList.add(new Product());
        Mockito.when(productRepository.findByTypeIgnoreCaseContaining("fan")).thenReturn(productList);
        List<Product> productList1;
        productList1=productServiceImpl.searchByType("fan");
        assert(productList1.size()>0);
    }
    @Test
    public void productFilterTest(){
        List<Product> productList=new ArrayList<>();
        productList.add(new Product());
        Mockito.when(productRepository.findByUserTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearAndAirflowAndMaxPowerAndSoundAtMaxSpeedAndFanSweepDiameterAndHeight("usertype","application","mountinglocation","accessories",2022,2,2,2,2,2)).thenReturn(productList);
        List<Product> productList1;
        productList1=productServiceImpl.filter("usertype","application","mountinglocation","accessories",2022,2,2,2,2,2);
        assert(productList1.size()>0);
    }

    @Test
    public void findById(){
        Product product=new Product();
        product.setProductName("returnProductName");
        product.setProductId(11);
        Mockito.when(productRepository.findById(11)).thenReturn(Optional.of(product));
        Product product1=productServiceImpl.findById(11);
        assert (product1.getProductName().equals("returnProductName"));
    }

    @Test
    public void getProjectFromProduct(){
        Product product=new Product();
        int productId = product.getProductId();
        List<ProjectProduct> projectProductList=new ArrayList<>();
        ProjectProduct projectProduct=new ProjectProduct();
        projectProduct.setProject(new Project());
        projectProductList.add(projectProduct);
        Mockito.when(projectProductRepository.findByProduct(product)).thenReturn(projectProductList);
        List<Project> projectList=productServiceImpl.getProjectFromProduct(productId);
        assert (projectList.size()>0);
    }
}
