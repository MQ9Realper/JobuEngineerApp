package com.jobu.engineer.data.models.retrofit;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the response received from the login API call, containing the authentication token and user information.
 */
@Getter
@Setter
public class LoginResponse {
  @SerializedName("token")
  private String token;
  @SerializedName("user")
  private LoginResponseUser user;
}
