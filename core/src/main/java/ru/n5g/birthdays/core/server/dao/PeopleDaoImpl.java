package ru.n5g.birthdays.core.server.dao;

import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.Contact;

@Repository
public class PeopleDaoImpl extends BaseDaoImpl<Contact> implements PeopleDao {
}
