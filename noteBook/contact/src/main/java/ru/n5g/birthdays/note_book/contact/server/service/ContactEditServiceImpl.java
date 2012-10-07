package ru.n5g.birthdays.note_book.contact.server.service;

import java.util.ArrayList;
import java.util.Date;
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
import ru.n5g.birthdays.core.server.dao.combo_box.EventTypeComboBoxDao;
import ru.n5g.birthdays.core.server.service.combo_box.EventTypeComboBoxService;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
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

  @Override
  public void saveContact(ContactDTO dto) {
    Contact bean = Contact.convert(dto);
    bean.setUserId(User.getAuthenticationUserId());
    contactDAO.saveOrUpdate(bean);
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
        evenDTO.setDateEvent(new Date(0, ev.getMonth(), ev.getDay()));
        evenDTO.setYear(ev.getYear());
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
