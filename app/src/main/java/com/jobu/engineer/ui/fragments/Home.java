package com.jobu.engineer.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jobu.engineer.data.models.dto.Job;
import com.jobu.engineer.databinding.FragmentHomeBinding;
import com.jobu.engineer.ui.adapters.AvailableJobsAdapter;
import com.jobu.engineer.ui.bottomsheets.BottomSheetConfirm;
import java.util.List;

/**
 * Home Fragment.
 */
public class Home extends Fragment {
  private FragmentHomeBinding binding;

  public Home() {
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
    binding = FragmentHomeBinding.inflate(inflater, container, false);

    // Show the available jobs
    showJobs();

    return binding.getRoot();
  }

  /**
   * This method is responsible for showing the available jobs.
   * It creates an instance of AvailableJobsAdapter and sets it as the adapter for the RecyclerView.
   */
  private void showJobs() {
    AvailableJobsAdapter adapterJobs = new AvailableJobsAdapter(getJobs(), new AvailableJobsAdapter.OnJobActionListener() {
      @Override
      public void onAccept(Job job) {
        confirmAccept(job.getServiceName());
      }

      @Override
      public void onReject(Job job) {
        confirmReject(job.getServiceName());
      }
    });

    binding.rvAvailableJobs.setAdapter(adapterJobs);
    binding.rvAvailableJobs.setHasFixedSize(true);
    binding.rvAvailableJobs.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

  }

  /**
   * This method is responsible for getting the list of jobs.
   * It creates a list of Job objects and adds some sample data to it.
   *
   * @return a list of Job objects
   */
  private List<Job> getJobs() {
    Job job1 = new Job();
    job1.setServiceName("Electrical - Appliance Repair");
    job1.setLocation("Westlands, Nairobi");
    job1.setRequestedTime("Requested 5 mins ago");

    Job job2 = new Job();
    job2.setServiceName("Plumbing - Drainage");
    job2.setLocation("Kilimani, Nairobi");
    job2.setRequestedTime("Requested 12 mins ago");

    Job job3 = new Job();
    job3.setServiceName("Cleaning - Deep Cleaning");
    job3.setLocation("Lavington, Nairobi");
    job3.setRequestedTime("Requested 20 mins ago");

    Job job4 = new Job();
    job4.setServiceName("IT - Network Setup");
    job4.setLocation("Upper Hill, Nairobi");
    job4.setRequestedTime("Requested 35 mins ago");

    Job job5 = new Job();
    job5.setServiceName("Telecom - CCTV Installation");
    job5.setLocation("Karen, Nairobi");
    job5.setRequestedTime("Requested 1 hour ago");

    return List.of(job1, job2, job3, job4, job5);
  }

  /**
   * This method is responsible for confirming the acceptance of a job.
   * It creates an instance of BottomSheetConfirm and sets its title and message.
   *
   * @param jobName the name of the job to be accepted
   */
  private void confirmAccept(String jobName) {
    BottomSheetConfirm bottomSheetConfirm = new BottomSheetConfirm();
    bottomSheetConfirm.setTitle("Accept Job");
    bottomSheetConfirm.setMessage("Are you sure you want to accept the job: " + jobName + "?");
    bottomSheetConfirm.setOnAlertBottomSheetConfirm(new BottomSheetConfirm.OnAlertBottomSheetConfirm() {
      @Override
      public void onConfirm() {

      }
    });
    bottomSheetConfirm.show(getChildFragmentManager(), "BottomSheetConfirm");
  }

  /**
   * This method is responsible for confirming the rejection of a job.
   * It creates an instance of BottomSheetConfirm and sets its title and message.
   *
   * @param jobName the name of the job to be rejected
   */
  private void confirmReject(String jobName) {
    BottomSheetConfirm bottomSheetConfirm = new BottomSheetConfirm();
    bottomSheetConfirm.setTitle("Reject Job");
    bottomSheetConfirm.setMessage("Are you sure you want to reject the job: " + jobName + "?");
    bottomSheetConfirm.setOnAlertBottomSheetConfirm(new BottomSheetConfirm.OnAlertBottomSheetConfirm() {
      @Override
      public void onConfirm() {

      }
    });
    bottomSheetConfirm.show(getChildFragmentManager(), "BottomSheetConfirm");
  }

}