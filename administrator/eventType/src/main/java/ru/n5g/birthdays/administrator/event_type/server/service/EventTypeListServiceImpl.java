package ru.n5g.birthdays.administrator.event_type.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeListService;
import ru.n5g.birthdays.administrator.event_type.server.dao.EventTypeListDao;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

@Service("eventTypeListService.rpc")
public class EventTypeListServiceImpl implements EventTypeListService {
  @Autowired
  private EventTypeListDao eventTypeListDao;

  @Override
  public BasePagingLoadResult<EventTypeDTO> loadContactList(BasePagingLoadConfig loadConfig) {
    BaseModelData filter = new BaseModelData();
    List<EventTypeDTO> agentModelList = getModelList(eventTypeListDao.loadTableRows(filter));
    int start = 0;
    int limit = eventTypeListDao.getTableRowsCount(filter);
    int offsetLimit = 0;

    BasePagingLoadResult<EventTypeDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<EventTypeDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  @Override
  @Transactional
  public void deleteContact(List<EventTypeDTO> dtoList) {
    EventType eventType;
    for (EventTypeDTO dto : dtoList) {
      eventType = eventTypeListDao.get(dto.getId());
      eventTypeListDao.deleteNonTransactional(eventType);
    }
  }

  protected List<EventTypeDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      resultList.add(EventType.convert((EventType) o));
    }
    return resultList;
  }
}
