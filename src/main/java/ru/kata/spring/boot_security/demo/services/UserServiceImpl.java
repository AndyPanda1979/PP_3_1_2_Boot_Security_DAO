package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.role.RoleDao;
import ru.kata.spring.boot_security.demo.dao.user.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserDao userDao;
    final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAllUsers();
    }
    @Override
    public User getUserById(Long id) {
        return userDao.findUserById(id);
    }
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user.getId());
    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    @Override
    public List<String> getUserRolesAsStringList(User user) {
        List<Role> userRoles = user.getRoles();
        List<String> userRolesAsStringList = new ArrayList<>();
        for (Role role: userRoles) {
            userRolesAsStringList.add(role.getAuthority());
        }
        return userRolesAsStringList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.tryGetUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("not found");
    }
}
