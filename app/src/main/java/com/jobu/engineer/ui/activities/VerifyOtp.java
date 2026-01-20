package com.jobu.engineer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.jobu.engineer.R;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.ActivityVerifyOtpBinding;

/**
 * VerifyOtp Activity.
 */
public class VerifyOtp extends AppCompatActivity {
  private ActivityVerifyOtpBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Initialize Toolbar
    AppUtils.initToolbar(this, binding.toolbarVerifyOtp.getRoot(), getString(R.string.verify_otp), true);

    binding.btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(VerifyOtp.this, Main.class));
      }
    });

    // Apply insets
    AppUtils.handleWindowInsets(VerifyOtp.this, binding.main);

  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }
}