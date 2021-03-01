package com.project.verification.ClientShowEmpData;

import com.google.gson.annotations.SerializedName;
import com.project.verification.EmpPkg.EmpData;

import java.util.List;

public class EmpPojoo {

    @SerializedName("data")
    private List<EmpDataa> mData;
    @SerializedName("status")
    private String mStatus;

    public List<EmpDataa> getData() {
        return mData;
    }

    public EmpPojoo(List<EmpDataa> mData) {
        this.mData = mData;
    }

    public void setData(List<EmpDataa> data) {
        mData = data;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
