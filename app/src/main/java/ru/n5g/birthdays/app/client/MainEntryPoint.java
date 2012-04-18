package ru.n5g.birthdays.app.client;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.ui.RootPanel;

public class MainEntryPoint implements com.google.gwt.core.client.EntryPoint {

  public void onModuleLoad() {
    Info.display("Информация", "Hello World");
    RootPanel.get().add(new ContentPanel());
  }
}
