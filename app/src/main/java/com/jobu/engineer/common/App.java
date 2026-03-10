package com.jobu.engineer.common;

import android.app.Application;
import com.jobu.engineer.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * This class is the custom Application class for the Jobu Customer app. It initializes the Google Places API when the application is created.
 */
public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Realm
    Realm.init(this);

    // Configure Realm database (optional but recommended)
    RealmConfiguration config = new RealmConfiguration.Builder()
        .name(getString(R.string.app_name) + ".realm")
        .schemaVersion(1)
        .allowWritesOnUiThread(true)
        .build();
    Realm.setDefaultConfiguration(config);

  }
}
