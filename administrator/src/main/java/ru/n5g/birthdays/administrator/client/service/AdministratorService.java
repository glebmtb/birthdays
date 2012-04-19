package ru.n5g.birthdays.administrator.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("administratorService")
public interface AdministratorService extends RemoteService {
  String getMessage();
}
