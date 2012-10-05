package ru.n5g.birthdays.note_book.contact.server.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.ContactDao;
import ru.n5g.birthdays.core.server.dao.combo_box.EventTypeComboBoxDao;
import ru.n5g.birthdays.core.server.service.combo_box.EventTypeComboBoxService;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.contact.client.service.ContactEditService;

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
  public ContactDTO getContact(Long id) {
    Contact contact = contactDAO.get(id);
    ContactDTO dto = Contact.convert(contact);
    if (contact.getEvent() != null) {

//      EventDTO eventDTO = Event.convert(contact.getEvent());
//      eventDTO.setEventType(EventType.convert(contact.getEvent().getEventType()));
//      dto.setEvent(eventDTO);
    }
    return dto;
  }

  @Override
  public BasePagingLoadResult<EventTypeDTO> loadEventTypeList(BasePagingLoadConfig loadConfig) {
    eventTypeComboBoxService.setComboBoxDao(eventTypeComboBoxDao);
    return eventTypeComboBoxService.load(loadConfig);
  }
}
