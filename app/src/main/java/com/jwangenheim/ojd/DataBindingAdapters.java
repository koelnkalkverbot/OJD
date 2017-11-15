package com.jwangenheim.ojd;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import static com.jwangenheim.ojd.model.BaseMeal.MealType;

public class DataBindingAdapters
{
  @BindingAdapter("android:src")
  public static void setImage(ImageView imageView, MealType type) {
    switch(type)
    {
      case SOUP:
        imageView.setImageResource(R.drawable.ic_soup);
        break;
      case CURRY:
        imageView.setImageResource(R.drawable.ic_curry);
        break;
      case SALAD:
        imageView.setImageResource(R.drawable.ic_salad);
        break;
      case QUICHE:
        imageView.setImageResource(R.drawable.ic_quiche);
        break;
      case SWEETS:
        imageView.setImageResource(R.drawable.ic_sweets);
        break;
    }
  }
}
