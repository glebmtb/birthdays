package ru.n5g.birthdays.components.client.presenter;

import ru.n5g.birthdays.components.client.localization.SimpleWindowLocalization;

/**
 * @author belyaev
 */
public abstract class SimpleWindowPresenter {
  private SimpleWindowLocalization localization;

  protected SimpleWindowPresenter(SimpleWindowLocalization localization) {
    this.localization = localization;
  }

  public SimpleWindowLocalization getLocalization() {
    return localization;
  }
}
