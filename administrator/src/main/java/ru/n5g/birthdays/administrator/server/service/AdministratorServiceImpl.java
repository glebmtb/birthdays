package ru.n5g.birthdays.administrator.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.n5g.birthdays.administrator.client.service.AdministratorService;

@SuppressWarnings("serial")
public class AdministratorServiceImpl extends RemoteServiceServlet implements AdministratorService{
  @Override
  public String getMessage() {
    return "Hello World 2";
  }
}
