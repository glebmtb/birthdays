package ru.n5g.birthdays.core.server.dao;


import ru.n5g.birthdays.core.server.bean.People;

public interface PeopleDAO {
  People get(Long aLong);
}
