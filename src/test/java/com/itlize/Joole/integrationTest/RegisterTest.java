package com.itlize.Joole.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlize.Joole.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void register() throws Exception{

        User user=new User();
        user.setName("username1");
        user.setPassword("password");
        user.setId(1);
        mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(user
        )));

    }



}
