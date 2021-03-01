package com.project.verification;

public class Emp_Model
{
    public  String inEdName,inEdAge,status,spinner;

    Emp_Model()
    {

    }

    public Emp_Model(String inEdName, String inEdAge, String status, String spinner) {
        this.inEdName = inEdName;
        this.inEdAge = inEdAge;
        this.status = status;
        this.spinner = spinner;
    }

    public String getInEdName() {
        return inEdName;
    }

    public void setInEdName(String inEdName) {
        this.inEdName = inEdName;
    }

    public String getInEdAge() {
        return inEdAge;
    }

    public void setInEdAge(String inEdAge) {
        this.inEdAge = inEdAge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }
}
