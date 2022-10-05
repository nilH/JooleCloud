package com.itlize.Joole.service.serviceImpl;

import com.itlize.Joole.entity.User;
import com.itlize.Joole.repository.UserRepository;
import com.itlize.Joole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.findByName(user.getName()).isPresent()){
            return user;
        }
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByName(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean delete(User user) {
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    //update password. leave for further requirements
    @Override
    public boolean update(String userName, User user) {
        try{
            User toUpdate=userRepository.findByName(userName).orElse(null);
            toUpdate.setTimeUpdated(LocalDateTime.now());
            toUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }
}
