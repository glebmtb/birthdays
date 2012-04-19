package ru.n5g.birthdays.administrator.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdministratorServiceAsync {
  void getMessage(AsyncCallback<String> callback);
}
