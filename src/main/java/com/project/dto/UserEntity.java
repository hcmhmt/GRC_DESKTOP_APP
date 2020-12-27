package com.project.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "\"user\"")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "surname", unique = false, nullable = false, length = 100)
    private String userSurname;

    @Column(name = "first_name", unique = false, nullable = false, length = 100)
    private String userFirstName;

    @Column(name = "email", unique = false, nullable = false, length = 20)
    private String userEmail;

    @Column(name = "password", unique = false, nullable = false, length = 20)
    private String userPassword;

    @Column(name = "user_group", unique = false, nullable = false, length = 20)
    private String userGroup;

    @Column(name = "user_type", unique = false, nullable = false, length = 20)
    private TypeEnum userType;

    @Column(name = "user_manager", unique = false, nullable = false, length = 20)
    private String managerID;

    @Column(name = "user_is_active", nullable = false)
    private StatusEnum userIsActive;

    @JoinColumn(name = "role_user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private RoleEntity roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public TypeEnum getUserType() {
        return userType;
    }

    public void setUserType(TypeEnum userType) {
        this.userType = userType;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public StatusEnum getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(StatusEnum userIsActive) {
        this.userIsActive = userIsActive;
    }

    public RoleEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleEntity roleId) {
        this.roleId = roleId;
    }
}
