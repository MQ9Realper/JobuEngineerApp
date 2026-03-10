package com.jobu.engineer.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jobu.engineer.data.models.dto.Job;
import com.jobu.engineer.databinding.ItemAvailableJobBinding;
import java.util.List;

/**
 * This is the AvailableJobsAdapter.
 * It is responsible for displaying the available jobs in a RecyclerView.
 */
public class AvailableJobsAdapter extends RecyclerView.Adapter<AvailableJobsAdapter.JobViewHolder> {
  private List<Job> jobs;
  private final OnJobActionListener listener;

  /**
   * Constructor for the AvailableJobsAdapter.
   *
   * @param jobs     a list of Job objects
   * @param listener an instance of OnJobActionListener
   */
  public AvailableJobsAdapter(List<Job> jobs, OnJobActionListener listener) {
    this.jobs = jobs;
    this.listener = listener;
  }

  @NonNull
  @Override
  public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemAvailableJobBinding view = ItemAvailableJobBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new JobViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
    Job job = jobs.get(position);

    holder.binding.txtServiceName.setText(job.getServiceName());
    holder.binding.txtLocation.setText(job.getLocation());
    holder.binding.txtRequestedTime.setText(job.getRequestedTime());

    holder.binding.btnAccept.setOnClickListener(v -> {
      if (listener != null) {
        listener.onAccept(job);
      }
    });

    holder.binding.btnReject.setOnClickListener(v -> {
      if (listener != null) {
        listener.onReject(job);
      }
    });
  }

  @Override
  public int getItemCount() {
    return jobs == null ? 0 : jobs.size();
  }

  /**
   * Updates the list of jobs and notifies the adapter that the data has changed.
   *
   * @param newJobs the new list of jobs to be displayed
   */
  public void updateJobs(List<Job> newJobs) {
    this.jobs = newJobs;
    notifyDataSetChanged();
  }

  /**
   * ViewHolder class for the AvailableJobsAdapter.
   * It holds the view for each item in the RecyclerView.
   */
  public static class JobViewHolder extends RecyclerView.ViewHolder {
    private final ItemAvailableJobBinding binding;

    public JobViewHolder(@NonNull ItemAvailableJobBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

  /**
   * Interface for handling job actions.
   */
  public interface OnJobActionListener {
    void onAccept(Job job);

    void onReject(Job job);
  }

}