package com.jobu.engineer.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationBarView;
import com.jobu.engineer.R;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.ActivityMainBinding;
import com.jobu.engineer.ui.adapters.AdapterBottomNavigation;

/**
 * Main Activity.
 */
public class Main extends AppCompatActivity {
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Setup bottom navigation
    setupBottomNavigation();

    // Apply insets
    AppUtils.handleWindowInsets(Main.this, binding.main);

  }

  /**
   * This method is for setting up the bottom navigation view and view pager.
   */
  private void setupBottomNavigation() {
    AdapterBottomNavigation adapterMainBottomNav = new AdapterBottomNavigation(this);
    binding.viewPager.setAdapter(adapterMainBottomNav);

    binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuHome) {
          binding.viewPager.setCurrentItem(0);
          return true;
        } else if (item.getItemId() == R.id.menuJobs) {
          binding.viewPager.setCurrentItem(1);
          return true;
        } else if (item.getItemId() == R.id.menuProfile) {
          binding.viewPager.setCurrentItem(2);
          return true;
        }
        return false;
      }
    });

    binding.viewPager.setUserInputEnabled(false);
    binding.viewPager.setOffscreenPageLimit(3);
  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }

}