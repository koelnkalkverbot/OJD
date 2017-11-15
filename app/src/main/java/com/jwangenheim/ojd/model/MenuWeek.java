package com.jwangenheim.ojd.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class MenuWeek
{
  public int week;
  public List<OtherDish> other_dishes;
  public List<MenuDay> days;
  public List<Sweet> sweets;

  public int getWeek()
  {
    return week;
  }

  public List<OtherDish> getOtherDishes()
  {
    return other_dishes;
  }

  public List<MenuDay> getDays()
  {
    return days;
  }

  public List<Sweet> getSweets()
  {
    return sweets;
  }
}
