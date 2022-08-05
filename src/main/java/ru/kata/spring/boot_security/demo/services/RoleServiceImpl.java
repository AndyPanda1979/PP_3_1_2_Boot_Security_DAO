package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.role.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.findRoleByName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAllRoles();
    }
}
