package com.project.service;

import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;

import com.project.dto.RiskEntity;
import com.project.dto.RoleEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class RoleRiskService {

    private final Session session;

    public RoleRiskService() {
        session = TestHibernate.getSession();
    }

    public List<RiskEntity> getAll() {
        Query query = session.createQuery("FROM RiskEntity ");
        List<RiskEntity> risks = (List<RiskEntity>) query.list();
        return risks;
    }

    public RiskEntity save(RiskEntity risk) {
        if (isRiskexistByriskname(risk.getRiskName())) {
            return null;
        } else {
            Long id = (Long) session.save(risk);
            risk.setRiskId(id);
            return risk;
        }
    }

    public boolean isRiskexistByriskname(String riskName) {
        int isExist;
        Query query = session.createQuery("FROM RiskEntity WHERE riskName =: riskName")
                .setParameter("risk_name", riskName);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<RiskEntity> getOneRole(String riskName) {
        Query query = session.createQuery("FROM RiskEntity WHERE riskName =: riskName");
        query.setParameter("risk_name", riskName);
        return query.list();
    }


}


