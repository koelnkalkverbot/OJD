package com.jwangenheim.ojd;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public abstract class AppComponent {

  public static AppComponent from(@NonNull Context context){
    return ((OJDApp) context.getApplicationContext()).getAppComponent();
  }

  public abstract void inject(MainFragment mainFragment);
}
