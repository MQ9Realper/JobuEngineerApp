package com.jobu.engineer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.data.models.dto.ServiceSubCategory;
import com.jobu.engineer.databinding.ActivitySelectSubCategoriesBinding;
import com.jobu.engineer.ui.adapters.AdapterServiceSubCategories;
import com.jobu.engineer.ui.bottomsheets.BottomSheetBookService;

/**
 * Select SubCategories Activity.
 */
public class SelectSubCategories extends AppCompatActivity {
  private ActivitySelectSubCategoriesBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySelectSubCategoriesBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    // Init toolbar
    initToolbar();

    // Show subcategories
    showSubCategories();

    // Apply insets
    AppUtils.handleWindowInsets(SelectSubCategories.this, binding.main);

  }

  /**
   * Initialize toolbar with subtitle.
   */
  private void initToolbar() {
    Intent intent = getIntent();
    if (intent != null) {
      if (intent.hasExtra("service_category") && intent.getStringExtra("service_category") != null) {
        String serviceCategory = intent.getStringExtra("service_category");
        AppUtils.initToolbarWithSubtitle(this, binding.toolbar.getRoot(), "Select Subcategory", "Service: " + serviceCategory, true);
      }
    }
  }

  /**
   * Display service subcategories.
   */
  private void showSubCategories() {
    AdapterServiceSubCategories adapter = new AdapterServiceSubCategories(AppUtils.getServiceSubCategories());
    adapter.setServiceClickListener(new AdapterServiceSubCategories.OnItemClickListener() {
      @Override
      public void onItemClick(ServiceSubCategory serviceSubCategory) {
        showBookServiceDialog(serviceSubCategory.getName());
      }
    });
    binding.recyclerViewSubCategories.setAdapter(adapter);
    binding.recyclerViewSubCategories.setLayoutManager(new LinearLayoutManager(SelectSubCategories.this, LinearLayoutManager.VERTICAL, false));
    binding.recyclerViewSubCategories.setHasFixedSize(true);
  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }

  /**
   * Show the book service bottom sheet dialog.
   *
   * @param subCategory The selected subcategory
   */
  private void showBookServiceDialog(String subCategory) {
    BottomSheetBookService bottomSheetBookService = new BottomSheetBookService();
    bottomSheetBookService.setSubcategory(subCategory);
    bottomSheetBookService.show(getSupportFragmentManager(), "BottomSheetBookService");
  }

}