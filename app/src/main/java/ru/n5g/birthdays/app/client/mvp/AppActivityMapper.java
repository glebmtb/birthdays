package ru.n5g.birthdays.app.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

  private AppClientFactory factory;


  public AppActivityMapper(AppClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public Activity getActivity(Place place) {

    return null;
  }
}
