package ru.n5g.birthdays.administrator.user.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.n5g.birthdays.administrator.user.client.service.UserListService;
import ru.n5g.birthdays.administrator.user.server.dao.UserListDao;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.bean.UserRole;
import ru.n5g.birthdays.core.server.dao.combo_box.UserRoleComboBoxDao;
import ru.n5g.birthdays.core.server.service.combo_box.UserRoleComboBoxService;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UserDTO;
import ru.n5g.birthdays.core.shared.bean.UserRoleCode;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

@Service("userListService.rpc")
public class UserListServiceImpl implements UserListService {
  @Autowired
  private UserListDao administratorListDao;

  @Autowired
  private UserRoleComboBoxService userRoleComboBoxService;

  @Autowired
  private UserRoleComboBoxDao userRolComboBoxDao;

  private PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");

  @Override
  public BasePagingLoadResult<UserDTO> loadUserList(BasePagingLoadConfig loadConfig) {
    List<UserDTO> agentModelList = getModelList(administratorListDao.loadTableRows());
    int start = 0;
    int limit = administratorListDao.getTableRowsCount();
    int offsetLimit = 0;

    BasePagingLoadResult<UserDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<UserDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  @Override
  @Transactional
  public void setUsers(UserDTO dto) {
    Long id = dto.getId();
    User user;

    if (id == null)
      user = new User();
    else {
      user = administratorListDao.get(id);
      if (user.getRole().getCode() == UserRoleCode.ROLE_ADMIN
          && dto.getRole().getCode() != UserRoleCode.ROLE_ADMIN
          && administratorListDao.isLastAdmin()) {
        throw new RuntimeException("Нельзя удалить последнего администратора!");
      }
    }

    user.setLogin(dto.getLogin());
    if (dto.getPassword() != null) {
      user.setPassword(passwordEncoder.encodePassword(dto.getPassword(), null));
    }
    user.setRole(UserRole.convert(dto.getRole()));
    user.setComment(dto.getPassword());
    user.setUserName(dto.getUserName());
    administratorListDao.saveOrUpdateNonTransactional(user);
  }

  @Override
  public void delUsers(UserDTO dto) {
    if (administratorListDao.isLastAdmin()) {
      throw new RuntimeException("Нельзя удалить последнего администратора!");
    }
    User user = administratorListDao.get(dto.getId());
    administratorListDao.delete(user);
  }

  @Override
  public RpcWhiteList registerClasses(RpcWhiteList whiteList) throws AccessDeniedException, RuntimeException {
    return new RpcWhiteList();
  }


  @Override
  public BasePagingLoadResult<UserRoleDTO> loadUserRoleList(BasePagingLoadConfig loadConfig) {
    userRoleComboBoxService.setComboBoxDao(userRolComboBoxDao);
    return userRoleComboBoxService.load(loadConfig);
  }

  protected List<UserDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      resultList.add(User.convert((User) o));
    }
    return resultList;
  }
}
