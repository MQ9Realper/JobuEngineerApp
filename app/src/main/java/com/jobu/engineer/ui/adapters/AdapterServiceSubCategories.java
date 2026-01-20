package com.jobu.engineer.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.data.models.dto.ServiceSubCategory;
import com.jobu.engineer.databinding.LayoutSubcategoriesListItemBinding;
import java.util.ArrayList;

/**
 * Adapter for Service Sub Categories.
 */
public class AdapterServiceSubCategories extends RecyclerView.Adapter<AdapterServiceSubCategories.ViewHolder> {
  private final ArrayList<ServiceSubCategory> serviceCategories;
  private OnItemClickListener itemClickListener;

  /**
   * Constructor for AdapterServiceSubCategories.
   *
   * @param serviceCategories List of service categories
   */
  public AdapterServiceSubCategories(ArrayList<ServiceSubCategory> serviceCategories) {
    this.serviceCategories = serviceCategories;
  }

  /**
   * ViewHolder class for service category items.
   */
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final LayoutSubcategoriesListItemBinding binding;

    public ViewHolder(LayoutSubcategoriesListItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutSubcategoriesListItemBinding binding = LayoutSubcategoriesListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ServiceSubCategory category = serviceCategories.get(position);
    holder.binding.txtSubCategoryName.setText(category.getName());
    holder.binding.initialsView.setText(AppUtils.getInitials(category.getName()));
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
   * Set the item click listener.
   *
   * @param listener The listener to set
   */
  public void setServiceClickListener(OnItemClickListener listener) {
    this.itemClickListener = listener;
  }

  /**
   * Interface for item click listener.
   */
  public interface OnItemClickListener {

    /**
     * Called when a service subcategory item is clicked.
     *
     * @param serviceSubCategory The clicked service subcategory
     */
    void onItemClick(ServiceSubCategory serviceSubCategory);
  }
}
