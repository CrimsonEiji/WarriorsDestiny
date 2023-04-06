package com.example.WarriorsTest.services;

import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveAndFlush(UserEntity user) {
        userRepository.saveAndFlush(user);
    }
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<UserEntity> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> findByUsernameNot(String username){
        return userRepository.findByUsernameNot(username);
    }
}

