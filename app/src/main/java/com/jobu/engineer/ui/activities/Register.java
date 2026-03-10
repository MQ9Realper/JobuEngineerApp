package com.jobu.engineer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.jobu.engineer.common.AlertType;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.data.callbacks.ApiCallback;
import com.jobu.engineer.data.models.retrofit.RegistrationResponse;
import com.jobu.engineer.databinding.ActivityRegisterBinding;
import com.jobu.engineer.ui.bottomsheets.BottomSheetAlert;
import com.jobu.engineer.ui.bottomsheets.BottomSheetConfirm;
import com.jobu.engineer.ui.viewmodels.ViewModelUser;

/**
 * Register Activity.
 */
public class Register extends AppCompatActivity {
  private ActivityRegisterBinding binding;
  private ViewModelUser viewModelUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityRegisterBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Init view model
    viewModelUser = new ViewModelProvider(this).get(ViewModelUser.class);

    // Init toolbar
    AppUtils.initToolbar(this, binding.toolbar.getRoot(), "Create Account", true);

    // Apply insets
    AppUtils.handleWindowInsets(Register.this, binding.main);

    // Set click listener for the register button
    binding.btnRegister.setOnClickListener(this::submitRegistration);

    // Set click listener for password visibility toggle
    binding.layoutRegisterPassword.passwordVisibility.setOnClickListener(this::togglePasswordVisibility);

  }

  /**
   * This method is responsible for handling the user registration process. It retrieves the input values from the registration form, validates them, and if all inputs are valid, it calls the register method in the ViewModelUser to perform the registration API call. The method also handles the success and failure of the registration operation by showing appropriate messages to the user and navigating to the login screen upon successful registration.
   */
  public void registerUser(View view) {
    String name = binding.layoutRegisterName.edtNameEditText.getText().toString().trim();
    String email = binding.layoutRegisterEmail.emailEditText.getText().toString().trim();
    int experience = Integer.parseInt(binding.layoutRegisterExperience.edtYearsOfExperienceEditText.getText().toString().trim());
    String specialization = binding.layoutRegisterSpecialization.edtSpecializationEditText.getText().toString().trim();
    int rate = Integer.parseInt(binding.layoutRegisterHourlyRate.edtHourlyRateEditText.getText().toString().trim());
    String password = binding.layoutRegisterPassword.passwordEditText.getText().toString();

    // Perform registration
    AppUtils.showProgress(this);
    viewModelUser.register(name, email, specialization, experience, rate, true, password, new ApiCallback<RegistrationResponse>() {
      @Override
      public void onSuccess(RegistrationResponse result) {
        AppUtils.dismissProgress();
        showSuccess();
      }

      @Override
      public void onFailure(Throwable t) {
        AppUtils.dismissProgress();
        showError(t.getMessage() != null ? t.getMessage() : "An error occurred during registration. Please try again.");
      }
    });
  }

  /**
   * This method displays an error message to the user using a BottomSheetAlert. It takes a string message as a parameter and configures the BottomSheetAlert to show an error alert with the provided message. The alert is displayed to inform the user about the registration failure.
   *
   * @param message the error message to be displayed in the alert
   */
  private void showError(String message) {
    BottomSheetAlert bottomSheetAlert = new BottomSheetAlert();
    bottomSheetAlert.setAlertType(AlertType.ERROR);
    bottomSheetAlert.setTitle("Registration Failed");
    bottomSheetAlert.setMessage(message);
    bottomSheetAlert.setOnAlertBottomSheetClose(new BottomSheetAlert.OnAlertBottomSheetClose() {
      @Override
      public void onClose() {
        // Handle any actions needed when the alert is closed, if necessary
      }
    });
    bottomSheetAlert.show(getSupportFragmentManager(), "BottomSheetAlert");
  }

  /**
   * This method displays a success message to the user using a BottomSheetAlert. It takes a string message as a parameter and configures the BottomSheetAlert to show a success alert with the provided message. The alert is displayed to inform the user about the successful registration.
   */
  private void showSuccess() {
    BottomSheetAlert bottomSheetAlert = new BottomSheetAlert();
    bottomSheetAlert.setAlertType(AlertType.SUCCESS);
    bottomSheetAlert.setTitle("Registration Successful");
    bottomSheetAlert.setMessage("Your account has been created successfully. Please log in to continue.");
    bottomSheetAlert.setOnAlertBottomSheetClose(new BottomSheetAlert.OnAlertBottomSheetClose() {
      @Override
      public void onClose() {
        startActivity(new Intent(Register.this, LoginWithEmail.class));
        finish();
      }
    });
    bottomSheetAlert.show(getSupportFragmentManager(), "BottomSheetAlert");
  }

  /**
   * Returns true if the input fields are valid, otherwise shows appropriate error messages and returns false. This method checks if the name, email, phone, address, and password fields are not empty and if the email and phone number are in valid formats. If any of the validations fail, it displays a toast message to the user indicating the specific error and sets focus to the corresponding input field.
   *
   * @param name           this is the name input field value that is being validated. It checks if the name is not empty and shows an error message if it is.
   * @param email          this is the email input field value that is being validated. It checks if the email is not empty and if it matches the email pattern. If either check fails, it shows an error message.
   * @param specialization this is the specialization input field value that is being validated. It checks if the specialization is not empty and shows an error message if it is.
   * @param experience     this is the experience input field value that is being validated. It checks if the experience is not empty and shows an error message if it is.
   * @param rate           this is the rate input field value that is being validated. It checks if the rate is not empty and shows an error message if it is.
   * @param password       this is the password input field value that is being validated. It checks if the password is not empty and shows an error message if it is.
   */
  private boolean isInputValid(String name, String email, String specialization, String experience, String rate, String password) {
    if (name.isEmpty()) {
      AppUtils.showToast(this, "Name is required");
      binding.layoutRegisterName.edtNameEditText.requestFocus();
      return false;
    }

    if (email.isEmpty()) {
      AppUtils.showToast(this, "Email is required");
      binding.layoutRegisterEmail.emailEditText.requestFocus();
      return false;
    }

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      AppUtils.showToast(this, "Please enter a valid email address");
      binding.layoutRegisterEmail.emailEditText.requestFocus();
      return false;
    }

    if (experience.isEmpty()) {
      AppUtils.showToast(this, "Years of experience is required");
      binding.layoutRegisterExperience.edtYearsOfExperienceEditText.requestFocus();
      return false;
    }

    if (specialization.isEmpty()) {
      AppUtils.showToast(this, "Specialization is required");
      binding.layoutRegisterSpecialization.edtSpecializationEditText.requestFocus();
      return false;
    }

    if (rate.isEmpty()) {
      AppUtils.showToast(this, "Hourly rate is required");
      binding.layoutRegisterHourlyRate.edtHourlyRateEditText.requestFocus();
      return false;
    }

    if (password.isEmpty()) {
      AppUtils.showToast(this, "Password is required");
      binding.layoutRegisterPassword.passwordEditText.requestFocus();
      return false;
    }

    return true;
  }

  /**
   * This method is called when the register button is clicked. It retrieves the input values from the registration form, validates them using the isInputValid method, and if all inputs are valid, it calls the confirmRegistration method to display a confirmation dialog before proceeding with the registration.
   *
   * @param view the view that was clicked
   */
  private void submitRegistration(View view) {
    String name = binding.layoutRegisterName.edtNameEditText.getText().toString().trim();
    String email = binding.layoutRegisterEmail.emailEditText.getText().toString().trim();
    String experience = binding.layoutRegisterExperience.edtYearsOfExperienceEditText.getText().toString().trim();
    String specialization = binding.layoutRegisterSpecialization.edtSpecializationEditText.getText().toString().trim();
    String rate = binding.layoutRegisterHourlyRate.edtHourlyRateEditText.getText().toString().trim();
    String password = binding.layoutRegisterPassword.passwordEditText.getText().toString();

    if (isInputValid(name, email, specialization, experience, rate, password)) {
      confirmRegistration(view);
    }
  }

  /**
   * This method displays a confirmation dialog to the user before proceeding with the registration. It uses a BottomSheetConfirm to ask the user if they are sure about creating an account with the provided information. If the user confirms, it calls the registerUser method to perform the registration.
   *
   * @param view the view that was clicked
   */
  private void confirmRegistration(View view) {
    BottomSheetConfirm bottomSheetConfirm = new BottomSheetConfirm();
    bottomSheetConfirm.setTitle("Confirm Registration");
    bottomSheetConfirm.setMessage("Are you sure you want to create an account with the provided information?");
    bottomSheetConfirm.setOnAlertBottomSheetConfirm(new BottomSheetConfirm.OnAlertBottomSheetConfirm() {
      @Override
      public void onConfirm() {
        registerUser(view);
      }
    });
    bottomSheetConfirm.show(getSupportFragmentManager(), "BottomSheetConfirm");
  }

  /**
   * This method toggles the visibility of the password.
   */
  public void togglePasswordVisibility(View view) {
    AppUtils.togglePasswordVisibility(binding.layoutRegisterPassword.passwordEditText, binding.layoutRegisterPassword.passwordVisibility);
  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }
}