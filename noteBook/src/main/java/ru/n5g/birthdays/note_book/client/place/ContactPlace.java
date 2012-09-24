package ru.n5g.birthdays.note_book.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author home
 */
public class ContactPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<ContactPlace> {
    @Override
    public ContactPlace getPlace(String token) {
      return new ContactPlace();
    }

    @Override
    public String getToken(ContactPlace place) {
      return "";
    }
  }
}
