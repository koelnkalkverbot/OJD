package com.jwangenheim.ojd.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class BaseMeal
{
  private String description;
  private boolean isVeggie;
  private boolean isVegan;
  private String price;

  public BaseMeal() {
    // Default constructor required for calls to DataSnapshot.getValue(BaseMeal.class)
  }

  public String getDescription()
  {
    return description;
  }

  public boolean isVeggie()
  {
    return isVeggie;
  }

  public boolean isVegan()
  {
    return isVegan;
  }

  public String getPrice()
  {
    return price;
  }

  public enum MealType
  {
    SOUP("soup"),
    CURRY("curry"),
    SALAD("salad"),
    QUICHE("quiche"),
    SWEETS("sweets");


    MealType(final String s)
    {
    }
  }
}
