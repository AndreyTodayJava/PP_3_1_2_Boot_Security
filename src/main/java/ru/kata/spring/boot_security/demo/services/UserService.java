package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Set;


@Service
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User newUser, Set<String> roles) {
        newUser.addRoles(roles);
        userRepository.save(newUser);
    }

    @Transactional
    public void update(int id, User updatedUser, Set<String> listOfRoles) {
        User user = userRepository.getById(id);
        user.setEmail(updatedUser.getEmail());
        user.setAge(updatedUser.getAge());
        user.setRoles(listOfRoles);
        user.setLastName(updatedUser.getLastName());
        user.setUsername(updatedUser.getUsername());
        if (!StringUtils.isEmpty(updatedUser.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
