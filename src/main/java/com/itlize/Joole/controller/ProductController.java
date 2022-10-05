package com.itlize.Joole.controller;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.User;
import com.itlize.Joole.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;
//
//    public ResponseEntity<?> register(@RequestBody User user)
//    {
//        if(userService.findByUsername(user.getName())!=null){
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        User user1=new User();
//        user1.setPassword(user.getPassword());
//        user1.setName(user.getName());
//        user1.setTimeCreated(LocalDateTime.now());
//        user1.setRole("User");
//        return new ResponseEntity<>(userService.saveUser(user1), HttpStatus.CREATED);
//    }

    @PostMapping(value = "/add_product")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
//        Product product1 = new Product();
//        product1.setProductName(product.getProductName());
//        product1.setType(product.getType());
//        product1.setProductInfo(product.getProductInfo());
//        product1.setCertificate(product.getCertificate());

        Integer result = productService.addProduct(product);
        if (result == null) {
            return new ResponseEntity<>("{\"unknown mistake!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping(value = "/delete_product")
    public ResponseEntity<?> deleteProduct(@RequestParam("product_id") int productId) {
        Product product1 = productService.findById(productId);
        if (product1 == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
            productService.deleteProduct(product1);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search_by_type")
    public ResponseEntity<?>  searchByType(@RequestParam("type") String type){
        List<Product> result=productService.searchByType(type);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/product_filter")
    public ResponseEntity<?> Filter(@RequestParam("user_type") String userType, @RequestParam("application")  String application, @RequestParam("mounting_location") String mountingLocation,
                                @RequestParam("accessories") String accessories, @RequestParam("modelYear") String modelYear, @RequestParam("airflow") double airflow,
                                @RequestParam("max_power") double maxPower, @RequestParam("sound_at_max_speed") double soundAtMaxSpeed,
                                @RequestParam("fan_sweep_diameter") double fanSweepDiameter, @RequestParam("height") double height){
        List<Product> result = productService.filter(userType,  application,  mountingLocation,  accessories,  Integer.parseInt(modelYear),  airflow,  maxPower,  soundAtMaxSpeed,  fanSweepDiameter,  height);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/update_product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product,@RequestParam("product_id") Integer productId){
        Product product1 = productService.findById(productId);
        if (product1 == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(product, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findall_product")
    public ResponseEntity<?> findAllProduct() {
        List<Product> result = productService.findAllProduct();
        if (result == null) {
            return new ResponseEntity<>("{\"Empty!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findby_productid")
    public ResponseEntity<?> findById(@RequestParam("product_id") Integer productId){
        Product result = productService.findById(productId);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findby_productname")
    public ResponseEntity<?> findByName(@RequestParam("product_name") String productName){
        List<Product> result = productService.findByName(productName);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/get_project_from_product")
    public ResponseEntity<?> getProjectFromProduct(@RequestParam("product_id") int productId){
        List<Project> result = productService.getProjectFromProduct(productId);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
