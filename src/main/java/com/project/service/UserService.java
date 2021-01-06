package com.project.service;


import com.project.dto.RoleEntity;
import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserService {

    private final Session session;

    public UserService() {
        session = TestHibernate.getSession();
    }

    public List<UserEntity> getAll() {
        Query query = session.createQuery("FROM UserEntity ORDER BY userId ASC");
        List<UserEntity> users = (List<UserEntity>) query.list();
        return users;
    }

    public UserEntity save(UserEntity user) {
        if (isUserExistByUsername(user.getUsername())) {
            return null;
        } else {
            Long id = (Long) session.save(user);
            user.setUserId(id);
            return user;
        }
    }

    public boolean isUserExistByUsername(String username) {
        int isExist;
        Query query = session.createQuery("FROM UserEntity WHERE username =: username")
                .setParameter("username", username);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<UserEntity> getOneUser(String username) {
        Query query = session.createQuery("FROM UserEntity WHERE username =: username");
        query.setParameter("username", username);
        return query.list();
    }

    public List<UserEntity> getAllUsersByManager(String manager) {
        Query query = session.createQuery("FROM UserEntity WHERE managerID =: manager")
                .setParameter("manager", manager);
        return query.list();
    }


    public List<UserEntity> getAllUsersByUserType(String userType) {
        Query query = session.createQuery("FROM UserEntity WHERE userType =: userType")
                .setParameter("userType", userType);
        return query.list();
    }

    public List<UserEntity> searchUsers(String userName) {
        Query query = session.createQuery("FROM UserEntity WHERE username LIKE concat('%',:userName,'%') ORDER BY userId ASC")
                .setParameter("userName", userName);

        if (query.list().size() >= 1)
            return (List<UserEntity>) query.list();
        return null;
    }

    public UserEntity canLogin(UserEntity userEntity) {

        Query query = session.createQuery("FROM UserEntity u WHERE u.username =: username AND u.userPassword =: password")
                .setParameter("username", userEntity.getUsername())
                .setParameter("password", userEntity.getUserPassword());

        boolean isExist = (query.list().size() >= 1);
        if (isExist) {
            //List list = query.list();
            //Object[] manager = (Object[]) list.get(0);
            return (UserEntity) query.list().get(0);
        } else
            return null;

    }

    public boolean deleteById(Long userId) {
        session.createQuery("DELETE UserEntity where userId =: userId")
                .setParameter("userId", userId)
                .executeUpdate();

        session.createNativeQuery("DELETE FROM user_role where user_id = " + userId)
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
