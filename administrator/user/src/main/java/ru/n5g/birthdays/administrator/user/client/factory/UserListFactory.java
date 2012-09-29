package ru.n5g.birthdays.administrator.user.client.factory;

import ru.n5g.birthdays.administrator.user.client.localization.UserListLocalization;
import ru.n5g.birthdays.administrator.user.client.presenter.UserListPresenter;
import ru.n5g.birthdays.administrator.user.client.service.UserListServiceAsync;

/**
 * @author home
 */
public interface UserListFactory {
  UserListPresenter getPresenter();

  UserListLocalization getLocalization();

  UserListServiceAsync getService();
}
