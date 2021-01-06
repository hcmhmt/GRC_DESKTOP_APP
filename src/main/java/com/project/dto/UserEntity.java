package com.project.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "surname", unique = false, nullable = true, length = 100)
    private String userSurname;

    @Column(name = "first_name", unique = false, nullable = true, length = 100)
    private String userFirstName;

    @Column(name = "email", unique = false, nullable = true, length = 20)
    private String userEmail;

    @Column(name = "password", unique = false, nullable = true, length = 20)
    private String userPassword;

    @Column(name = "user_group", unique = false, nullable = true, length = 20)
    private String userGroup;

    @Column(name = "user_type", unique = false, nullable = true, length = 20)
    private String userType;

    @Column(name = "user_manager", unique = false, nullable = true, length = 20)
    private String managerID;

    @Column(name = "user_is_active", nullable = true)
    private String userIsActive;

    @ManyToMany(mappedBy = "users")
    private List<RoleEntity> roles;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(String userIsActive) {
        this.userIsActive = userIsActive;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
