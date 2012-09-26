package ru.n5g.birthdays.note_book.client.presenter;

import java.util.List;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.client.factory.ContactListFactory;
import ru.n5g.birthdays.note_book.client.view.ContactListViewImpl;

public class ContactListPresenter {
  public interface ContactView extends IsWidget {
    void onRefresh();
  }

  private ContactListFactory factory;
  private ContactView view;

  public ContactListPresenter(ContactListFactory factory) {
    this.factory = factory;
  }


  public IsWidget start() {
    if (view == null) {
      view = new ContactListViewImpl(this, factory.getLocalization());
    }
    return view;
  }

  public ListStore loadContactList() {
    RpcProxy<BasePagingLoadResult<ContactDTO>> proxy = new RpcProxy<BasePagingLoadResult<ContactDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<ContactDTO>> listAsyncCallback) {
        factory.getService().loadContactList((BasePagingLoadConfig) loadConfig, listAsyncCallback);
      }
    };
    PagingLoader<PagingLoadResult<ContactDTO>> loader;
    loader = new BasePagingLoader<PagingLoadResult<ContactDTO>>(proxy, new ModelReader());

    return new ListStore<ContactDTO>(loader);
  }

  public void onAddContact() {
    factory.getContactEditFactory().getPresenter().addContact(new ContactEditPresenter.IsSave() {
      @Override
      public void onSuccess() {
        view.onRefresh();
      }
    });
  }

  public void onEditContact(ContactDTO dto) {
    factory.getContactEditFactory().getPresenter().editContact(dto, new ContactEditPresenter.IsSave() {
      @Override
      public void onSuccess() {
        view.onRefresh();
      }
    });
  }

  public void onDeleteContact(List<ContactDTO> dtoList) {
   factory.getService().deleteContact(dtoList, new AsyncCallback<Void>() {
     public void onFailure(Throwable caught) {
       Info.display("Error", caught.getMessage());
     }

     public void onSuccess(Void result) {
       view.onRefresh();
       Info.display(factory.getLocalization().information(), factory.getLocalization().deleteSuccess());
     }
   });
  }

}
