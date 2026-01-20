package com.jobu.engineer.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.jobu.engineer.BuildConfig;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.FragmentProfileBinding;

/**
 * Profile Fragment.
 */
public class Profile extends Fragment {
  private FragmentProfileBinding binding;

  public Profile() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentProfileBinding.inflate(inflater, container, false);

    // Show profile information
    showProfileInfo();

    return binding.getRoot();
  }

  /**
   * Show profile information.
   */
  @SuppressLint("SetTextI18n")
  private void showProfileInfo() {
    binding.initialsView.setText(AppUtils.getInitials("John Doe"));
    binding.txtUsername.setText("John Doe");
    binding.txtStatus.setText("NOT VERIFIED");
    binding.layoutProfilePhone.txtProfilePhoneNumber.setText("+254 712 345 678");
    binding.layoutProfileNationalId.txtProfileNationalIdentification.setText("367338383");
    binding.txtAppVersion.setText("App Version v%s (%s)".formatted(BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
  }

}