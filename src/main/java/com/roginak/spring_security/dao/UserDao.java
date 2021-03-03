package com.roginak.spring_security.dao;

import com.roginak.spring_security.entities.Role;
import com.roginak.spring_security.entities.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    List<Role> getAllRoles();

    public void saveUser(User user);

    public void editUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public User getUserByLogin(String username);

    public Role getRoleByRoleName(String roleName);
}
