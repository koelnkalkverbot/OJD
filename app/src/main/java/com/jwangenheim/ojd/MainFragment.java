package com.jwangenheim.ojd;

import java.util.ArrayList;

import com.jwangenheim.ojd.databinding.FragmentMainBinding;
import com.jwangenheim.ojd.model.MenuWeek;
import com.jwangenheim.ojd.viewmodel.MenuWeekViewModel;

import org.joda.time.DateTime;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment
{
  private static final int NUM_PAGES = 5;

  @BindView(R.id.co_content) LinearLayout coContent;
  @BindView(R.id.progress) ProgressBar progressBar;
  @BindView(R.id.viewPager) ViewPager viewPager;
  @BindView(R.id.pagerTabStrip) PagerTabStrip pagerTabStrip;
  private PagerAdapter pagerAdapter;
  private FragmentMainBinding binding;

  @Inject
  MenuWeekViewModel menuWeekViewModel;

  public static Fragment newInstance() {
    return new MainFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    ButterKnife.bind(this, binding.getRoot());
    setHasOptionsMenu(true);

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    AppComponent.from(getContext()).inject(this);

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvOtherDishes.getContext(),
        DividerItemDecoration.VERTICAL);
    binding.rvOtherDishes.addItemDecoration(dividerItemDecoration);
    subscribeUi();
  }

  private void subscribeUi()
  {
    menuWeekViewModel.getMenuWeek().observe(this, new Observer<MenuWeek>()
    {
      @Override
      public void onChanged(@Nullable MenuWeek menuWeek)
      {
        if (menuWeek != null)
        {
          progressBar.setVisibility(View.INVISIBLE);
          coContent.setVisibility(View.VISIBLE);
          pagerAdapter = new MenuDayPagerAdapter(getActivity().getSupportFragmentManager());
          viewPager.setPageTransformer(true, new ZoomOutPagerTransformer());
          viewPager.setAdapter(pagerAdapter);
          int currentPos = new DateTime().getDayOfWeek();
          if (currentPos > 4) currentPos = 4;
          viewPager.setCurrentItem(currentPos);
          binding.rvOtherDishes.setAdapter(new OtherDishesAdapter(new ArrayList<>(menuWeek.getOtherDishes())));
        }
      }
    });
  }

  @Override
  public void onResume()
  {
    super.onResume();
    subscribeUi();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.info, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_info:
        getActivity().getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, InfoFragment.newInstance())
            .addToBackStack(InfoFragment.class.getSimpleName())
            .commit();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private class MenuDayPagerAdapter extends FragmentStatePagerAdapter
  {
    MenuDayPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return MenuDayFragment.newInstance(menuWeekViewModel.getMenuWeek().getValue().getDays().get(position), position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return getContext().getResources().getStringArray(R.array.days)[position];
    }

    @Override
    public int getCount() {
      return NUM_PAGES;
    }
  }
}
