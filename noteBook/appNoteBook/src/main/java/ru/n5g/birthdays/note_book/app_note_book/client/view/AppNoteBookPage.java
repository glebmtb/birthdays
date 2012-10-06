package ru.n5g.birthdays.note_book.app_note_book.client.view;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.note_book.app_note_book.client.presenter.AppNoteBookPresenter;
import ru.n5g.birthdays.note_book.app_note_book.client.bean.TabEnum;

/**
 * @author home
 */
public interface AppNoteBookPage extends IsWidget {
  void setPresenter(AppNoteBookPresenter appPresenter);

  AcceptsOneWidget getWidgetContainer();

  void selectTab(TabEnum tabEnum);

  void setHeader(String heading1, String heading2);

  interface Presenter {
    AppNoteBookPage start();

    /**
     * Проверить разрешение по кодам
     *
     * @param permissionCode код для определения разрешения
     * @param toolBar        табпанелька содержащая панельки
     * @param tabItem        панелька которую необходимо убрать из {@link com.extjs.gxt.ui.client.widget.TabPanel}, если отсутствуют права доступа
     */
    public void checkPermissions(String permissionCode, TabPanel toolBar, TabItem tabItem);

    /**
     * Проверить разрешение по кодам
     *
     * @param permissionCode код для определения разрешения
     * @param component      компонента, для которого нужно определить разрешение
     * @param hideIfDenied   скрывать/дизаблитиь или отображать/активировать компоненту в зависимости от разрешения
     */
    void checkPermissions(String permissionCode, Component component, boolean hideIfDenied);

    void openContact();

    void openEventType();
  }
}
