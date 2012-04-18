package ru.n5g.birthdays.app.client.view;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class OneWidgetContentPanel extends ContentPanel implements AcceptsOneWidget {

  private Widget currentWidget;

  public OneWidgetContentPanel() {
    addStyleName("one-widget-content-panel");
    setLayout(new FitLayout());
    setHeaderVisible(false);
    getAriaSupport().setPresentation(true);
    setLayoutOnChange(true);
    setBorders(false);
  }

  public void setWidget(IsWidget w) {
    setWidget(asWidgetOrNull(w));
  }

  public void setWidget(Widget w) {
    if (w == currentWidget) {
      return;
    }

    if (w != null) {
      w.removeFromParent();
    }

    if (currentWidget != null) {
      remove(currentWidget);
    }

    currentWidget = w;

    if (w != null) {
      add(w);
    }
  }
}
