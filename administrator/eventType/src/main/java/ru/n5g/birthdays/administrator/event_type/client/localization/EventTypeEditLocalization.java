package ru.n5g.birthdays.administrator.event_type.client.localization;

import com.google.gwt.i18n.client.Constants;
import ru.n5g.birthdays.components.client.localization.SimpleWindowLocalization;

/**
 * @author belyaev
 */
public interface EventTypeEditLocalization extends Constants, SimpleWindowLocalization {
  String getAddTitle();

  String information();

  String saveSuccess();

  String emptyContact();

  String titleError();

  String getEditTitle();

  String getLabelEdit();

  String eventTypeName();
}
