package com.jobu.engineer.data.callbacks;

/**
 * Generic callback interface for API responses.
 *
 * @param <T> the type of the successful response
 */
public interface ApiCallback<T> {
  /**
   * Called when the API call is successful.
   *
   * @param result the result of the API call
   */
  void onSuccess(T result);

  /**
   * Called when the API call fails.
   *
   * @param t the throwable representing the error
   */
  void onFailure(Throwable t);

}
