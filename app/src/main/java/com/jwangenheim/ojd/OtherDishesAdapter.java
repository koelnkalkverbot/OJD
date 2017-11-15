package com.jwangenheim.ojd;

import java.util.ArrayList;
import java.util.List;

import com.jwangenheim.ojd.databinding.RecyclerItemBinding;
import com.jwangenheim.ojd.model.OtherDish;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class OtherDishesAdapter extends RecyclerView.Adapter<OtherDishesAdapter.OtherDishViewHolder>
{

  private List<OtherDish> otherDishes;

  OtherDishesAdapter(ArrayList<OtherDish> otherDishes) {
    this.otherDishes = otherDishes;
  }

  @Override
  public OtherDishViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    View mealContainer = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
    return new OtherDishViewHolder(mealContainer);
  }

  @Override
  public void onBindViewHolder(OtherDishViewHolder holder, int position)
  {
    OtherDish dish = otherDishes.get(position);
    holder.bindMeal(dish);
  }

  @Override
  public int getItemCount()
  {
    return otherDishes.size();
  }

  static class OtherDishViewHolder extends RecyclerView.ViewHolder {
    private RecyclerItemBinding binding;

    ImageView type;
    TextView meal;
    TextView price;

    OtherDishViewHolder(View v) {
      super(v);
      binding = DataBindingUtil.bind(itemView);
      type = (ImageView) v.findViewById(R.id.iv_type);
      meal = (TextView) v.findViewById(R.id.tv_meal);
      price = (TextView) v.findViewById(R.id.tv_price);
    }

    void bindMeal(OtherDish otherDish) {
      binding.setDish(otherDish);
    }
  }
}
