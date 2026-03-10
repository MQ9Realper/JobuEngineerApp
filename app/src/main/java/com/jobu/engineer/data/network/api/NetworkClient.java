package com.jobu.engineer.data.network.api;

import android.content.Context;
import com.jobu.engineer.common.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class represents the API Service interface.
 */
public class NetworkClient {

  /**
   * Returns a Retrofit client instance for network operations.
   *
   * @return a configured Retrofit object
   */
  public static Retrofit getNetworkingClient(Context context) {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(NetworkInterceptor.getOkHttpClient())
        .build();
  }

}