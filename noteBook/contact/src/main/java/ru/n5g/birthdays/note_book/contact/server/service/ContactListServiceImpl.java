package ru.n5g.birthdays.note_book.contact.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.bean.Event;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.note_book.contact.client.service.ContactListService;
import ru.n5g.birthdays.note_book.contact.server.dao.ContactListDao;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactListDTO;

@Service("contactListService.rpc")
public class ContactListServiceImpl implements ContactListService {
  @Autowired
  private ContactListDao contactListDao;

  @Override
  @Transactional
  public BasePagingLoadResult<ContactListDTO> loadContactList(BasePagingLoadConfig loadConfig) {
    BaseModelData filter = new BaseModelData();
    filter.set("userId", User.getAuthenticationUserId());
    List<ContactListDTO> agentModelList = getModelList(contactListDao.loadTableRows(filter));
    int start = 0;
    int limit = contactListDao.getTableRowsCount(filter);
    int offsetLimit = 0;

    BasePagingLoadResult<ContactListDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<ContactListDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  @Override
  @Transactional
  public void deleteContact(List<ContactListDTO> dtoList) {
    Contact contact;
    for (ContactListDTO dto : dtoList) {
      contact = contactListDao.get(dto.getId());
      contactListDao.deleteNonTransactional(contact);
    }
  }

  protected List<ContactListDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      Contact bean = (Contact) o;
      ContactListDTO dto = new ContactListDTO();
      dto.setId(bean.getId());
      dto.setNickname(bean.getNickname());
      StringBuilder fio = new StringBuilder();
      fio.append(bean.getFirstName() != null ? bean.getFirstName().concat(" ") : "");
      fio.append(bean.getLastName() != null ? bean.getLastName().concat(" ") : "");
      fio.append(bean.getMiddleName() != null ? bean.getMiddleName() : "");
      dto.setFio(fio.toString());
      dto.setComment(bean.getComment());
      dto.setUserId(bean.getUserId());
      dto.setEventList(getEventList(bean.getEvent()));
      resultList.add(dto);
    }
    return resultList;
  }

  private String getEventList(List<Event> beanList) {
    String list = null;
    for (Event e : beanList) {
      if (e.getDay() == null)
        break;
      list = list == null ? "" : list.concat("; ");
      StringBuilder st = new StringBuilder();
      st.append(e.getEventType().getName());
      st.append(" ");
      st.append(e.getDay());
      st.append(" ");
      if (e.getMonth() != null)
        switch (e.getMonth()) {
          case 1:
            st.append("января");
            break;
          case 2:
            st.append("февраля");
            break;
          case 3:
            st.append("марта");
            break;
          case 4:
            st.append("апреля");
            break;
          case 5:
            st.append("мая");
            break;
          case 6:
            st.append("июня");
            break;
          case 7:
            st.append("июля");
            break;
          case 8:
            st.append("августа");
            break;
          case 9:
            st.append("сентября");
            break;
          case 10:
            st.append("октября");
            break;
          case 11:
            st.append("ноября");
            break;
          case 12:
            st.append("декабря");
            break;
        }
      list = list.concat(st.toString());
    }
    return list;
  }
}
