package ru.n5g.birthdays.note_book.event.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author home
 */
public class EventListPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<EventListPlace> {
    @Override
    public EventListPlace getPlace(String token) {
      return new EventListPlace();
    }

    @Override
    public String getToken(EventListPlace place) {
      return "";
    }
  }
}
