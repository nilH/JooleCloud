package com.itlize.Joole.repository;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectProductRepository projectProductRepository;
    @Test
    public void createProduct(){
        Product product=new Product();
        product.setProductName("productName");

        Product product1=productRepository.save(product);
        assert(product1.getProductId()!=null);
    }

    @Test
    public void searchByType(){
        String type="fans";
        Product product=new Product();
        product.setProductName("HVAC8h8811");
        product.setType(type);
        productRepository.save(product);
        List<Product> productList =productRepository.findByTypeIgnoreCaseContaining("fan");
        assert(productList.size()>0);
    }

    @Test
    public void filterBySpecs(){
        Product product=new Product();
        product.setProductName("HEV33811");
        product.setType("fan");
        product.setUserType("usertype");
        product.setApplication("application");
        product.setMountingLocation("mountinglocation");
        product.setAccessories("accessories");
        product.setModelYear(2022);
        product.setAirflow(2);
        product.setMaxPower(2);
        product.setSoundAtMaxSpeed(2);
        product.setFanSweepDiameter(2);
        product.setHeight(2);
        productRepository.save(product);
        List<Product> productList=productRepository.findByUserTypeAndApplicationAndMountingLocationAndAccessoriesAndModelYearAndAirflowAndMaxPowerAndSoundAtMaxSpeedAndFanSweepDiameterAndHeight("usertype","application","mountinglocation","accessories",2022,2,2,2,2,2);
        assert(productList.size()>0);
    }

    @Test
    public void deleteProduct(){
        String type="fans";
        Product product=new Product();
        product.setProductName("HVAC88821");
        product.setType(type);
        productRepository.save(product);
        productRepository.delete(product);
        List<Product> productList=productRepository.findByTypeIgnoreCaseContaining("fan");
        assert(productList.size()==0);
    }
    @Test
    public void findById(){
        Product product=new Product();
        product.setProductName("productName");
        Product product1=productRepository.save(product);
        assert (productRepository.findById(product1.getProductId()).orElse(null)!=null);
    }

    @Test
    public void findByName(){
        Product product=new Product();
        product.setProductName("productName");
        product.setType("type");
        Product product1=productRepository.save(product);
        assert (productRepository.findByProductName(product1.getProductName()).size()>0);
    }

}
