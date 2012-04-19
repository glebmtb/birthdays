package ru.n5g.birthdays.app.client.view;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import ru.n5g.birthdays.app.client.localization.AppLocalization;
import ru.n5g.birthdays.app.client.presenter.AppPresenter;
import ru.n5g.birthdays.core.shared.TabEnum;


public class AppPageImpl extends ContentPanel implements AppPage {
  private AppLocalization localization;
  private Presenter presenter;
  private OneWidgetContentPanel appContainer;
  private TabPanel toolBar;

  private LayoutContainer top;
  private Label lblHeader1;
  private Label lblHeader2;
  private Label lblHello;
  private Label lblEmployeeName;

  private TabItem btnAdmin;
  private TabItem btnLogout;

  private TabEnum selectedTab;

  public AppPageImpl(AppLocalization localization) {
    super();
    this.localization = localization;

    setHeaderVisible(false);
    setLayout(new FitLayout());

    initToolbar();

    ContentPanel panel = new ContentPanel(new FitLayout());
    panel.setHeaderVisible(false);
    panel.setBorders(false);

    top = new LayoutContainer(new FlowLayout());
    top.setHeight(40);
    top.addStyleName("top-panel");

    lblHeader1 = new Label();
    lblHeader1.addStyleName("lbl-header-1");
    LayoutContainer left = new LayoutContainer(new FlowLayout(0));
    left.addStyleName("panel-header-left");
    LayoutContainer right = new LayoutContainer(new FlowLayout(0));
    right.addStyleName("panel-header-right");

    lblHeader2 = new Label();
    lblHeader2.addStyleName("lbl-header-2");


    lblHello = new Label();
    lblHello.setText(localization.hello());
    lblHello.addStyleName("lbl-hello");

    lblEmployeeName = new Label();
    lblEmployeeName.addStyleName("lbl-employee-name");

    /*left.add(lblHeader1);
    left.add(lblHeader2);*/
    right.add(lblHello);
    right.add(lblEmployeeName);

    //top.add(left);
    top.add(right);

    //setTopComponent(top);

    appContainer = new OneWidgetContentPanel();
    panel.setTopComponent(toolBar);
    panel.add(appContainer);
    add(panel);
    RootPanel.get().add(top);
  }

  public void initToolbar() {
    selectedTab = TabEnum.ADMINISTRATOR;
    toolBar = new TabPanel();
    toolBar.setBorders(false);
    toolBar.setBodyBorder(false);
    toolBar.setBorderStyle(false);
    toolBar.setBorders(false);
    toolBar.addStyleName("toolbar-tab");
    toolBar.setAutoSelect(false);

    btnLogout = new TabItem();
    btnLogout.addStyleName("btn-menu");
    btnLogout.setText(localization.btnLogout());
    btnLogout.setBorders(true);
    btnLogout.getHeader().addStyleName("btn-logout");
    btnLogout.addListener(Events.Select, new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent be) {
        TabItem selected = toolBar.getSelectedItem();
        onLogoutClick(selected);
      }
    });

    btnAdmin = new TabItem();
    btnAdmin.addStyleName("btn-menu");
    btnAdmin.setToolTip(localization.btnAdmin());
    btnAdmin.setText(localization.btnAdmin());
    btnAdmin.setBorders(true);
    btnAdmin.addListener(Events.Select, new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent be) {
        presenter.openAdministrator();
      }
    });

    btnAdmin.addStyleName("btn-tab");


    btnLogout.setHeight(0);
    btnAdmin.setHeight(0);


    btnLogout.setBorders(false);
    btnAdmin.setBorders(false);


    toolBar.add(btnAdmin);
    toolBar.add(btnLogout);
  }

  private void onLogoutClick(final TabItem selected) {
    MessageBox.confirm(localization.logoutDlgTitle(), localization.logoutConfirm(), new Listener<MessageBoxEvent>() {
      @Override
      public void handleEvent(MessageBoxEvent be) {
        if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
          logout();
        }
        else {
          toolBar.setSelection(selected);
        }
      }
    });
  }

  private void logout() {
    String url = GWT.getHostPageBaseURL();
    if (url.endsWith("/")) {
      url = url.substring(0, url.length() - 1);
    }
    url += "/logout";
    com.google.gwt.user.client.Window.Location.assign(url);
  }

  @Override
  public void setPresenter(AppPresenter appPresenter) {
    this.presenter = appPresenter;
  }

  public AcceptsOneWidget getWidgetContainer() {
    return appContainer;
  }

  @Override
  public void selectTab(TabEnum tab) {
    selectedTab=tab;
    chooseTab();
  }

  @Override
  public void setHeader(String heading1, String heading2) {
    String lblHeader1Text = localization.header().concat(": ");
    if (heading1 != null) {
      lblHeader1Text = lblHeader1Text.concat(heading1).concat(" &gt; ");
    }

    String lblHeader2Text = "";
    if (heading2 != null) {
      lblHeader2Text = heading2;
    }
    lblHeader1.setText(lblHeader1Text);
    lblHeader2.setText(lblHeader2Text);
  }

  protected void chooseTab() {
    switch (selectedTab) {
      case ADMINISTRATOR:
        toolBar.setSelection(btnAdmin);
        break;
      default:
        toolBar.setSelection(btnAdmin);
        break;
    }
  }
}
