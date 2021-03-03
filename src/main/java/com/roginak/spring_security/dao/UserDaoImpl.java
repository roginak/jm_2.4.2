package com.roginak.spring_security.dao;

import org.springframework.stereotype.Repository;
import com.roginak.spring_security.entities.Role;
import com.roginak.spring_security.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.createQuery("delete from User u where u.id = :id")
                .setParameter("id", id).executeUpdate();
    }


    @Override
    public User getUserByLogin(String login) {
        return entityManager.createQuery("from User where login = :login", User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public Role getRoleByRoleName(String name) {
        return entityManager.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name).getSingleResult();
    }

}
