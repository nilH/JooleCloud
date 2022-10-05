package com.itlize.Joole.Service;

import com.itlize.Joole.entity.User;
import com.itlize.Joole.repository.UserRepository;
import com.itlize.Joole.service.MyUserDetailService;
import com.itlize.Joole.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private MyUserDetailService userDetailService;
    private User user;


    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Oliver");
        user.setPassword("123456");
    }

    //test not implemented
    @Test
    public void loadUserByUsernameTest()
    {
        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(user));
        assertThat(userDetailService.loadUserByUsername("Oliver")).isEqualTo(user);
    }





}