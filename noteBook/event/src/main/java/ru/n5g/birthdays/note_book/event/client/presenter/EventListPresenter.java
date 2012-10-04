package ru.n5g.birthdays.note_book.event.client.presenter;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.core.shared.bean.EventDTO;
import ru.n5g.birthdays.note_book.event.client.factory.EventListFactory;
import ru.n5g.birthdays.note_book.event.client.view.EventView;

public class EventListPresenter {
  private EventListFactory factory;
  private View view;

  public EventListPresenter(EventListFactory factory) {
    this.factory = factory;
  }

  public View start() {
    if (view == null) {
      view = new EventView(this, factory.getLocalization());
    }
    return view;
  }

  public ListStore loadList() {
    RpcProxy<BasePagingLoadResult<EventDTO>> proxy = new RpcProxy<BasePagingLoadResult<EventDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<EventDTO>> listAsyncCallback) {
        factory.getService().loadList((BasePagingLoadConfig) loadConfig, listAsyncCallback);
      }
    };
    PagingLoader<PagingLoadResult<EventDTO>> loader;
    loader = new BasePagingLoader<PagingLoadResult<EventDTO>>(proxy, new ModelReader());

    return new ListStore<EventDTO>(loader);
  }

  public interface View extends IsWidget {
    void onRefresh();
  }
}
