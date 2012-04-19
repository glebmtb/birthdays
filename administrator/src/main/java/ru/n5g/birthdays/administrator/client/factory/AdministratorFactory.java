package ru.n5g.birthdays.administrator.client.factory;

import ru.n5g.birthdays.administrator.client.localization.AdministratorLocalization;
import ru.n5g.birthdays.administrator.client.presenter.AdministratorPresenter;
import ru.n5g.birthdays.administrator.client.service.AdministratorServiceAsync;

public interface AdministratorFactory {

  AdministratorLocalization getLocalization();

  AdministratorPresenter getPresenter();

  AdministratorServiceAsync getService();
}
