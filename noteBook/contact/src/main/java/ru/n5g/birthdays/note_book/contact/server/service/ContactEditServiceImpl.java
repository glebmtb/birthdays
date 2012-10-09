package ru.n5g.birthdays.note_book.contact.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.bean.Event;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.ContactDao;
import ru.n5g.birthdays.core.server.dao.EventDao;
import ru.n5g.birthdays.core.server.dao.combo_box.EventTypeComboBoxDao;
import ru.n5g.birthdays.core.server.service.combo_box.EventTypeComboBoxService;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.contact.client.service.ContactEditService;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactEditDTO;
import ru.n5g.birthdays.note_book.contact.shared.bean.EventListDTO;
import ru.n5g.birthdays.note_book.contact.shared.bean.RpcWhiteListContact;

/**
 * @author belyaev
 */
@Service("contactEditService.rpc")
public class ContactEditServiceImpl implements ContactEditService {

  @Autowired
  private ContactDao contactDAO;

  @Autowired
  private EventTypeComboBoxDao eventTypeComboBoxDao;

  @Autowired
  private EventTypeComboBoxService eventTypeComboBoxService;

  @Autowired
  private EventDao eventDao;

  @Override
  @Transactional
  public void saveContact(ContactEditDTO dto) {
    Contact bean = Contact.convert(dto);
    bean.setUserId(User.getAuthenticationUserId());
    contactDAO.saveOrUpdateNonTransactional(bean);

    for (EventListDTO el : dto.getEventList()) {
      if (el.getDelete() != null && el.getDelete()) {
        eventDao.deleteNonTransactional(eventDao.get(el.getId()));
      }
      else {
        Event event = el.getId() == null ? new Event() : eventDao.get(el.getId());
        event.setUserId(User.getAuthenticationUserId());
        event.setEventTypeId(el.getEventType().getId());
        event.setDay(el.getDay() != null ? el.getDay().intValue() : null);
        event.setMonth(el.getMonth() != null ? el.getMonth().intValue() : null);
        event.setYear(el.getYear() != null ? el.getYear().intValue() + 1900 : null);
        event.setContactId(bean.getId());
        eventDao.saveOrUpdateNonTransactional(event);
      }
    }
  }

  @Override
  @Transactional
  public ContactEditDTO getContact(Long id) {
    Contact contact = contactDAO.get(id);
    ContactEditDTO dto = new ContactEditDTO();
    dto.setId(contact.getId());
    dto.setNickname(contact.getNickname());
    dto.setFirstName(contact.getFirstName());
    dto.setLastName(contact.getLastName());
    dto.setMiddleName(contact.getMiddleName());
    dto.setComment(contact.getComment());
    dto.setUserId(contact.getUserId());
    if (contact.getEvent() != null) {
      List<EventListDTO> eventList;
      eventList = new ArrayList<EventListDTO>();
      EventListDTO evenDTO;
      for (Event ev : contact.getEvent()) {
        evenDTO = new EventListDTO();
        evenDTO.setId(ev.getId());
        evenDTO.setDay(ev.getDay());
        evenDTO.setMonth(ev.getMonth());
        evenDTO.setYear(ev.getYear() != null ? ev.getYear() - 1900 : null);
        evenDTO.setEventType(EventType.convert(ev.getEventType()));
        eventList.add(evenDTO);
      }
      dto.setEventList(eventList);
    }
    return dto;
  }

  @Override
  public BasePagingLoadResult<EventTypeDTO> loadEventTypeList(BasePagingLoadConfig loadConfig) {
    eventTypeComboBoxService.setComboBoxDao(eventTypeComboBoxDao);
    return eventTypeComboBoxService.load(loadConfig);
  }

  @Override
  public RpcWhiteListContact registerClasses(RpcWhiteListContact whiteList) throws AccessDeniedException, RuntimeException {
    return new RpcWhiteListContact();
  }
}
