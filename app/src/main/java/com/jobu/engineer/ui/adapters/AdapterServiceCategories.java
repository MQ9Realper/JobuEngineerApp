package com.jobu.engineer.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jobu.engineer.data.models.dto.ServiceCategory;
import com.jobu.engineer.databinding.LayoutServiceCategoryListItemBinding;
import java.util.ArrayList;

/**
 * Adapter for Service Categories.
 */
public class AdapterServiceCategories extends RecyclerView.Adapter<AdapterServiceCategories.ViewHolder> {
  private final ArrayList<ServiceCategory> serviceCategories;
  private OnItemClickListener itemClickListener;

  /**
   * Constructor for AdapterServiceCategories.
   *
   * @param serviceCategories List of service categories
   */
  public AdapterServiceCategories(ArrayList<ServiceCategory> serviceCategories) {
    this.serviceCategories = serviceCategories;
  }

  /**
   * ViewHolder class for service category items.
   */
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final LayoutServiceCategoryListItemBinding binding;

    public ViewHolder(LayoutServiceCategoryListItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutServiceCategoryListItemBinding binding = LayoutServiceCategoryListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ServiceCategory category = serviceCategories.get(position);
    holder.binding.txtServiceCategory.setText(category.getName());
    holder.binding.iconServiceCategory.setImageResource(category.getIconResId());
    holder.itemView.setOnClickListener(view -> {
      if (itemClickListener != null) {
        itemClickListener.onItemClick(category);
      }
    });
  }

  @Override
  public int getItemCount() {
    return serviceCategories.size();
  }

  /**
   * Set the item click listener for service categories.
   *
   * @param listener The listener to handle item clicks
   */
  public void setServiceClickListener(OnItemClickListener listener) {
    this.itemClickListener = listener;
  }

  /**
   * Interface for item click listener.
   */
  public interface OnItemClickListener {

    /**
     * Called when a service category item is clicked.
     *
     * @param category The clicked service category
     */
    void onItemClick(ServiceCategory category);

  }
}
