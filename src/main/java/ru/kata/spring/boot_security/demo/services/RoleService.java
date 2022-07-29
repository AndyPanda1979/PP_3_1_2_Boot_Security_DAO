package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.role.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;
import java.util.List;

@Service
public class RoleService {
    final RoleDao roleDao;


    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role getRoleByName(String roleName) {
        return roleDao.findRoleByName(roleName);
    }
    public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }

}
