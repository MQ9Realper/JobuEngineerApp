package com.jobu.engineer.data.models.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the user profile information, including id, name, email, and role. It is used to transfer user profile data within the application.
 */
@Getter
@Setter
public class UserProfile {
  private int id;
  private String name;
  private String email;
  private String role;
  private boolean available;
  private String specialization;
  private int experienceYears;
  private int hourlyRate;
}
