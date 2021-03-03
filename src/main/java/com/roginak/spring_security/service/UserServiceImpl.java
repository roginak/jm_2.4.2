package com.roginak.spring_security.service;

import com.roginak.spring_security.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.roginak.spring_security.entities.Role;
import com.roginak.spring_security.entities.User;
import java.util.HashSet;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDao.getUserByLogin(login);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        setUserRoles(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        setUserRoles(user);
        userDao.editUser(user);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Transactional
    public void setUserRoles(User user) {
        if(user.getRoles() == null) {
            user.setRoles(new HashSet<Role>());
        }
       user.getListRoles().forEach(x->user.getRoles().add(userDao.getRoleByRoleName(x)));
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}
