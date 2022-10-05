package com.itlize.Joole.repository;

import com.itlize.Joole.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void createUser(){
        User user=new User();
        user.setName("user");
        user.setPassword("password");
        user.setRole("customer");
        userRepository.save(user);
    }

    @Test
    public void findUserByName(){
        createUser();
        User user=userRepository.findByName("user").orElse(null);
        assert(user!=null);
    }

    @Test
    public void findAllUsers(){
        createUser();
        List<User> userList=userRepository.findAll();
        assert (userList.size()>0);
    }

    @Test
    public void deleteUser(){
        createUser();
        User user=userRepository.findByName("user").orElse(null);
        userRepository.delete(user);
        User user1=userRepository.findByName("user").orElse(null);
        assert (user1==null);
    }

    @Test
    public void updateUser(){
        createUser();
        User user=userRepository.findByName("user").orElse(null);
        LocalDateTime localDateTime=LocalDateTime.now();
        user.setTimeUpdated(localDateTime);
        User user1=userRepository.findById(user.getId()).orElse(null);
        assert (user1.getTimeUpdated().equals(localDateTime));
    }

    @Test
    public void deleteAllUser(){
        userRepository.deleteAll();
        User user=userRepository.findByName("user").orElse(null);
        assert(user==null);
    }

}
