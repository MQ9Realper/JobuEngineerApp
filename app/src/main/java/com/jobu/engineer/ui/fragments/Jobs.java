package com.jobu.engineer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.jobu.engineer.databinding.FragmentJobsBinding;

/**
 * Jobs Fragment.
 */
public class Jobs extends Fragment {
  private FragmentJobsBinding binding;

  public Jobs() {
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
    binding = FragmentJobsBinding.inflate(inflater, container, false);

    return binding.getRoot();
  }
}