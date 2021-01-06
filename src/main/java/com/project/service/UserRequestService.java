package com.project.service;

import com.project.dto.RequestEntity;
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
        Long id = (Long) session.save(request);
        request.setRequestId(id);
        return request;

    }
}






