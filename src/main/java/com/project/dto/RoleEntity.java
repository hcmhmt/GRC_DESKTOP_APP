package com.project.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "\"role\"")
public class RoleEntity implements Serializable {

    @Id
    @Column(name = "role_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name", unique = true, nullable = false, length = 100)
    private String roleName;

    @Column(name = "role_description", unique = false, nullable = false, length = 100)
    private String description;

    @Column(name = "role_owner", unique = true, nullable = false, length = 100)
    private String roleOwnerID;

    @Column(name = "role_risklevel", unique = true, nullable = false, length = 100)
    private String risklevel;

    @Column(name = "role_system", unique = true, nullable = false, length = 100)
    private String system;

    @JoinColumn(name = "role_user_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "risk_id", referencedColumnName = "risk_id")
    private RiskEntity risk;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getRoleOwnerID() {
        return roleOwnerID;
    }

    public void setRoleOwnerID(String roleOwnerID) {
        this.roleOwnerID = roleOwnerID;
    }

    public String getRisklevel() {
        return risklevel;
    }

    public void setRisklevel(String risklevel) {
        this.risklevel = risklevel;
    }

    public RiskEntity getRiskname() {
        return risk;
    }

    public void setRiskname(RiskEntity riskname) {
        this.risk = risk;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }


}




