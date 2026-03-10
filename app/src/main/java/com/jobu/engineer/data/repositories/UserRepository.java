package com.jobu.engineer.data.repositories;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jobu.engineer.data.cache.PreferenceHelper;
import com.jobu.engineer.data.callbacks.ApiCallback;
import com.jobu.engineer.data.models.dto.UserProfile;
import com.jobu.engineer.data.models.retrofit.ApiError;
import com.jobu.engineer.data.models.retrofit.LoginResponse;
import com.jobu.engineer.data.models.retrofit.RegistrationResponse;
import com.jobu.engineer.data.network.api.ApiService;
import com.jobu.engineer.data.network.api.NetworkClient;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is responsible for handling user-related data operations, such as authentication and user information management. It interacts with the API service for network requests and uses the PreferenceHelper for local data storage.
 */
public class UserRepository {
  private final ApiService apiService;
  private final PreferenceHelper preferenceHelper;

  /**
   * This is the constructor for the UserRepository class, which initializes the API service and preference helper.
   *
   * @param context the context of the application
   */
  public UserRepository(Context context) {
    this.apiService = NetworkClient.getNetworkingClient(context).create(ApiService.class);
    this.preferenceHelper = new PreferenceHelper(context);
  }

  /**
   * This method constructs the request body for the authentication API call, containing the user's email and password.
   *
   * @param email    the user's email address
   * @param password the user's password
   * @return a JSONObject representing the request body for authentication
   */
  private JsonObject getAuthRequestBody(String email, String password) {
    JsonObject requestBody = new JsonObject();
    try {
      requestBody.addProperty("email", email);
      requestBody.addProperty("password", password);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return requestBody;
  }

  /**
   * This method performs the login operation by making an API call with the provided email and password. It handles the response and updates the local storage with the authentication token and user information if the login is successful.
   *
   * @param email    the user's email address
   * @param password the user's password
   * @param callback a callback to handle the success or failure of the login operation
   */
  public void login(String email, String password, ApiCallback<LoginResponse> callback) {
    apiService.login(getAuthRequestBody(email, password)).enqueue(new Callback<LoginResponse>() {
      @Override
      public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
          String token = response.body().getToken();
          preferenceHelper.saveAuthToken(token);
          preferenceHelper.saveUserId(response.body().getUser().getId());
          preferenceHelper.saveUserName(response.body().getUser().getName());
          preferenceHelper.saveUserEmail(response.body().getUser().getEmail());
          preferenceHelper.saveUserRole(response.body().getUser().getRole());
          preferenceHelper.saveUserSpecialization(response.body().getUser().getSpecialization());
          preferenceHelper.saveUserExperienceYears(response.body().getUser().getExperienceYears());
          preferenceHelper.saveUserHourlyRate(response.body().getUser().getHourlyRate());
          preferenceHelper.saveUserAvailable(response.body().getUser().isAvailable());
          preferenceHelper.saveAuthStatus(true);
          callback.onSuccess(response.body());
        } else {
          try {
            assert response.errorBody() != null;
            String errorBody = response.errorBody().string();
            ApiError errorResponse = new Gson().fromJson(errorBody, ApiError.class);
            callback.onFailure(new Throwable(errorResponse.getError()));
          } catch (Exception e) {
            callback.onFailure(e);
          }
        }
      }

      @Override
      public void onFailure(Call<LoginResponse> call, Throwable t) {
        callback.onFailure(t);
      }
    });
  }

  /**
   * This method retrieves the user's profile information from local storage and constructs a UserProfile object.
   *
   * @return a UserProfile object containing the user's profile information
   */
  public UserProfile getUserProfile() {
    UserProfile userProfile = new UserProfile();
    userProfile.setId(preferenceHelper.getUserId());
    userProfile.setName(preferenceHelper.getUserName());
    userProfile.setEmail(preferenceHelper.getUserEmail());
    userProfile.setRole(preferenceHelper.getUserRole());
    userProfile.setAvailable(preferenceHelper.getUserAvailable());
    userProfile.setSpecialization(preferenceHelper.getUserSpecialization());
    userProfile.setExperienceYears(preferenceHelper.getUserExperienceYears());
    userProfile.setHourlyRate(preferenceHelper.getUserHourlyRate());
    return userProfile;
  }

  /**
   * This method performs the logout operation by clearing the user's authentication token and profile information from local storage.
   */
  public void logout() {
    preferenceHelper.clearUserData();
    deleteAllTables();
  }

  /**
   * This method clears all user-related data from the local Realm database. It is typically called during the logout process to ensure that any cached user data is removed.
   */
  private void deleteAllTables() {
    try (Realm realm = Realm.getDefaultInstance()) {
      realm.executeTransaction(r -> {
        r.deleteAll();
      });
    }
  }

  /**
   * This method constructs the request body for the registration API call, containing the user's name, email, phone, address, and password.
   *
   * @param name            the user's name
   * @param email           the user's email address
   * @param specialization  the user's specialization
   * @param experienceYears the user's experience years
   * @param hourlyRate      the user's hourly rate
   * @param available       the user's availability status
   * @return a JSONObject representing the request body for registration
   */
  private JsonObject getRegistrationRequestBody(String name, String email, String specialization, int experienceYears, int hourlyRate, boolean available, String password) {
    JsonObject requestBody = new JsonObject();
    try {
      requestBody.addProperty("name", name);
      requestBody.addProperty("email", email);
      requestBody.addProperty("password", password);
      requestBody.addProperty("specialization", specialization);
      requestBody.addProperty("experience_years", experienceYears);
      requestBody.addProperty("hourly_rate", hourlyRate);
      requestBody.addProperty("available", available);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return requestBody;
  }

  /**
   * This method performs the registration operation by making an API call with the provided user information. It handles the response and returns the registration result through a callback.
   *
   * @param name            the user's name
   * @param email           the user's email address
   * @param specialization  the user's specialization
   * @param experienceYears the user's experience years
   * @param hourlyRate      the user's hourly rate
   * @param available       the user's availability status
   * @param callback        a callback to handle the success or failure of the registration operation
   */
  public void register(String name, String email, String specialization, int experienceYears, int hourlyRate, boolean available, String password, ApiCallback<RegistrationResponse> callback) {
    apiService.register(getRegistrationRequestBody(name, email, specialization, experienceYears, hourlyRate, available, password)).enqueue(new Callback<RegistrationResponse>() {
      @Override
      public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
          callback.onSuccess(response.body());
        } else {
          try {
            assert response.errorBody() != null;
            String errorBody = response.errorBody().string();
            ApiError errorResponse = new Gson().fromJson(errorBody, ApiError.class);
            callback.onFailure(new Throwable(errorResponse.getError()));
          } catch (Exception e) {
            callback.onFailure(e);
          }
        }
      }

      @Override
      public void onFailure(Call<RegistrationResponse> call, Throwable t) {
        callback.onFailure(t);
      }
    });
  }

}
