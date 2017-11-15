package com.jwangenheim.ojd.model;

import java.io.Serializable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MenuDay implements Serializable
{
  boolean closed;
  String veggi;
  String meat;

  public MenuDay() {

  }

  public boolean isClosed()
  {
    return closed;
  }

  public String getVeggi()
  {
    return veggi;
  }

  public String getMeat()
  {
    return meat;
  }
}
