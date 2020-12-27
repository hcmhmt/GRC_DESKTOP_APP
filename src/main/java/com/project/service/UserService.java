package com.project.service;



import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserService {

    private final Session session;

    public UserService() {
        session = TestHibernate.getSession();
    }

    public List<UserEntity> getAll() {
        Query query = session.createQuery("FROM UserEntity ");
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

    public UserEntity canLogin(UserEntity userEntity) {

        Query query = session.createQuery("FROM UserEntity u inner join u.roleId WHERE u.username =: username AND u.userPassword =: password")
                .setParameter("username", userEntity.getUsername())
                .setParameter("password", userEntity.getUserPassword());

        boolean isExist = (query.list().size() >= 1);
        if (isExist) {
            List list = query.list();
            Object[] manager = (Object[]) list.get(0);
            return (UserEntity) manager[0];
        } else
            return null;

    }
}
