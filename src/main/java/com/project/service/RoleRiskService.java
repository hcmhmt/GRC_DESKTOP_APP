package com.project.service;

import com.project.dto.RiskEntity;
import com.project.gui.TestHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class RoleRiskService {

    private final Session session;

    public RoleRiskService() {
        session = TestHibernate.getSession();
    }

    public List<RiskEntity> getAll() {
        Query query = session.createQuery("FROM RiskEntity ORDER BY riskId ASC");
        List<RiskEntity> risks = (List<RiskEntity>) query.list();
        return risks;
    }

    public RiskEntity save(RiskEntity risk) {
        if (isRiskExistByRiskname(risk.getRiskName())) {
            return null;
        } else {
            Long id = (Long) session.save(risk);
            risk.setRiskId(id);
            return risk;
        }
    }

    public boolean isRiskExistByRiskname(String riskName) {
        int isExist;
        Query query = session.createQuery("FROM RiskEntity WHERE riskName =: riskName")
                .setParameter("riskName", riskName);
        isExist = query.list().size();
        return isExist > 0;
    }

    public List<RiskEntity> getSearchedRisk(String riskName) {
        Query query = session.createQuery("FROM RiskEntity WHERE riskName LIKE concat('%',:riskName,'%') ORDER BY riskId ASC");
        query.setParameter("riskName", riskName);
        return query.list();
    }

    public boolean deleteById(Long riskId, Long roleId) {
        session.createQuery("DELETE RiskEntity where riskId =: riskId")
                .setParameter("riskId", riskId)
                .executeUpdate();

        session.createQuery("DELETE RoleEntity where roleId =: roleId")
                .setParameter("roleId", roleId)
                .executeUpdate();

        return true;
    }

    public boolean delete(Long riskId, Long roleId) {
        try {
            session.beginTransaction();
            flushAndClear();
            deleteById(riskId,roleId);
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


