package com.project.verification.ClientShowEmpData;

import com.google.gson.annotations.SerializedName;

public class EmpDataa {

    @SerializedName("id")
    private String mId;
    @SerializedName("employee_name")
    private String mEmployeeName;
    @SerializedName("employee_age")
    private String mEmployeeAge;
    @SerializedName("employee_salary")
    private String mEmployeeSalary;
    @SerializedName("profile_image")
    private String mProfileImage;

    public EmpDataa(String mId, String mEmployeeName, String mEmployeeAge, String mEmployeeSalary, String mProfileImage) {
        this.mId = mId;
        this.mEmployeeName = mEmployeeName;
        this.mEmployeeAge = mEmployeeAge;
        this.mEmployeeSalary = mEmployeeSalary;
        this.mProfileImage = mProfileImage;
    }

    public String getEmployeeAge() {
        return mEmployeeAge;
    }

    public void setEmployeeAge(String employeeAge) {
        mEmployeeAge = employeeAge;
    }

    public String getEmployeeName() {
        return mEmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        mEmployeeName = employeeName;
    }

    public String getEmployeeSalary() {
        return mEmployeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        mEmployeeSalary = employeeSalary;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {
        mProfileImage = profileImage;
    }
}
