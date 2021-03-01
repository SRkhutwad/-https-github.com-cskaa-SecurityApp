package com.project.verification.ClientShowEmpData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connectionn {

    public static final String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    public static Retrofit retrofit = null;

    public static Retrofit getApiCon(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        System.out.println("Retrofit returns: "+retrofit);
        return retrofit;
    }
}
