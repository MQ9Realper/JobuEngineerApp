package com.jobu.engineer.data.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This is the dto for the JobHistory.
 */
@Setter
@Getter
public class JobHistory {
  private String serviceName;
  private String customerName;
  private String location;
  private String date;
  private String status;
}
