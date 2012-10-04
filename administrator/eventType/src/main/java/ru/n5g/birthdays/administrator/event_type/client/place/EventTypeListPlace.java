package ru.n5g.birthdays.administrator.event_type.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author home
 */
public class EventTypeListPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<EventTypeListPlace> {
    @Override
    public EventTypeListPlace getPlace(String token) {
      return new EventTypeListPlace();
    }

    @Override
    public String getToken(EventTypeListPlace place) {
      return "";
    }
  }
}
