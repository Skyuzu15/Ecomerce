package com.meuprojeto.ecomerce.service;

import com.meuprojeto.ecomerce.model.User;
import com.meuprojeto.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsuarios() {
        return userRepository.findAll();
    }

    public Optional<User> getAllUsuarios(Long id) {
        return userRepository.findById(id);
    }

    public User saveUsuario(User user) {
        return userRepository.save(user);
    }

    public void deleteUsusario(Long id) {
        userRepository.deleteById(id);
    }
}
