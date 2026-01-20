package com.jobu.engineer.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.ActivityRequestServiceBinding;

/**
 * Request Service Activity.
 */
public class RequestService extends AppCompatActivity {
  private ActivityRequestServiceBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityRequestServiceBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Initialize toolbar
    AppUtils.initToolbar(this, binding.toolbar.getRoot(), "Service Request", true);

  }
}