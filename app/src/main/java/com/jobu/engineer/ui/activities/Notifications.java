package com.jobu.engineer.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.ActivityNotificationsBinding;

/**
 * Notifications Activity.
 */
public class Notifications extends AppCompatActivity {
  private ActivityNotificationsBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Init toolbar
    AppUtils.initToolbar(this, binding.toolbar.getRoot(), "Notifications", true);

    // Apply insets
    AppUtils.handleWindowInsets(Notifications.this, binding.main);

  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }

}