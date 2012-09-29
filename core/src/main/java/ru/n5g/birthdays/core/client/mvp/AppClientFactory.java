package ru.n5g.birthdays.core.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;

/**
 * ClientFactory приложения
 */
public interface AppClientFactory {
  /**
   * Получить EventBus
   * @return
   */
  EventBus getEventBus();

  /**
   * Получить PlaceController
   * @return
   */
  PlaceController getPlaceController();

  /**
   * Получить название приложения (для отображения в заголовке страницы браузера)
   * @return
   */
  String getAppTitle();

  /**
   * Получить дефолтное место приложения (например десктоп)
   * @return
   */
  Place getDefaultPlace();

}
