package ru.n5g.birthdays.app.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.People;
import ru.n5g.birthdays.core.server.dao.PeopleDAO;

@Service
public class BirthdaysServiceImpl implements BirthdaysService{
  @Autowired
  private PeopleDAO peopleDAO;

  public String getHelloWorld() {
    People people = peopleDAO.get(new Long(1));
    return people.getFirstName();
  }
}
