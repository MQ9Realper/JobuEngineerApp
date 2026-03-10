package com.jobu.engineer.data.models.retrofit;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the structure of an API error response, containing an error message.
 */
@Getter
@Setter
public class ApiError {
  @SerializedName("error")
  private String error;
}
