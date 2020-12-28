package com.project.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "\"risk\"")
public class RiskEntity implements Serializable {

    @Id
    @Column(name = "risk_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riskId;

    @Column(name = "risk_name", unique = true, nullable = false, length = 100)
    private String riskName;

    @Column(name = "risk_description", unique = false, nullable = true, length = 100)
    private String riskDescription;

    @Column(name = "risk_owner", unique = false, nullable = true, length = 100)
    private String riskOwnerID;

    @Column(name = "risk_level", unique = false, nullable = true, length = 100)
    private String risklevel;

    @OneToOne(mappedBy = "risk")
    private RoleEntity role;

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public String getRiskOwnerID() {
        return riskOwnerID;
    }

    public void setRiskOwnerID(String riskOwnerID) {
        this.riskOwnerID = riskOwnerID;
    }

    public String getRisklevel() {
        return risklevel;
    }

    public void setRisklevel(String risklevel) {
        this.risklevel = risklevel;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}





