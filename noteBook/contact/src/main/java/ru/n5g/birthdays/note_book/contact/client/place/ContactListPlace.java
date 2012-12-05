package ru.n5g.birthdays.note_book.contact.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author home
 */
public class ContactListPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<ContactListPlace> {
    @Override
    public ContactListPlace getPlace(String token) {
      return new ContactListPlace();
    }

    @Override
    public String getToken(ContactListPlace place) {
      return "";
    }
  }
}
