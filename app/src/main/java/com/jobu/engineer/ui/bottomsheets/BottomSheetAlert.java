package com.jobu.engineer.ui.bottomsheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jobu.engineer.R;
import com.jobu.engineer.common.AlertType;
import com.jobu.engineer.databinding.BottomsheetAlertBinding;
import lombok.Setter;

/**
 * Bottom sheet dialog for displaying alerts to the user.
 * It can show either a success or error alert based on the provided AlertType.
 */
public class BottomSheetAlert extends BottomSheetDialogFragment {
  @Setter
  private AlertType alertType;
  @Setter
  private String title;
  @Setter
  private String message;
  @Setter
  private OnAlertBottomSheetClose onAlertBottomSheetClose;

  /**
   * Constructor for BottomSheetAlert.
   */
  public BottomSheetAlert() {
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    BottomsheetAlertBinding binding = BottomsheetAlertBinding.inflate(inflater, container, false);
    binding.txtTitle.setText(title);
    binding.txtMessage.setText(message);
    binding.btnClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (onAlertBottomSheetClose != null) {
          onAlertBottomSheetClose.onClose();
        }
        dismiss();
      }
    });

    if (alertType == AlertType.SUCCESS) {
      binding.animationView.setAnimation(R.raw.success);
      binding.btnClose.setBackgroundColor(getResources().getColor(R.color.success));
      binding.txtTitle.setTextColor(getResources().getColor(R.color.success));
    } else if (alertType == AlertType.ERROR) {
      binding.animationView.setAnimation(R.raw.fail);
      binding.btnClose.setBackgroundColor(getResources().getColor(R.color.error));
      binding.txtTitle.setTextColor(getResources().getColor(R.color.error));
    }

    setCancelable(false);

    return binding.getRoot();
  }

  /**
   * This interface represents the on alert bottom sheet close listener.
   */
  public interface OnAlertBottomSheetClose {
    void onClose();
  }

}
