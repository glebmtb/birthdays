package ru.n5g.birthdays.core.server.dao;

import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.People;

@Repository
public class PeopleDAOImpl implements PeopleDAO {

//  @Autowired
//  protected HibernateTemplate hibernateTemplate;

  @Override
  public People get(final Long surveyId) {
    People people = new People();
    people.setId(surveyId);
    people.setFirstName("Hello World DAO");
    return people;
  }
}
