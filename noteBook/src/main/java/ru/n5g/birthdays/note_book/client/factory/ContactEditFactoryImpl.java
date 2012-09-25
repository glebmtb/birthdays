package ru.n5g.birthdays.note_book.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.note_book.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactEditPresenter;

/**
 * @author belyaev
 */
public class ContactEditFactoryImpl implements ContactEditFactory {
  private final ContactEditLocalization localization = GWT.create(ContactEditLocalization.class);
  private ContactEditPresenter presenter;

  @Override
  public ContactEditLocalization getLocalization() {
    return localization;
  }

  @Override
  public ContactEditPresenter getPresenter() {
    if (presenter == null)
      presenter = new ContactEditPresenter(this);
    return presenter;
  }
}
