package ru.n5g.birthdays.administrator.user.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.administrator.user.client.localization.UserListLocalization;
import ru.n5g.birthdays.administrator.user.client.presenter.UserListPresenter;
import ru.n5g.birthdays.administrator.user.client.service.UserListService;
import ru.n5g.birthdays.administrator.user.client.service.UserListServiceAsync;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

/**
 * @author home
 */
public class UserListFactoryImpl implements UserListFactory {
  private UserListLocalization localization = GWT.create(UserListLocalization.class);
  private UserListServiceAsync service = GWT.create(UserListService.class);

  private ClientFactory clientFactory;
  private UserListPresenter presenter;

  public UserListFactoryImpl(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public UserListPresenter getPresenter() {
    return presenter == null ? presenter = new UserListPresenter(this) : presenter;
  }

  @Override
  public UserListLocalization getLocalization() {
    return localization;
  }

  @Override
  public UserListServiceAsync getService() {
    return service;
  }
}
