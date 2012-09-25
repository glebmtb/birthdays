package ru.n5g.birthdays.note_book.client.presenter;

import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.components.client.view.SimpleWindowView;
import ru.n5g.birthdays.note_book.client.factory.ContactEditFactory;
import ru.n5g.birthdays.note_book.client.view.ContactEditWindowImplImpl;

/**
 * @author belyaev
 */
public class ContactEditPresenter extends SimpleWindowPresenter{
  private ContactEditFactory factory;
  private ContactEditWindow window;

  public ContactEditPresenter(ContactEditFactory factory) {
    super(factory.getLocalization());
    this.factory = factory;
  }

  public void addContact() {
    window = new ContactEditWindowImplImpl(this, factory.getLocalization());
    window.show();

  }

  public interface ContactEditWindow extends SimpleWindowView {

  }
}
