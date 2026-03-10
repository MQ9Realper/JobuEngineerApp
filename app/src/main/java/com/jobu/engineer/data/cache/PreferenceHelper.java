package com.jobu.engineer.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.annotations.SerializedName;
import com.jobu.engineer.R;

/**
 * This class is a helper for managing shared preferences in the application, specifically for storing and retrieving user authentication data and other related information.
 */
public class PreferenceHelper {
  private final SharedPreferences sharedPreferences;

  /**
   * This is the constructor for the shared preference helper class.
   *
   * @param context the context of the application
   */
  public PreferenceHelper(Context context) {
    this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
  }

  /**
   * This method saves the authentication token to shared preferences.
   *
   * @param token the authentication token to be saved
   */
  public void saveAuthToken(String token) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("auth_token", token);
    editor.apply();
  }

  /**
   * This method retrieves the authentication token from shared preferences.
   *
   * @return the authentication token, or null if it doesn't exist
   */
  public String getAuthToken() {
    return sharedPreferences.getString("auth_token", "");
  }

  /**
   * This method clears the authentication token from shared preferences.
   */
  public void clearAuthToken() {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.remove("auth_token");
    editor.apply();
  }

  /**
   * This method saves the user role to shared preferences.
   *
   * @param role the user role to be saved
   */
  public void saveUserRole(String role) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("user_role", role);
    editor.apply();
  }

  /**
   * This method retrieves the user role from shared preferences.
   *
   * @return the user role, or null if it doesn't exist
   */
  public String getUserRole() {
    return sharedPreferences.getString("user_role", "");
  }

  /**
   * This method saves the user ID to shared preferences.
   *
   * @param userId the user ID to be saved
   */
  public void saveUserId(int userId) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("user_id", userId);
    editor.apply();
  }

  /**
   * This method retrieves the user ID from shared preferences.
   *
   * @return the user ID, or -1 if it doesn't exist
   */
  public int getUserId() {
    return sharedPreferences.getInt("user_id", -1);
  }

  /**
   * This method saves the user name to shared preferences.
   *
   * @param name the user name to be saved
   */
  public void saveUserName(String name) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("user_name", name);
    editor.apply();
  }

  /**
   * This method retrieves the user name from shared preferences.
   *
   * @return the user name, or null if it doesn't exist
   */
  public String getUserName() {
    return sharedPreferences.getString("user_name", "");
  }

  /**
   * This method saves the user email to shared preferences.
   *
   * @param email the user email to be saved
   */
  public void saveUserEmail(String email) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("user_email", email);
    editor.apply();
  }

  /**
   * This method retrieves the user email from shared preferences.
   *
   * @return the user email, or null if it doesn't exist
   */
  public String getUserEmail() {
    return sharedPreferences.getString("user_email", "");
  }

  /**
   * This method saves the user specialization to shared preferences.
   *
   * @param specialization the user specialization to be saved
   */
  public void saveUserSpecialization(String specialization) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("specialization", specialization);
    editor.apply();
  }

  /**
   * This method retrieves the user specialization from shared preferences.
   *
   * @return the user specialization, or null if it doesn't exist
   */
  public String getUserSpecialization() {
    return sharedPreferences.getString("specialization", "");
  }

  /**
   * This method saves the user experience years to shared preferences.
   *
   * @param experienceYears the user experience years to be saved
   */
  public void saveUserExperienceYears(int experienceYears) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("experience_years", experienceYears);
    editor.apply();
  }

  /**
   * This method retrieves the user experience years from shared preferences.
   *
   * @return the user experience years, or 0 if it doesn't exist
   */
  public int getUserExperienceYears() {
    return sharedPreferences.getInt("experience_years", 0);
  }

  /**
   * This method saves the user hourly rate to shared preferences.
   *
   * @param hourlyRate the user hourly rate to be saved
   */
  public void saveUserHourlyRate(int hourlyRate) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("hourly_rate", hourlyRate);
    editor.apply();
  }

  /**
   * This method retrieves the user hourly rate from shared preferences.
   *
   * @return the user hourly rate, or 0 if it doesn't exist
   */
  public int getUserHourlyRate() {
    return sharedPreferences.getInt("hourly_rate", 0);
  }

  /**
   * This method saves the user available status to shared preferences.
   *
   * @param available a boolean indicating whether the user is available or not
   */
  public void saveUserAvailable(int available) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("available", available);
    editor.apply();
  }

  /**
   * This method retrieves the user available status from shared preferences.
   */
  public int getUserAvailable() {
    return sharedPreferences.getInt("available", 0);
  }

  /**
   * This method saves the authentication status (logged in or not) to shared preferences.
   *
   * @param isLoggedIn a boolean indicating whether the user is logged in or not
   */
  public void saveAuthStatus(boolean isLoggedIn) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean("is_logged_in", isLoggedIn);
    editor.apply();
  }

  /**
   * This method retrieves the authentication status from shared preferences.
   *
   * @return a boolean indicating whether the user is logged in or not
   */
  public boolean isLoggedIn() {
    return sharedPreferences.getBoolean("is_logged_in", false);
  }

  /**
   * This method clears all user data from shared preferences, including the authentication token, user role, user ID, user name, and user email.
   */
  public void clearUserData() {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.remove("auth_token");
    editor.remove("user_role");
    editor.remove("user_id");
    editor.remove("user_name");
    editor.remove("user_email");
    editor.remove("is_logged_in");
    editor.remove("user_phone");
    editor.remove("user_address");
    editor.apply();
  }

}
