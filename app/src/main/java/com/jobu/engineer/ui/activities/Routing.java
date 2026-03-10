package com.jobu.engineer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

/**
 * Routing Activity.
 */
public class Routing extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    SplashScreen.installSplashScreen(this);
    super.onCreate(savedInstanceState);

    startActivity(new Intent(this, LoginWithEmail.class));
    finish();

  }

}