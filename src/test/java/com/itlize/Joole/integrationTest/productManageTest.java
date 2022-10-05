package com.itlize.Joole.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class productManageTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    public void addProduct() throws Exception{
        Product product=new Product();
        product.setProductName("productnamefortest");
        product.setBrand("brand");
        product.setType("searchType");
        product.setCertificate("certificate");
        product.setModelYear(2012);
        product.setAirflow(1);
        product.setApplication("application");
        product.setHeight(1);
        product.setMaxPower(1);
        product.setMountingLocation("location");
        product.setManufacturer("manufacturer");
        product.setUserType("usertype");
        product.setFanSweepDiameter(1);
        product.setCertificate("certificate");
        product.setAccessories("accessories");
        product.setType("nameforsearch");
        ObjectMapper objectMapper= JsonMapper.builder().addModule(new JavaTimeModule()).build();
        mockMvc.perform(post("/add_product").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product)));
    }


}
