package com.project.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "\"request\"")
public class RequestEntity implements Serializable {

    @Id
    @Column(name = "request_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(name = "request_user_id", unique = false, nullable = true, length = 100)
    private String requestUserId;

    @Column(name = "request_description", unique = false, nullable = true, length = 500)
    private String requestDescription;

    @Column(name = "request_create_date", unique = false, nullable = true, length = 500)
    private Date requestCreateDate;

    @Column(name = "request_system", unique = false, nullable = true, length = 20)
    private String requestSystem;

    @Column(name = "request_role", unique = false, nullable = true, length = 30)
    private String requestRole;

    @Column(name = "request_status", unique = false, nullable = true, length = 30)
    private String requestStatus;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(String requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public Date getRequestCreateDate() {
        return requestCreateDate;
    }

    public void setRequestCreateDate(Date requestCreateDate) {
        this.requestCreateDate = requestCreateDate;
    }

    public String getRequestSystem() {
        return requestSystem;
    }

    public void setRequestSystem(String requestSystem) {
        this.requestSystem = requestSystem;
    }

    public String getRequestRole() {
        return requestRole;
    }

    public void setRequestRole(String requestRole) {
        this.requestRole = requestRole;
    }
}

