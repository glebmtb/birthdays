package ru.n5g.birthdays.note_book.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.client.service.ContactListService;
import ru.n5g.birthdays.note_book.server.dao.ContactListDao;

@Service("contactListService.rpc")
public class ContactListServiceImpl implements ContactListService {
  @Autowired
  private ContactListDao contactListDao;

  @Override
  public BasePagingLoadResult<ContactDTO> loadContactList(BasePagingLoadConfig loadConfig) {
    List<ContactDTO> agentModelList = getModelList(contactListDao.loadTableRows());
    int start = 0;
    int limit = contactListDao.getTableRowsCount();
    int offsetLimit = 0;

    BasePagingLoadResult<ContactDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<ContactDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  protected List<ContactDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      resultList.add(Contact.convert((Contact) o));
    }
    return resultList;
  }
}
