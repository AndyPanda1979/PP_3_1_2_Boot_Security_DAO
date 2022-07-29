package ru.kata.spring.boot_security.demo.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User findUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }


    @Override
    @Transactional
    public User findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.firstName = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User proxyUser = entityManager.find(User.class, id);
        entityManager.remove(proxyUser);
    }
}
