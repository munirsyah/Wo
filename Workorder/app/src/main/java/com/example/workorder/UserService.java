package com.example.workorder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/auth/")
    Call<LoginResponse> userlogin(@Body LoginRequest loginRequest);
}
