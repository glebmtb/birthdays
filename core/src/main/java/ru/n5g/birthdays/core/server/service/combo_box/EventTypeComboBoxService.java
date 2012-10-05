package ru.n5g.birthdays.core.server.service.combo_box;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.server.dao.combo_box.ComboBoxDao;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;


/**
 * @author belyaev
 */
@Service
public class EventTypeComboBoxService extends LoadComboBoxServiceImpl<EventTypeDTO>  {
  @Override
  public void setComboBoxDao(ComboBoxDao comboBoxDao) {
    super.setComboBoxDao(comboBoxDao);
  }

  @Override
  protected List<EventTypeDTO> getModelList(List dataList) {
    List<EventTypeDTO> list = new ArrayList<EventTypeDTO>();
    for (Object o : dataList) {
      list.add(EventType.convert((EventType) o));
    }
    return list;
  }
}
