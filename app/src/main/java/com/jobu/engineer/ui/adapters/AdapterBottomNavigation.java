package com.jobu.engineer.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.jobu.engineer.ui.fragments.Home;
import com.jobu.engineer.ui.fragments.Jobs;
import com.jobu.engineer.ui.fragments.Profile;

/**
 * Adapter for the bottom navigation.
 */
public class AdapterBottomNavigation extends FragmentStateAdapter {

  /**
   * Constructor.
   *
   * @param fragmentActivity FragmentActivity
   */
  public AdapterBottomNavigation(@NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 0:
        return new Home();
      case 1:
        return new Jobs();
      case 2:
        return new Profile();
      default:
        throw new IllegalArgumentException("Invalid position: " + position);
    }
  }

  @Override
  public int getItemCount() {
    return 3;
  }

}
