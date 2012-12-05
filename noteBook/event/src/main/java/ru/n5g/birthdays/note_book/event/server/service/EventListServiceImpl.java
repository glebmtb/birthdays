package ru.n5g.birthdays.note_book.event.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseFilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.EventListView;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.util.MonthTranslate;
import ru.n5g.birthdays.note_book.event.client.service.EventListService;
import ru.n5g.birthdays.note_book.event.server.dao.EventListDao;
import ru.n5g.birthdays.note_book.event.shared.bean.EventListDTO;
import ru.n5g.birthdays.note_book.event.shared.bean.RpcWhiteListEvent;

@Service("eventTypeListService.rpc")
public class EventListServiceImpl implements EventListService {
  @Autowired
  private EventListDao eventListDao;

  @Override
  public BasePagingLoadResult<EventListDTO> loadList(BasePagingLoadConfig loadConfig) {
    BaseModelData filter = new BaseModelData();
    filter.set("userId", User.getAuthenticationUserId());
    filter.set("sortDir",loadConfig.getSortDir());
    filter.set("sortField",loadConfig.getSortField());
    filter.set("filterConfigs",((BaseFilterPagingLoadConfig) loadConfig).getFilterConfigs());
    List<EventListDTO> agentModelList = getModelList(eventListDao.loadTableRows(filter));
    BasePagingLoadResult<EventListDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<EventListDTO>(agentModelList);
    return basePagingLoadResult;
  }

  protected List<EventListDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    EventListView bean;
    EventListDTO dto;
    for (Object o : dataList) {
      bean = (EventListView) o;
      dto = new EventListDTO();
      dto.setId(bean.getId());
      dto.setEventTypeName(bean.getEventTypeName());
      dto.setContactFio(bean.getContactFio());
      dto.setEventDaysLeft(bean.getEventDaysLeft());
      dto.setEventYears(bean.getEventYears());
      dto.setEventDay(convertDay(bean.getEventDay(), bean.getEventMonth()));
      resultList.add(dto);
    }
    bean = null;
    dto = null;
    return resultList;
  }


  @Override
  public RpcWhiteList registerClasses(RpcWhiteList whiteList) throws AccessDeniedException, RuntimeException {
    return new RpcWhiteListEvent();
  }

  private String convertDay(Integer day, Integer month) {
    StringBuilder st = new StringBuilder();
    if (day != null)
      st.append(day);
    else
      st.append("**");
    st.append(" ");
    if (month != null)
      st.append(MonthTranslate.getMonthName(month));
    else
      st.append("*****");
    return st.toString();
  }
}
