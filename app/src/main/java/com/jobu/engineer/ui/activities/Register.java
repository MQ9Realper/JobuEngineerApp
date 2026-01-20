package com.jobu.engineer.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.ActivityRegisterBinding;

/**
 * Register Activity.
 */
public class Register extends AppCompatActivity {
  private ActivityRegisterBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityRegisterBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Init toolbar
    AppUtils.initToolbar(this, binding.toolbar.getRoot(), "Create Account", true);

    // Apply insets
    AppUtils.handleWindowInsets(Register.this, binding.main);

  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }
}