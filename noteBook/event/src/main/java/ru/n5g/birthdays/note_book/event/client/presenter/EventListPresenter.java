package ru.n5g.birthdays.note_book.event.client.presenter;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.note_book.event.client.factory.EventListFactory;
import ru.n5g.birthdays.note_book.event.client.view.EventListView;
import ru.n5g.birthdays.note_book.event.shared.bean.EventListDTO;

public class EventListPresenter {
  private EventListFactory factory;
  private View view;

  public EventListPresenter(EventListFactory factory) {
    this.factory = factory;
  }

  public View start() {
    if (view == null) {
      view = new EventListView(this, factory.getLocalization());
    }
    return view;
  }

  public ListStore loadList() {
    RpcProxy<BasePagingLoadResult<EventListDTO>> proxy = new RpcProxy<BasePagingLoadResult<EventListDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<EventListDTO>> listAsyncCallback) {
        factory.getService().loadList((BasePagingLoadConfig) loadConfig, listAsyncCallback);
      }
    };
    PagingLoader<PagingLoadResult<EventListDTO>> loader;
    loader = new BasePagingLoader<PagingLoadResult<EventListDTO>>(proxy, new ModelReader());

    return new ListStore<EventListDTO>(loader);
  }

  public interface View extends IsWidget {
    void onRefresh();
  }
}
