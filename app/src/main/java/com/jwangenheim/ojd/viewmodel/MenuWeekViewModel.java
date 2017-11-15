package com.jwangenheim.ojd.viewmodel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jwangenheim.ojd.model.MenuWeek;

import org.joda.time.DateTime;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

public class MenuWeekViewModel extends ViewModel
{
  private MutableLiveData<MenuWeek> menuWeekLiveData;

  @Inject
  public MenuWeekViewModel() {
  }

  public MutableLiveData<MenuWeek> getMenuWeek() {

    if (menuWeekLiveData == null) {
      menuWeekLiveData = new MutableLiveData<>();

      final DateTime now = new DateTime();
      final int weekOfYear = now.getWeekOfWeekyear();
      loadMenuForWeek(21);
    }

    return menuWeekLiveData;
  }

  private void loadMenuForWeek(final int weekOfYear) {
    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("weeks");

    Query query = reference.orderByChild("week").equalTo(weekOfYear);
    query.addListenerForSingleValueEvent(new ValueEventListener()
    {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot)
      {
        if (dataSnapshot.exists())
        {
          for (DataSnapshot snap : dataSnapshot.getChildren())
          {
            menuWeekLiveData.setValue(snap.getValue(MenuWeek.class));
          }
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        Log.e(getClass().getSimpleName(), "ERROR: " + databaseError.getDetails());
      }
    });
  }
}
