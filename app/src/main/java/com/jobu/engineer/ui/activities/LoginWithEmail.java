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
import com.jobu.engineer.data.models.retrofit.LoginResponse;
import com.jobu.engineer.databinding.ActivityLoginWithEmailBinding;
import com.jobu.engineer.ui.bottomsheets.BottomSheetAlert;
import com.jobu.engineer.ui.viewmodels.ViewModelUser;

/**
 * Login Activity.
 */
public class LoginWithEmail extends AppCompatActivity {
  private ActivityLoginWithEmailBinding binding;
  private ViewModelUser viewModelUser;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    viewModelUser = new ViewModelProvider(this).get(ViewModelUser.class);
    binding = ActivityLoginWithEmailBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.loginForm.btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //startAuthAuthentication();
        startActivity(new Intent(LoginWithEmail.this, Main.class));
      }
    });

    binding.loginForm.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(LoginWithEmail.this, Register.class));
      }
    });

    // Set the password visibility click listener
    binding.loginForm.loginPassword.passwordVisibility.setOnClickListener(this::togglePasswordVisibility);

  }

  /**
   * This method is responsible for starting the authentication process by calling the startAuthAuthentication() method.
   */
  private void startAuthAuthentication() {
    String email = binding.loginForm.loginEmail.emailEditText.getText().toString().trim();
    String password = binding.loginForm.loginPassword.passwordEditText.getText().toString().trim();

    if (email.isEmpty() && password.isEmpty()) {
      AppUtils.showToast(LoginWithEmail.this, "Email and Password are required");
      binding.loginForm.loginEmail.emailEditText.requestFocus();
      return;
    }

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      AppUtils.showToast(LoginWithEmail.this, "Please enter a valid email address");
      binding.loginForm.loginEmail.emailEditText.requestFocus();
      return;
    }

    if (email.isEmpty()) {
      AppUtils.showToast(LoginWithEmail.this, "Email is required");
      binding.loginForm.loginEmail.emailEditText.requestFocus();
      return;
    }

    if (password.isEmpty()) {
      AppUtils.showToast(LoginWithEmail.this, "Password is required");
      binding.loginForm.loginPassword.passwordEditText.requestFocus();
      return;
    }

    authenticateUser(email, password);
  }

  /**
   * This method is responsible for authenticating the user by calling the login method in the ViewModelUser, which handles the API call and updates the local storage with the authentication token and user information if the login is successful. The method takes the user's email and password as parameters and uses a callback to handle the success or failure of the login operation.
   *
   * @param email    the user's email address
   * @param password the user's password
   */
  private void authenticateUser(String email, String password) {
    AppUtils.showProgress(this);
    viewModelUser.login(email, password, new ApiCallback<LoginResponse>() {
      @Override
      public void onSuccess(LoginResponse result) {
        AppUtils.dismissProgress();
        startActivity(new Intent(LoginWithEmail.this, Main.class));
        finish();
      }

      @Override
      public void onFailure(Throwable t) {
        AppUtils.dismissProgress();
        showError(t.getMessage() != null ? t.getMessage() : "An error occurred during login. Please try again.");
      }
    });
  }

  /**
   * This method displays an error message to the user using a BottomSheetAlert. It takes a string message as a parameter and configures the BottomSheetAlert to show an error alert with the provided message. The alert is displayed to inform the user about the login failure.
   *
   * @param message the error message to be displayed in the alert
   */
  private void showError(String message) {
    BottomSheetAlert bottomSheetAlert = new BottomSheetAlert();
    bottomSheetAlert.setAlertType(AlertType.ERROR);
    bottomSheetAlert.setTitle("Login Failed");
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
   * This method toggles the visibility of the password.
   */
  public void togglePasswordVisibility(View view) {
    AppUtils.togglePasswordVisibility(binding.loginForm.loginPassword.passwordEditText, binding.loginForm.loginPassword.passwordVisibility);
  }

}