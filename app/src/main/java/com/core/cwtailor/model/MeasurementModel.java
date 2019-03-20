package com.core.cwtailor.model;

public class MeasurementModel {
    private String id;
    private String email_id;
    private String status;

    public String getId() {
        return id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
