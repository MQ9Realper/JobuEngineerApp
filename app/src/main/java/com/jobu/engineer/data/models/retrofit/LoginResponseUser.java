package com.jobu.engineer.data.models.retrofit;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the user information returned in the login response.
 */
@Getter
@Setter
public class LoginResponseUser {
  @SerializedName("id")
  private int id;
  @SerializedName("name")
  private String name;
  @SerializedName("email")
  private String email;
  @SerializedName("role")
  private String role;
  @SerializedName("specialization")
  private String specialization;
  @SerializedName("experience_years")
  private int experienceYears;
  @SerializedName("hourly_rate")
  private int hourlyRate;
  @SerializedName("available")
  private boolean available;
}