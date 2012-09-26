package ru.n5g.birthdays.administrator.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.administrator.client.localization.AdministratorLocalization;
import ru.n5g.birthdays.administrator.client.presenter.AdministratorPresenter;
import ru.n5g.birthdays.administrator.client.service.AdministratorService;
import ru.n5g.birthdays.administrator.client.service.AdministratorServiceAsync;

public class AdministratorFactoryImpl implements AdministratorFactory {
  private final AdministratorLocalization localization = GWT.create(AdministratorLocalization.class);
  private final AdministratorServiceAsync service = GWT.create(AdministratorService.class);
  private AdministratorPresenter presenter;

  @Override
  public AdministratorLocalization getLocalization() {
    return localization;
  }

  public AdministratorPresenter getPresenter() {
    if (presenter == null)
      presenter = new AdministratorPresenter(this);
    return presenter;
  }

  @Override
  public AdministratorServiceAsync getService() {
    return service;
  }
}
