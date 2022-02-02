package com.example.coe.complaints;

import java.io.Serializable;

public class Complaint implements Serializable {

    private String issueName;
    private String raisedOnDate;
    private String status;
    private String issueDetails;

    public Complaint(String issueName, String issueDetails, String raisedOnDate, String status) {
        this.issueName = issueName;
        this.raisedOnDate = raisedOnDate;
        this.status = status;
        this.issueDetails = issueDetails;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getRaisedOnDate() {
        return raisedOnDate;
    }

    public void setRaisedOnDate(String raisedOnDate) {
        this.raisedOnDate = raisedOnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "issueName='" + issueName + '\'' +
                ", raisedOnDate='" + raisedOnDate + '\'' +
                ", status='" + status + '\'' +
                ", issueDetails='" + issueDetails + '\'' +
                '}';
    }
}
