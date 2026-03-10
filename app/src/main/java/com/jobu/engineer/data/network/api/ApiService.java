package com.jobu.engineer.data.network.api;

import com.google.gson.JsonObject;
import com.jobu.engineer.common.Constants;
import com.jobu.engineer.data.models.retrofit.LoginResponse;
import com.jobu.engineer.data.models.retrofit.RegistrationResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * This interface defines the API endpoints for network communication, specifically for user authentication.
 */
public interface ApiService {

  @POST(Constants.LOGIN_ENDPOINT)
  @Headers({"Content-Type: application/json"})
  Call<LoginResponse> login(@Body JsonObject request);

  @POST(Constants.REGISTER_ENDPOINT)
  @Headers({"Content-Type: application/json"})
  Call<RegistrationResponse> register(@Body JsonObject request);

}
