package ru.n5g.birthdays.administrator.app_admin.client;

import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import ru.n5g.birthdays.administrator.app_admin.client.factory.AppAdminClientFactory;
import ru.n5g.birthdays.administrator.app_admin.client.mvp.AppActivityMapper;
import ru.n5g.birthdays.administrator.app_admin.client.mvp.AppPlaceHistoryMapper;
import ru.n5g.birthdays.administrator.app_admin.client.mvp.AppTokenizerFactory;
import ru.n5g.birthdays.administrator.app_admin.client.presenter.AppAdminPresenter;
import ru.n5g.birthdays.administrator.app_admin.client.view.AppAdminPage;

public class MainEntryPoint implements com.google.gwt.core.client.EntryPoint {

  public void onModuleLoad() {
    AppAdminClientFactory clientFactory = GWT.create(AppAdminClientFactory.class);
    EventBus eventBus = clientFactory.getEventBus();
    PlaceController placeController = clientFactory.getPlaceController();

    AppAdminPage.Presenter presenter = new AppAdminPresenter(clientFactory);
    AppAdminPage appPage = presenter.start();

    // Start ActivityManager for the main widget with our ActivityMapper
    ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(appPage.getWidgetContainer());

    // Start PlaceHistoryHandler with our PlaceHistoryMapper
    AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
    historyMapper.setFactory(new AppTokenizerFactory());
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
