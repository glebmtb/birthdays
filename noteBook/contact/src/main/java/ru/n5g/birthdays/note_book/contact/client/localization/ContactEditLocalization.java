package ru.n5g.birthdays.note_book.contact.client.localization;

import com.google.gwt.i18n.client.Constants;
import ru.n5g.birthdays.components.client.localization.SimpleWindowLocalization;

/**
 * @author belyaev
 */
public interface ContactEditLocalization extends Constants, SimpleWindowLocalization {
  String getAddTitle();

  String nickname();

  String lastName();

  String firstName();

  String middleName();

  String comment();

  String information();

  String saveSuccess();

  String emptyContact();

  String titleError();

  String getEditTitle();

  String getLabelEdit();

  String getLabelEditAndClose();

  String contactFiledSet();

  String eventFieldSet();

  String comboBoxLoading();

  String comboBoxInitialization();

  String addEventType();

  String btnClose();

  String eventDate();

  String remind();

  String eventYear();

  String editEvent();

  String deleteEvent();

  String loadWindow();

  String saveDate();
}
