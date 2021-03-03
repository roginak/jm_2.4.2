package com.roginak.spring_security.service;
import com.roginak.spring_security.entities.Role;
import com.roginak.spring_security.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<Role> getAllRoles();

    public void saveUser(User user);

    public void editUser(User user);

    public User getUser(int id);

    public User getUserByLogin(String login);

    public void deleteUser(int id);
}
