package com.jobu.engineer.data.network.api;

import android.os.Looper;
import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is a helper class for intercepting API calls over the network.
 */
public class NetworkInterceptor {

  /**
   * This function returns an instance of OkHttpClient.
   *
   * @return OkHttpClient
   */
  @NonNull
  public static OkHttpClient getOkHttpClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.retryOnConnectionFailure(true);
    httpClient.connectTimeout(180, java.util.concurrent.TimeUnit.SECONDS);
    httpClient.readTimeout(180, java.util.concurrent.TimeUnit.SECONDS);
    httpClient.writeTimeout(180, java.util.concurrent.TimeUnit.SECONDS);
    httpClient.addInterceptor(new Interceptor() {
      @NonNull
      @Override
      public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder().build();
        Response response = chain.proceed(request);
        if (response.code() == 401 || response.code() == 403) {
          new Thread(new Runnable() {
            @Override
            public void run() {
              Looper.prepare();
              Looper.loop();
            }
          }).start();
          return response;
        }
        return response;
      }
    });
    return httpClient.build();
  }

}
