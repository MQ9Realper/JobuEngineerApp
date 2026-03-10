package com.jobu.engineer.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jobu.engineer.R;
import com.jobu.engineer.data.models.dto.JobHistory;
import com.jobu.engineer.databinding.ItemJobHistoryBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This is the adapter for the JobHistory.
 */
public class JobHistoryAdapter extends RecyclerView.Adapter<JobHistoryAdapter.JobHistoryViewHolder> {
  private List<JobHistory> jobHistoryList;
  private final List<JobHistory> originalList;
  private final OnJobHistoryClickListener listener;

  /**
   * This is the constructor for the JobHistoryAdapter.
   *
   * @param jobHistoryList a list of JobHistory objects
   * @param listener       an instance of OnJobHistoryClickListener
   */
  public JobHistoryAdapter(List<JobHistory> jobHistoryList, OnJobHistoryClickListener listener) {
    this.jobHistoryList = new ArrayList<>(jobHistoryList);
    this.originalList = new ArrayList<>(jobHistoryList);
    this.listener = listener;
  }

  @NonNull
  @Override
  public JobHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemJobHistoryBinding binding = ItemJobHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new JobHistoryViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull JobHistoryViewHolder holder, int position) {
    JobHistory job = jobHistoryList.get(position);

    holder.binding.txtServiceName.setText(job.getServiceName());
    holder.binding.txtCustomerName.setText("Customer: " + job.getCustomerName());
    holder.binding.txtLocation.setText(job.getLocation());
    holder.binding.txtDate.setText(job.getDate());
    holder.binding.txtStatus.setText(job.getStatus());
    setStatusStyle(holder.binding.txtStatus, job.getStatus());

    holder.itemView.setOnClickListener(v -> {
      if (listener != null) {
        listener.onJobClick(job);
      }
    });
  }

  @Override
  public int getItemCount() {
    return jobHistoryList == null ? 0 : jobHistoryList.size();
  }

  /**
   * This method is responsible for updating the list of JobHistory objects.
   *
   * @param newList a list of JobHistory objects
   */
  public void updateList(List<JobHistory> newList) {
    this.jobHistoryList = newList;
    notifyDataSetChanged();
  }

  /**
   * This method is responsible for setting the status style of the TextView.
   *
   * @param textView the TextView to set the style for
   * @param status   the status of the job
   */
  private void setStatusStyle(TextView textView, String status) {
    if (status == null) {
      return;
    }
    Context context = textView.getContext();
    switch (status.toUpperCase()) {
      case "COMPLETED":
        textView.setBackgroundResource(R.drawable.bg_status_completed);
        textView.setTextColor(ContextCompat.getColor(context, R.color.success));
        break;

      case "REJECTED":
        textView.setBackgroundResource(R.drawable.bg_status_rejected);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        break;

      case "CANCELLED":
        textView.setBackgroundResource(R.drawable.bg_status_rejected);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        break;

      default:
        textView.setBackgroundResource(R.drawable.bg_status_chip);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        break;
    }
  }

  /**
   * This method is responsible for filtering the list of JobHistory objects by status.
   *
   * @param status the status to filter by
   */
  public void filterByStatus(String status) {
    jobHistoryList.clear();

    if (status == null || status.trim().isEmpty() || status.equalsIgnoreCase("ALL")) {
      jobHistoryList.addAll(originalList);
    } else {
      for (JobHistory job : originalList) {
        if (job.getStatus() != null
            && job.getStatus().trim().equalsIgnoreCase(status.trim())) {
          jobHistoryList.add(job);
        }
      }
    }

    notifyDataSetChanged();
  }

  /**
   * This method is responsible for filtering the list of JobHistory objects by status and query.
   *
   * @param status the status to filter by
   * @param query  the query to filter by
   */
  public void filterByStatusAndQuery(String status, String query) {
    jobHistoryList.clear();

    String safeStatus = status == null ? "" : status.trim().toUpperCase(Locale.ROOT);
    String safeQuery = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);

    for (JobHistory job : originalList) {
      boolean matchesStatus = safeStatus.isEmpty()
          || safeStatus.equals("ALL")
          || (job.getStatus() != null
          && job.getStatus().trim().equalsIgnoreCase(safeStatus));

      boolean matchesQuery = safeQuery.isEmpty()
          || (job.getServiceName() != null
          && job.getServiceName().toLowerCase(Locale.ROOT).contains(safeQuery))
          || (job.getCustomerName() != null
          && job.getCustomerName().toLowerCase(Locale.ROOT).contains(safeQuery))
          || (job.getLocation() != null
          && job.getLocation().toLowerCase(Locale.ROOT).contains(safeQuery));

      if (matchesStatus && matchesQuery) {
        jobHistoryList.add(job);
      }
    }

    notifyDataSetChanged();
  }

  /**
   * This is the ViewHolder for the JobHistory.
   */
  public static class JobHistoryViewHolder extends RecyclerView.ViewHolder {
    private final ItemJobHistoryBinding binding;

    public JobHistoryViewHolder(@NonNull ItemJobHistoryBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

  /**
   * This is the interface for the click listener of the JobHistory.
   */
  public interface OnJobHistoryClickListener {
    void onJobClick(JobHistory jobHistory);
  }
}