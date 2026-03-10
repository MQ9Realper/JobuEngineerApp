package com.jobu.engineer.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jobu.engineer.R;
import com.jobu.engineer.data.models.dto.JobHistory;
import com.jobu.engineer.databinding.FragmentJobsBinding;
import com.jobu.engineer.ui.adapters.JobHistoryAdapter;
import java.util.List;

/**
 * Jobs Fragment.
 */
public class Jobs extends Fragment {
  private FragmentJobsBinding binding;
  private String selectedStatus = "ALL";

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

    // Show the job history
    showJobHistory();

    return binding.getRoot();
  }

  /**
   * This method is responsible for showing the job history.
   */
  private void showJobHistory() {
    JobHistoryAdapter adapter = new JobHistoryAdapter(getJobHistory(), new JobHistoryAdapter.OnJobHistoryClickListener() {
      @Override
      public void onJobClick(JobHistory jobHistory) {

      }
    });

    binding.recyclerViewJobs.setAdapter(adapter);
    binding.recyclerViewJobs.setHasFixedSize(true);
    binding.recyclerViewJobs.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

    binding.chipGroupFilters.setOnCheckedStateChangeListener((group, checkedIds) -> {
      if (checkedIds.isEmpty()) {
        return;
      }
      int checkedId = checkedIds.get(0);

      if (checkedId == R.id.chipAll) {
        adapter.filterByStatus("ALL");
        selectedStatus = "ALL";
      } else if (checkedId == R.id.chipCompleted) {
        adapter.filterByStatus("Completed");
        selectedStatus = "Completed";
      } else if (checkedId == R.id.chipRejected) {
        adapter.filterByStatus("Rejected");
        selectedStatus = "Rejected";
      }
    });

    // Setup search
    setUpSearch(adapter);

  }

  /**
   * This method is responsible for getting the job history.
   *
   * @return a list of JobHistory objects
   */
  private List<JobHistory> getJobHistory() {
    JobHistory job1 = new JobHistory();
    job1.setServiceName("Electrical - Appliance Repair");
    job1.setCustomerName("Jane Mwangi");
    job1.setLocation("Kilimani, Nairobi");
    job1.setDate("09 Feb 2026, 02:25 AM");
    job1.setStatus("Completed");

    JobHistory job2 = new JobHistory();
    job2.setServiceName("Plumbing - Drainage");
    job2.setCustomerName("Peter Otieno");
    job2.setLocation("Westlands, Nairobi");
    job2.setDate("08 Feb 2026, 04:10 PM");
    job2.setStatus("Rejected");

    JobHistory job3 = new JobHistory();
    job3.setServiceName("Cleaning - Deep Cleaning");
    job3.setCustomerName("Mary Wanjiru");
    job3.setLocation("Lavington, Nairobi");
    job3.setDate("07 Feb 2026, 11:45 AM");
    job3.setStatus("Completed");

    JobHistory job4 = new JobHistory();
    job4.setServiceName("IT - Network Setup");
    job4.setCustomerName("Samuel Kariuki");
    job4.setLocation("Upper Hill, Nairobi");
    job4.setDate("06 Feb 2026, 03:20 PM");
    job4.setStatus("Completed");

    JobHistory job5 = new JobHistory();
    job5.setServiceName("Telecom - CCTV Installation");
    job5.setCustomerName("Grace Njeri");
    job5.setLocation("Karen, Nairobi");
    job5.setDate("05 Feb 2026, 09:30 AM");
    job5.setStatus("Cancelled");

    return List.of(job1, job2, job3, job4, job5);
  }

  /**
   * This method is responsible for setting up the search functionality.
   */
  private void setUpSearch(JobHistoryAdapter adapter) {
    binding.searchBox.edtSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void afterTextChanged(Editable editable) {

      }

      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        adapter.filterByStatusAndQuery(selectedStatus, charSequence.toString());
      }
    });
  }
}