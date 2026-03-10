package com.jobu.engineer.ui.bottomsheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jobu.customer.databinding.BottomsheetConfirmBinding;
import lombok.Setter;

/**
 * Bottom sheet dialog for displaying alerts to the user.
 * It can show either a success or error alert based on the provided AlertType.
 */
public class BottomSheetConfirm extends BottomSheetDialogFragment {
  @Setter
  private String title;
  @Setter
  private String message;
  @Setter
  private OnAlertBottomSheetConfirm onAlertBottomSheetConfirm;

  /**
   * Constructor for BottomSheetAlert.
   */
  public BottomSheetConfirm() {
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    BottomsheetConfirmBinding binding = BottomsheetConfirmBinding.inflate(inflater, container, false);
    binding.txtTitle.setText(title);
    binding.txtMessage.setText(message);
    binding.btnCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
      }
    });

    binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (onAlertBottomSheetConfirm != null) {
          onAlertBottomSheetConfirm.onConfirm();
        }
        dismiss();
      }
    });

    setCancelable(false);

    return binding.getRoot();
  }

  /**
   * This interface represents the on alert bottom sheet confirm listener.
   */
  public interface OnAlertBottomSheetConfirm {
    void onConfirm();
  }

}
