package com.project.service;



import com.project.dto.RiskEntity;
import com.project.dto.UserEntity;
import com.project.gui.TestHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RiskService {

    private final Session session;

    public RiskService() {
        session = TestHibernate.getSession();
    }

    public List<RiskEntity> getAll() {
        Query query = session.createQuery("FROM RiskEntity ORDER BY riskId ");
        List<RiskEntity> risks = (List<RiskEntity>) query.list();
        return risks;
    }

    public RiskEntity save(RiskEntity risk) {
        if (isRiskExistByRiskName(risk.getRiskName())) {
            return null;
        } else {
            Long id = (Long) session.save(risk);
            risk.setRiskId(id);
            return risk;
        }
    }

    public boolean isRiskExistByRiskName(String riskName) {
        int isExist;
        Query query = session.createQuery("FROM RiskEntity WHERE riskName =: riskName")
                .setParameter("riskName", riskName);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<UserEntity> getOneRisk(String riskName) {
        Query query = session.createQuery("FROM RiskEntity WHERE riskName =: riskName");
        query.setParameter("riskName", riskName);
        return query.list();
    }

}
