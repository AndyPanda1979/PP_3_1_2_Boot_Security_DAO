package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.role.RoleDao;
import ru.kata.spring.boot_security.demo.dao.user.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl {

    final UserDao userDao;
    final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public List<User> getUsers() {
        return userDao.findAllUsers();
    }

    public User getUserById(Long id) {
        return userDao.findUserById(id);
    }
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    public void deleteUser(User user) {
        userDao.deleteUser(user.getId());
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public List<String> getUserRolesAsStringList(User user) {
        List<Role> userRoles = user.getRoles();
        List<String> userRolesAsStringList = new ArrayList<>();
        for (Role role: userRoles) {
            userRolesAsStringList.add(role.getAuthority());
        }
        return userRolesAsStringList;
    }
}
