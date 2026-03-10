package com.jobu.engineer.data.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This is the Job dto.
 * It contains the service name, location, and requested time.
 */
@Getter
@Setter
public class Job {
  private String serviceName;
  private String location;
  private String requestedTime;
}
