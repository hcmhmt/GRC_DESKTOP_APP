package com.project.service;

import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;

import com.project.dto.RoleEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class UserRoleService {

    private final Session session;

    public UserRoleService() {
        session = TestHibernate.getSession();
    }

    public List<RoleEntity> getAll() {
        Query query = session.createQuery("FROM RoleEntity ");
        List<RoleEntity> roles = (List<RoleEntity>) query.list();
        return roles;
    }

    public RoleEntity save(RoleEntity role) {
        if (isRoleExistByroleName(role.getRoleName())) {
            return null;
        } else {
            Long id = (Long) session.save(role);
            role.setRoleId(id);
            return role;
        }
    }

    public boolean isRoleExistByroleName(String roleName) {
        int isExist;
        Query query = session.createQuery("FROM RoleEntity WHERE roleName =: roleName")
                .setParameter("role_name", roleName);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<RoleEntity> getOneRole(String roleName) {
        Query query = session.createQuery("FROM RoleEntity WHERE roleName =: RoleName");
        query.setParameter("role_name", roleName);
        return query.list();
    }


}



