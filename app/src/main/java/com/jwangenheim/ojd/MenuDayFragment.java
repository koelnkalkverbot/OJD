package com.jwangenheim.ojd;

import com.jwangenheim.ojd.model.MenuDay;
import com.jwangenheim.ojd.model.MenuWeek;
import com.jwangenheim.ojd.viewmodel.MenuWeekViewModel;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDayFragment extends LifecycleFragment
{
  @BindView(R.id.tv_main_dish_meat) TextView mainDishMeat;
  @BindView(R.id.tv_main_dish_veggi) TextView mainDishVeggi;
  @BindView(R.id.iv_closed) ImageView closed;
  @BindView(R.id.co_content) LinearLayout content;

  MenuDay menuDay;
  int position;

  public static MenuDayFragment newInstance(final MenuDay menuDay, final int position) {
    MenuDayFragment fragment = new MenuDayFragment();

    Bundle args = new Bundle();
    args.putInt("position", position);
    args.putSerializable("menuDay", menuDay);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.menu_day, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);

    this.menuDay = (MenuDay) getArguments().getSerializable("menuDay");
    this.position = getArguments().getInt("position");

    if (menuDay.isClosed())
    {
      content.setVisibility(View.INVISIBLE);
      closed.setVisibility(View.VISIBLE);
    }
    else
    {
      mainDishMeat.setText(menuDay.getMeat());
      mainDishVeggi.setText(menuDay.getVeggi());
      content.setVisibility(View.VISIBLE);
    }
  }
}
