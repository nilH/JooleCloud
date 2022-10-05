package com.itlize.Joole.integrationTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itlize.Joole.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductSearchTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void lineSearch() throws Exception{
        List<Product> productList;
        ObjectMapper objectMapper= JsonMapper.builder().addModule(new JavaTimeModule()).build();
        MvcResult mvcResult=mockMvc.perform(get("/line_search").param("product_name","name")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        productList=objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Product>>() {
        });
        System.out.println(productList.get(0).getProductId());
    }
}
