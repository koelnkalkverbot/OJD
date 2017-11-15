package com.jwangenheim.ojd.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class OtherDish extends BaseMeal
{
  private String type;

  public MealType getType()
  {
    return MealType.valueOf(type.toUpperCase());
  }
}
