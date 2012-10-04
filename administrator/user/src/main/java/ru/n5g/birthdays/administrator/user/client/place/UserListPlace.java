package ru.n5g.birthdays.administrator.user.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * @author home
 */
public class UserListPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<UserListPlace> {
    @Override
    public UserListPlace getPlace(String token) {
      return new UserListPlace();
    }

    @Override
    public String getToken(UserListPlace place) {
      return "";
    }
  }
}
