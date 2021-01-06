package com.project.service;

import com.project.dto.RoleEntity;
import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class RoleService {

    private final Session session;

    public RoleService() {
        session = TestHibernate.getSession();
        session.beginTransaction();
        session.getTransaction().commit();
    }

    public List<RoleEntity> getAll() {
        Query query = session.createQuery("FROM RoleEntity ");
        List<RoleEntity> roles = (List<RoleEntity>) query.list();
        return roles;
    }

    public List<RoleEntity> getAllBySystem(String system) {
        Query query = session.createQuery("FROM RoleEntity WHERE system =: system")
                .setParameter("system", system);
        List<RoleEntity> roles = (List<RoleEntity>) query.list();
        return roles;
    }

    public RoleEntity save(RoleEntity role, UserEntity user) {
        if (isRoleExistByroleName(role.getRoleName())) {
            return null;
        } else {
            Long id = (Long) session.save(role);
            role.setRoleId(id);

            session.beginTransaction();
            session.createNativeQuery("INSERT INTO user_role values ("+ id +", "+ user.getUserId() +")")
                    .executeUpdate();
            session.getTransaction().commit();

            return role;
        }
    }

    public boolean isRoleExistByroleName(String roleName) {
        int isExist;
        Query query = session.createQuery("FROM RoleEntity WHERE roleName =: roleName")
                .setParameter("roleName", roleName);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<RoleEntity> getOneRole(String roleName) {
        Query query = session.createQuery("FROM RoleEntity WHERE roleName LIKE concat('%',:roleName,'%')")
                .setParameter("roleName", roleName);

        if (query.list().size() >= 1)
            return (List<RoleEntity>) query.list();
        return null;
    }

    public RoleEntity getOneRolebyId(Long id) {
        Query query = session.createQuery("FROM RoleEntity WHERE roleId =: id")
                .setParameter("id", id);

        if (query.list().size() >= 1)
            return (RoleEntity) query.list().get(0);
        return null;
    }

    public RoleEntity getOneRolebyRiskId(Long risk_role_id) {
        Query query = session.createQuery("FROM RoleEntity WHERE risk_role_id =: risk_role_id")
                .setParameter("risk_role_id", risk_role_id);

        if (query.list().size() >= 1)
            return (RoleEntity) query.list().get(0);
        return null;
    }

    public boolean deleteById(Long roleId) {
        session.createQuery("DELETE RoleEntity where roleId =: roleId")
                .setParameter("roleId", roleId)
                .executeUpdate();


        session.createNativeQuery("DELETE FROM user_role where role_id = " + roleId)
                .executeUpdate();

        return true;
    }

    public boolean delete(Long id) {
        try {
            session.beginTransaction();
            flushAndClear();
            deleteById(id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }

        return true;
    }

    public void flushAndClear() {
        session.flush();
        session.clear();
    }
}



