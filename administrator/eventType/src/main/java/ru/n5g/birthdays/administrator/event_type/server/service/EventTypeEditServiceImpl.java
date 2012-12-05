package ru.n5g.birthdays.administrator.event_type.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeEditService;
import ru.n5g.birthdays.administrator.event_type.server.dao.EventTypeListDao;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author belyaev
 */
@Service("eventTypeEditService.rpc")
public class EventTypeEditServiceImpl implements EventTypeEditService {

  @Autowired
  private EventTypeListDao eventTypeListDao;

  @Override
  public void saveContact(EventTypeDTO dto) {
    EventType bean = EventType.convert(dto);
    eventTypeListDao.saveOrUpdate(bean);
  }
}
