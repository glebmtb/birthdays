package ru.n5g.birthdays.administrator.event_type.client.presenter;

import java.util.List;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.administrator.event_type.client.factory.EventTypeListFactory;
import ru.n5g.birthdays.administrator.event_type.client.view.EventTypeListViewImpl;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

public class EventTypeListPresenter {
  public interface View extends IsWidget {
    void onRefresh();
  }

  private EventTypeListFactory factory;
  private View view;

  public EventTypeListPresenter(EventTypeListFactory factory) {
    this.factory = factory;
  }


  public IsWidget start() {
    if (view == null) {
      view = new EventTypeListViewImpl(this, factory.getLocalization());
    }
    return view;
  }

  public ListStore loadContactList() {
    RpcProxy<BasePagingLoadResult<EventTypeDTO>> proxy = new RpcProxy<BasePagingLoadResult<EventTypeDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<EventTypeDTO>> listAsyncCallback) {
        factory.getService().loadContactList((BasePagingLoadConfig) loadConfig, listAsyncCallback);
      }
    };
    PagingLoader<PagingLoadResult<EventTypeDTO>> loader;
    loader = new BasePagingLoader<PagingLoadResult<EventTypeDTO>>(proxy, new ModelReader());

    return new ListStore<EventTypeDTO>(loader);
  }

  public void onAddContact() {
    factory.getContactEditFactory().getPresenter().addContact(new EventTypeEditPresenter.IsSave() {
      @Override
      public void onSuccess() {
        view.onRefresh();
      }
    });
  }

  public void onEditContact(EventTypeDTO dto) {
    factory.getContactEditFactory().getPresenter().editContact(dto, new EventTypeEditPresenter.IsSave() {
      @Override
      public void onSuccess() {
        view.onRefresh();
      }
    });
  }

  public void onDeleteContact(List<EventTypeDTO> dtoList) {
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
