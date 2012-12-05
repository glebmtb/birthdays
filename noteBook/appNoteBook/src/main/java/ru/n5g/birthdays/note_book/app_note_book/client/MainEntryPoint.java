package ru.n5g.birthdays.note_book.app_note_book.client;

import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import ru.n5g.birthdays.note_book.app_note_book.client.factory.AppNoteBookClientFactory;
import ru.n5g.birthdays.note_book.app_note_book.client.mvp.AppNoteBookActivityMapper;
import ru.n5g.birthdays.note_book.app_note_book.client.mvp.AppNoteBookPlaceHistoryMapper;
import ru.n5g.birthdays.note_book.app_note_book.client.mvp.AppNoteBookTokenizerFactory;
import ru.n5g.birthdays.note_book.app_note_book.client.presenter.AppNoteBookPresenter;
import ru.n5g.birthdays.note_book.app_note_book.client.view.AppNoteBookPage;

public class MainEntryPoint implements com.google.gwt.core.client.EntryPoint {

  public void onModuleLoad() {
    AppNoteBookClientFactory clientFactory = GWT.create(AppNoteBookClientFactory.class);
    EventBus eventBus = clientFactory.getEventBus();
    PlaceController placeController = clientFactory.getPlaceController();

    AppNoteBookPage.Presenter presenter = new AppNoteBookPresenter(clientFactory);
    AppNoteBookPage appPage = presenter.start();

    // Start ActivityManager for the main widget with our ActivityMapper
    ActivityMapper activityMapper = new AppNoteBookActivityMapper(clientFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(appPage.getWidgetContainer());

    // Start PlaceHistoryHandler with our PlaceHistoryMapper
    AppNoteBookPlaceHistoryMapper historyMapper = GWT.create(AppNoteBookPlaceHistoryMapper.class);
    historyMapper.setFactory(new AppNoteBookTokenizerFactory());
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, clientFactory.getDefaultPlace());

    Viewport v = new Viewport();
    v.setLayout(new FitLayout());
    v.add(appPage.asWidget());

    RootPanel.get().add(v);
    // Goes to place represented on URL or default place
    historyHandler.handleCurrentHistory();
  }
}
