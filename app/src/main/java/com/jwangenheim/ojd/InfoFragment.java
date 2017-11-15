package com.jwangenheim.ojd;


import com.jwangenheim.ojd.databinding.FragmentInfoBinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class InfoFragment extends Fragment
{
  FragmentInfoBinding binding;

  public static android.support.v4.app.Fragment newInstance() {
    return new InfoFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
    ButterKnife.bind(this, binding.getRoot());
    return binding.getRoot();
  }
}
