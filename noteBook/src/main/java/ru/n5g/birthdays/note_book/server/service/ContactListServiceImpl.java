package ru.n5g.birthdays.note_book.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.client.service.ContactListService;
import ru.n5g.birthdays.note_book.server.dao.ContactListDao;

@Service("contactListService.rpc")
public class ContactListServiceImpl implements ContactListService {
  @Autowired
  private ContactListDao contactListDao;

  @Override
  public BasePagingLoadResult<ContactDTO> loadContactList(BasePagingLoadConfig loadConfig) {
    BaseModelData filter = new BaseModelData();
    filter.set("userId", User.getAuthenticationUserId());
    List<ContactDTO> agentModelList = getModelList(contactListDao.loadTableRows(filter));
    int start = 0;
    int limit = contactListDao.getTableRowsCount(filter);
    int offsetLimit = 0;

    BasePagingLoadResult<ContactDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<ContactDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  @Override
  @Transactional
  public void deleteContact(List<ContactDTO> dtoList) {
    Contact contact;
    for (ContactDTO dto : dtoList) {
      contact = contactListDao.get(dto.getId());
      contactListDao.deleteNonTransactional(contact);
    }
  }

  protected List<ContactDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      resultList.add(Contact.convert((Contact) o));
    }
    return resultList;
  }
}
