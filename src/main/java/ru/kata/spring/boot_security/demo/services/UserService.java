package ru.kata.spring.boot_security.demo.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public User findByEmail(String email);
    public List<User> findAll();
    public void save(User newUser, Set<String> roles);
    public void update(int id, User updatedUser, Set<String> listOfRoles);
    public void delete(int id);
}
