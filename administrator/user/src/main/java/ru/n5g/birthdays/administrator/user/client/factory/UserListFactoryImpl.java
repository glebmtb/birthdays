package ru.n5g.birthdays.administrator.user.client.factory;

import ru.n5g.birthdays.core.client.factory.ClientFactory;

/**
 * @author home
 */
public class UserListFactoryImpl implements UserListFactory {
  private ClientFactory clientFactory;

  public UserListFactoryImpl(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }
}
