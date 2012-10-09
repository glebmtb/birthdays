package ru.n5g.birthdays.note_book.contact.client.presenter;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.components.client.view.SimpleWindowView;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.contact.client.factory.ContactEditFactory;
import ru.n5g.birthdays.note_book.contact.client.view.ContactEditWindowImplImpl;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactEditDTO;

/**
 * @author belyaev
 */
public class ContactEditPresenter extends SimpleWindowPresenter {
  private ContactEditFactory factory;
  private ContactEditWindow window;
  private IsSave isSave;

  public ContactEditPresenter(ContactEditFactory factory) {
    super(factory.getLocalization());
    this.factory = factory;
  }

  public void addContact(IsSave isSave) {
    this.isSave = isSave;
    openWindow(ActionEnum.ADD, new ContactEditDTO());
  }

  public void editContact(Long idContact, IsSave isSave) {
    this.isSave = isSave;
    final MessageBox mes = MessageBox.info("Инфо", "Открытие данных", null);
    mes.getDialog().mask(factory.getLocalization().loadWindow());
    factory.getService().getContact(idContact, new AsyncCallback<ContactEditDTO>() {
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
        mes.getDialog().unmask();
        mes.hide();
      }

      public void onSuccess(ContactEditDTO result) {
        openWindow(ActionEnum.EDIT, result);
        mes.getDialog().unmask();
        mes.hide();
      }
    });
  }

  private void openWindow(ActionEnum action, ContactEditDTO dto) {
    if (action == null || dto == null) {
      Info.display(factory.getLocalization().titleError(), factory.getLocalization().emptyContact());
    }
    window = new ContactEditWindowImplImpl(this, factory.getLocalization(), action, dto);
    window.show();
  }

  public void save(ContactEditDTO dto) {
    window.mask(factory.getLocalization().saveDate());
    factory.getService().saveContact(dto, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
        window.unmask();
      }

      public void onSuccess(Void result) {
        Info.display(factory.getLocalization().information(), factory.getLocalization().saveSuccess());
        isSave.onSuccess();
        window.unmask();
        window.hide();
      }
    });
  }

  public ListStore<EventTypeDTO> getEventTypeComboBoxStore() {
    RpcProxy<BasePagingLoadResult<EventTypeDTO>> proxy = new RpcProxy<BasePagingLoadResult<EventTypeDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<EventTypeDTO>> callback) {
        factory.getService().loadEventTypeList((BasePagingLoadConfig) loadConfig, callback);
      }
    };
    PagingLoader<PagingLoadResult<EventTypeDTO>> loader = new BasePagingLoader<PagingLoadResult<EventTypeDTO>>(proxy, new ModelReader());
    loader.setSortDir(Style.SortDir.ASC);
    loader.setSortField(EventTypeDTO.NAME);
    loader.setRemoteSort(true);
    return new ListStore<EventTypeDTO>(loader);
  }

  public interface ContactEditWindow extends SimpleWindowView {
    El mask(String message);

    void unmask();

    void hide();
  }

  public interface IsSave {
    void onSuccess();
  }
}
