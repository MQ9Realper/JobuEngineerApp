package com.jobu.engineer.ui.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.jobu.engineer.data.callbacks.ApiCallback;
import com.jobu.engineer.data.models.dto.UserProfile;
import com.jobu.engineer.data.models.retrofit.LoginResponse;
import com.jobu.engineer.data.models.retrofit.RegistrationResponse;
import com.jobu.engineer.data.repositories.UserRepository;

/**
 * This ViewModel is responsible for managing user-related data and operations in the UI layer.
 * It interacts with the UserRepository to fetch and update user information as needed.
 */
public class ViewModelUser extends AndroidViewModel {
  private final UserRepository userRepository;

  /**
   * This is the constructor for the UserViewModel class, which initializes the UserRepository with the application context.
   *
   * @param application the application context used to initialize the UserRepository
   */
  public ViewModelUser(@NonNull Application application) {
    super(application);
    this.userRepository = new UserRepository(application.getApplicationContext());
  }

  /**
   * This method performs the login operation by calling the login method in the UserRepository, which handles the API call and updates the local storage with the authentication token and user information if the login is successful.
   *
   * @param email    the user's email address
   * @param password the user's password
   * @param callback a callback to handle the success or failure of the login operation
   */
  public void login(String email, String password, ApiCallback<LoginResponse> callback) {
    userRepository.login(email, password, callback);
  }

  /**
   * This method performs the registration operation by calling the register method in the UserRepository, which handles the API call to create a new user account with the provided information.
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
    userRepository.register(name, email, specialization, experienceYears, hourlyRate, available, password, callback);
  }

  /**
   * This method retrieves the user profile information from the UserRepository.
   *
   * @return a UserProfile object containing the user's profile information
   */
  public UserProfile getUserProfile() {
    return userRepository.getUserProfile();
  }

  /**
   * This method performs the logout operation by calling the logout method in the UserRepository, which clears the user's authentication data and updates the local storage accordingly.
   */
  public void logout() {
    userRepository.logout();
  }

}
