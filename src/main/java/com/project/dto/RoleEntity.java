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

    @Column(name = "role_description", unique = false, nullable = true, length = 100)
    private String description;

    @Column(name = "role_owner", unique = false, nullable = true, length = 100)
    private String roleOwnerID;

    @Column(name = "role_system", unique = false, nullable = true, length = 100)
    private String system;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<UserEntity> users;

    @JoinColumn(name = "risk_role_id", unique = false, nullable = true)
    private Long risk_role_id;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleOwnerID() {
        return roleOwnerID;
    }

    public void setRoleOwnerID(String roleOwnerID) {
        this.roleOwnerID = roleOwnerID;
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

    public Long getRisk() {
        return risk_role_id;
    }

    public void setRisk(Long risk_role_id) {
        this.risk_role_id = risk_role_id;
    }

}





