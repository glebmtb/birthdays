package ru.n5g.birthdays.note_book.event.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.Event;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.shared.bean.EventDTO;
import ru.n5g.birthdays.note_book.event.client.service.EventListService;
import ru.n5g.birthdays.note_book.event.server.dao.EventListDao;

@Service("eventTypeListService.rpc")
public class EventListServiceImpl implements EventListService {
  @Autowired
  private EventListDao eventDao;

  @Override
  public BasePagingLoadResult<EventDTO> loadList(BasePagingLoadConfig loadConfig) {
    BaseModelData filter = new BaseModelData();
    filter.set("userId", User.getAuthenticationUserId());
    List<EventDTO> agentModelList = getModelList(eventDao.loadTableRows(filter));
    int start = 0;
    int limit = eventDao.getTableRowsCount(filter);
    int offsetLimit = 0;

    BasePagingLoadResult<EventDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<EventDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  protected List<EventDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      EventDTO dto = Event.convert((Event) o);
      dto.setEventType(EventType.convert(((Event) o).getEventType()));
      resultList.add(dto);
    }
    return resultList;
  }
}
