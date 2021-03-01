package com.project.verification.ClientShowEmpData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apii {

    @GET("employees")
    Call<EmpPojoo> getEmployees();
}
