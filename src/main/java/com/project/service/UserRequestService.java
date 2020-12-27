package com.project.service;

import com.project.dto.RequestEntity;
import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;

import com.project.dto.RoleEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class UserRequestService {

    private final Session session;

    public UserRequestService() {
        session = TestHibernate.getSession();
    }

    public List<RequestEntity> getAll() {
        Query query = session.createQuery("FROM RequestEntity ");
        List<RequestEntity> requests = (List<RequestEntity>) query.list();
        return requests;
    }

    public RequestEntity save(RequestEntity request) {
        if (isRequestExistByrequestID(request.getRequestId())) {
            return null;
        } else {
            Long id = (Long) session.save(request);
            request.setRequestId(id);
            return request;
        }
    }

    public boolean isRequestExistByrequestID(Long requestID) {
        int isExist;
        Query query = session.createQuery("FROM RequestEntity WHERE requestId =: requestID")
                .setParameter("request_id", requestID);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<RoleEntity> getOneRole(Long requestID) {
        Query query = session.createQuery("FROM RequestEntity WHERE requestId =: requestID");
        query.setParameter("request_id", requestID);
        return query.list();
    }


}



