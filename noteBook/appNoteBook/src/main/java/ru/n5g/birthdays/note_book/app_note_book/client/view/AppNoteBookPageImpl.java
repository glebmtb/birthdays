package ru.n5g.birthdays.note_book.app_note_book.client.view;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.n5g.birthdays.note_book.app_note_book.client.bean.TabEnum;
import ru.n5g.birthdays.note_book.app_note_book.client.localization.AppNoteBookLocalization;
import ru.n5g.birthdays.note_book.app_note_book.client.presenter.AppNoteBookPresenter;


public class AppNoteBookPageImpl extends ContentPanel implements AppNoteBookPage {
  private AppNoteBookLocalization localization;
  private Presenter presenter;
  private OneWidgetContentPanel appContainer;
  private TabPanel toolBar;

  private TabItem btnContact;
  private TabItem btnEventType;
  private TabItem btnLogout;

  private TabEnum selectedTab;

  public AppNoteBookPageImpl(AppNoteBookLocalization localization) {
    super();
    this.localization = localization;

    setHeaderVisible(false);
    setLayout(new FitLayout());

    initToolbar();

    ContentPanel panel = new ContentPanel(new FitLayout());
    panel.setHeaderVisible(false);
    panel.setBorders(false);

    appContainer = new OneWidgetContentPanel();
    panel.setTopComponent(toolBar);
    panel.add(appContainer);
    add(panel);
  }

  public void initToolbar() {
    selectedTab = TabEnum.CONTACT;
    toolBar = new TabPanel();
    toolBar.setBorders(false);
    toolBar.setBodyBorder(false);
    toolBar.setBorderStyle(false);
    toolBar.setBorders(false);
    toolBar.setAutoSelect(false);

    btnLogout = new TabItem();
    btnLogout.setText(localization.btnLogout());
    btnLogout.setBorders(true);
    btnLogout.addListener(Events.Select, new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent be) {
        TabItem selected = toolBar.getSelectedItem();
        onLogoutClick(selected);
      }
    });

    btnContact = new TabItem();
    btnContact.setToolTip(localization.btnContact());
    btnContact.setText(localization.btnContact());
    btnContact.setBorders(true);
    btnContact.addListener(Events.Select, new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent be) {
        presenter.openContact();
      }
    });

    btnEventType = new TabItem();
    btnEventType.setToolTip(localization.btnEventType());
    btnEventType.setText(localization.btnEventType());
    btnEventType.setBorders(true);
    btnEventType.addListener(Events.Select, new Listener<ComponentEvent>() {
      public void handleEvent(ComponentEvent be) {
        presenter.openEventType();
      }
    });

    btnLogout.setHeight(0);
    btnContact.setHeight(0);
    btnEventType.setHeight(0);


    btnLogout.setBorders(false);
    btnContact.setBorders(false);
    btnEventType.setBorders(false);


    toolBar.add(btnEventType);
    toolBar.add(btnContact);
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
  public void setPresenter(AppNoteBookPresenter appPresenter) {
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
  }

  protected void chooseTab() {
    switch (selectedTab) {
      case CONTACT:
        toolBar.setSelection(btnContact);
        break;
      case EVENT:
        toolBar.setSelection(btnEventType);
        break;
      default:
        toolBar.setSelection(btnContact);
        break;
    }
  }
}
