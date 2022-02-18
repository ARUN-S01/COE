package com.example.coe.examactivity;

public class Exam {
    private String examName;
    private String fee;
    private String examDate;
    private String lastDate;
    private String lastDateWithFine;
    private String eligibility;
    private boolean registered;

    public Exam() {
    }

    public Exam(String examName, String fee, String examDate, String lastDate, String lastDateWithFine, String eligibility, boolean registered) {
        this.examName = examName;
        this.fee = fee;
        this.examDate = examDate;
        this.lastDate = lastDate;
        this.lastDateWithFine = lastDateWithFine;
        this.eligibility = eligibility;
        this.registered = registered;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastDateWithFine() {
        return lastDateWithFine;
    }

    public void setLastDateWithFine(String lastDateWithFine) {
        this.lastDateWithFine = lastDateWithFine;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
