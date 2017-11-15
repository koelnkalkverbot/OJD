package com.jwangenheim.ojd;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

// https://github.com/florent37/NewAndroidArchitecture-Component-Github

public class OJDApp extends Application
{

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    JodaTimeAndroid.init(this);

    this.appComponent = DaggerAppComponent.builder().build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
