package ru.n5g.birthdays.administrator.client.place;


import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AdministratorPlace extends Place {
  public static class Tokenizer implements PlaceTokenizer<AdministratorPlace> {
    @Override
    public AdministratorPlace getPlace(String token) {
      return new AdministratorPlace();
    }

    @Override
    public String getToken(AdministratorPlace place) {
      return "";
    }
  }
}
