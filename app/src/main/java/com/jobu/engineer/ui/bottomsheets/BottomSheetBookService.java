package com.jobu.engineer.ui.bottomsheets;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jobu.engineer.common.AppUtils;
import com.jobu.engineer.databinding.BottomsheetBookServiceBinding;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Bottom sheet dialog for booking a service.
 */
public class BottomSheetBookService extends BottomSheetDialogFragment {
  private String selectedSubcategory;
  private BottomsheetBookServiceBinding binding;

  /**
   * Constructor for BottomSheetBookService.
   */
  public BottomSheetBookService() {
    // Required empty public constructor
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = BottomsheetBookServiceBinding.inflate(inflater, container, false);
    binding.layoutRequestServiceSubcategory.serviceSubCategoryEditText.setText(selectedSubcategory);
    binding.layoutRequestServicePreferredDate.getRoot().setOnClickListener(this::chooseBookingDate);
    binding.layoutRequestServicePreferredTime.getRoot().setOnClickListener(this::chooseBookingTime);

    binding.btnSubmitServiceRequest.setOnClickListener(v -> dismiss());

    binding.iconClose.setOnClickListener(v -> dismiss());

    setCancelable(false);

    return binding.getRoot();
  }

  /**
   * Sets the selected subcategory.
   *
   * @param selectedSubcategory The selected subcategory.
   */
  public void setSubcategory(String selectedSubcategory) {
    this.selectedSubcategory = selectedSubcategory;
  }

  /**
   * Interface for submitting service requests.
   */
  public interface OnSubmitServiceRequestListener {

    /**
     * Called when a service request is submitted.
     *
     * @param serviceDescription The description of the service.
     * @param date               The requested date.
     * @param time               The requested time.
     */
    void onSubmitServiceRequest(String serviceDescription, String date, String time);
  }

  /**
   * This method is called when the booking time button is clicked.
   * It opens a TimePickerDialog to select the trip time.
   *
   * @param view the view that was clicked
   */
  private void chooseBookingTime(View view) {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    TimePickerDialog timePickerDialog = new TimePickerDialog(
        requireContext(),
        (timePicker, selectedHour, selectedMinute) -> {
          Calendar now = Calendar.getInstance();
          Calendar selectedTime = Calendar.getInstance();
          selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
          selectedTime.set(Calendar.MINUTE, selectedMinute);

          if (selectedTime.before(now)) {
            Toast.makeText(requireContext(), "Please select a future time.", Toast.LENGTH_LONG).show();
            return;
          }

          SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
          String formattedTime = sdf.format(selectedTime.getTime());

          binding.layoutRequestServicePreferredTime.serviceTimeEditText.setText(formattedTime);

        },
        hour,
        minute,
        false
    );

    timePickerDialog.show();
  }

  /**
   * This method is called when the booking date button is clicked.
   * It opens a DatePickerDialog to select the trip date.
   *
   * @param view the view that was clicked
   */
  public void chooseBookingDate(View view) {
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (datePicker, year, month, dayOfMonth) -> {
      Calendar selectedCalendar = Calendar.getInstance();
      selectedCalendar.set(year, month, dayOfMonth);
      String selectedDate = AppUtils.formatCalendarDate(selectedCalendar.getTime());
      binding.layoutRequestServicePreferredDate.serviceDateEditText.setText(selectedDate);
    },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    );

    // Prevent past dates
    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

    datePickerDialog.show();
  }
}
