package ru.n5g.birthdays.core.client.util;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;

/**
 * Класс ддя задания атрибута testid компоненту
 *
 * @author samatov
 */
public abstract class TestIdSetter {
  public static final String TESTID_ATTR_NAME = "testid";


  /**
   * Задать атрибут testId для компонента, если он ещё не был задан до этого.
   * Если компонент ещё не отрисован, атрибут будет задан при отрисовке.
   *
   * @param component компонент, которому будет задан атрибут testid
   * @param value     значение атрибута testid
   */
  public static void setTestIdIfAbsent(Component component, String value) {
    initTestId(component, value, true);
  }

  /**
   * Задать атрибут testId для компонента (заменить его, если он был задан до этого).
   * Если компонент ещё не отрисован, атрибут будет задан при отрисовке.
   *
   * @param component компонент, которому будет задан атрибут testid
   * @param value     значение атрибута testid
   */
  public static void resetTestId(Component component, String value) {
    initTestId(component, value, false);
  }

  /**
   * Задать атрибут testId для компонента.
   * Если компонент ещё не отрисован, атрибут будет задан при отрисовке.
   *
   * @param component    компонент, которому будет задан атрибут testid
   * @param value        значение атрибута testid
   * @param skipIfAbsent если true, не задавать атрибут, если он уже был задан до этого, иначе атрибут будет задан в любом случае
   */
  private static void initTestId(final Component component, final String value, final boolean skipIfAbsent) {
    if (!component.isRendered()) {
      component.addListener(Events.Render, new Listener<BaseEvent>() {

        @Override
        public void handleEvent(BaseEvent be) {
          setTestId(component, value, skipIfAbsent);
          component.removeListener(Events.Render, this);
        }
      });
    }
    else {
      setTestId(component, value, skipIfAbsent);
    }
  }

  private static void setTestId(Component component, String text, boolean skipIfExist) {
    if (skipIfExist && component.getElement().hasAttribute(TESTID_ATTR_NAME)) {
      return;
    }

    component.getElement().setAttribute(TESTID_ATTR_NAME, text);
  }
}
