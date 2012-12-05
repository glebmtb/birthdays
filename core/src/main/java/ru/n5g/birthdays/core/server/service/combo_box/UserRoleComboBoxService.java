package ru.n5g.birthdays.core.server.service.combo_box;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.UserRole;
import ru.n5g.birthdays.core.server.dao.combo_box.ComboBoxDao;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

@Service
public class UserRoleComboBoxService extends LoadComboBoxServiceImpl<UserRoleDTO> {

  @Override
  public void setComboBoxDao(ComboBoxDao comboBoxDao) {
    super.setComboBoxDao(comboBoxDao);
  }

  @Override
  protected List<UserRoleDTO> getModelList(List dataList) {
    List<UserRoleDTO> list = new ArrayList<UserRoleDTO>();
    UserRoleDTO dto;
    for (Object o : dataList) {
      list.add(UserRole.convert((UserRole) o));
    }
    return list;
  }
}
