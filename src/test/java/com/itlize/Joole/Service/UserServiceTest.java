package com.itlize.Joole.Service;

import com.itlize.Joole.entity.User;
import com.itlize.Joole.repository.UserRepository;
import com.itlize.Joole.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    List<User> userList;

    @BeforeEach
    public void setUp() {
        userList = new ArrayList<>();
        user = new User();
        user.setId(1);
        user.setName("Oliver");
        user.setPassword("123456");
        userList.add(user);
    }

    @AfterEach
    public void tearDown() {
        user = null;
        userList = null;
    }

    @Test
    public void saveUserTest() {
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("xxxx");
        User tmpUser = userService.saveUser(user);
        int id = tmpUser.getId();
        assertEquals(id, 1);
    }

    @Test
    public void findByUsernameTest(){
        when(userRepository.findByName("Oliver")).thenReturn(Optional.ofNullable(user));
        assertThat(userService.findByUsername("Oliver")).isEqualTo(user);
    }

    @Test
    public void findAllUsersTest(){
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1 =userService.findAllUsers();
        assertEquals(userList1,userList);
    }

    @Test
    public void updateUser(){
        User user=new User();
        user.setName("username");
        User user1=new User();
        user1.setName("username");
        Mockito.when(userRepository.findByName("username")).thenReturn(Optional.of(user1));
        userService.update("username",user);
        assert(user1.getTimeUpdated()!=null);
    }



}