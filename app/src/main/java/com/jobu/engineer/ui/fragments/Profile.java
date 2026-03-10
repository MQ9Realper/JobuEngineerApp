package com.jobu.engineer.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.jobu.engineer.BuildConfig;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.data.models.dto.UserProfile;
import com.jobu.engineer.databinding.FragmentProfileBinding;
import com.jobu.engineer.ui.activities.LoginWithEmail;
import com.jobu.engineer.ui.bottomsheets.BottomSheetConfirm;
import com.jobu.engineer.ui.viewmodels.ViewModelUser;

/**
 * Profile Fragment.
 */
public class Profile extends Fragment {
  private FragmentProfileBinding binding;
  private ViewModelUser viewModelUser;

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

    // Initialize ViewModel
    viewModelUser = new ViewModelProvider(this).get(ViewModelUser.class);

    // Show profile information
    showProfileInfo();

    return binding.getRoot();
  }

  /**
   * Show profile information.
   */
  @SuppressLint("SetTextI18n")
  private void showProfileInfo() {
    UserProfile userProfile = viewModelUser.getUserProfile();
    binding.initialsView.setText(AppUtils.getInitials(userProfile.getName()));
    binding.txtUsername.setText(userProfile.getName());
    binding.txtUserEmail.setText(userProfile.getEmail());
    binding.txtStatus.setText("VERIFIED");
    binding.layoutProfileSpecialization.txtProfileSpecialization.setText(userProfile.getSpecialization());
    binding.layoutProfileYearsOfExperience.txtProfileYearsOfExperience.setText(String.valueOf(userProfile.getExperienceYears()));
    binding.layoutProfileRate.txtProfileRate.setText("KES " + userProfile.getHourlyRate());
    binding.txtAppVersion.setText("v" + BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")");

    binding.layoutProfileLogout.getRoot().setOnClickListener(v -> showConfirmationDialog());

  }

  /**
   * Show confirmation dialog for logout.
   * If the user confirms, it logs out the user, shows a toast message, and navigates to the LoginWithEmail activity, clearing the back stack to prevent returning to the profile after logout.
   */
  private void showConfirmationDialog() {
    BottomSheetConfirm bottomSheetConfirm = new BottomSheetConfirm();
    bottomSheetConfirm.setTitle("Confirm Logout");
    bottomSheetConfirm.setMessage("Are you sure you want to log out?");
    bottomSheetConfirm.setOnAlertBottomSheetConfirm(new BottomSheetConfirm.OnAlertBottomSheetConfirm() {
      @Override
      public void onConfirm() {
        viewModelUser.logout();
        AppUtils.showToast((AppCompatActivity) getContext(), "Logged out successfully");
        Intent intent = new Intent(getContext(), LoginWithEmail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
      }
    });
    bottomSheetConfirm.show(getParentFragmentManager(), "BottomSheetConfirm");
  }

}