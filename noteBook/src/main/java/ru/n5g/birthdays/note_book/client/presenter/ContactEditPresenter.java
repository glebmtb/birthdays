package ru.n5g.birthdays.note_book.client.presenter;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.components.client.view.SimpleWindowView;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
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

  public void save(ContactDTO dto) {
    factory.getService().saveContact(dto, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
      }

      public void onSuccess(Void result) {
        window.hide();
        Info.display(factory.getLocalization().information(), factory.getLocalization().saveSuccess());
      }
    });
  }

  public interface ContactEditWindow extends SimpleWindowView {

    void hide();
  }
}
