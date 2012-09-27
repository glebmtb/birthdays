package ru.n5g.birthdays.administrator.server.service;

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
import ru.n5g.birthdays.administrator.client.service.AdministratorService;
import ru.n5g.birthdays.administrator.server.dao.AdministratorListDao;
import ru.n5g.birthdays.administrator.shared.bean.AdministratorListDTO;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.bean.UserRole;
import ru.n5g.birthdays.core.server.dao.combo_box.UserRoleComboBoxDao;
import ru.n5g.birthdays.core.server.service.combo_box.UserRoleComboBoxService;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

@Service("administratorService.rpc")
public class AdministratorServiceImpl<M extends AdministratorListDTO> implements AdministratorService<M> {
  @Autowired
  private AdministratorListDao administratorListDao;

  @Autowired
  private UserRoleComboBoxService userRoleComboBoxService;

  @Autowired
  private UserRoleComboBoxDao userRolComboBoxDao;

  private PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");

  @Override
  public BasePagingLoadResult<M> loadUserList(BasePagingLoadConfig loadConfig) {
    List<M> agentModelList = getModelList(administratorListDao.loadTableRows());
    int start = 0;
    int limit = administratorListDao.getTableRowsCount();
    int offsetLimit = 0;

    BasePagingLoadResult<M> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<M>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  @Override
  @Transactional
  public void setUsers(M dto) {
    Long id = dto.getId();
    User user;

    if (id == null)
      user = new User();
    else
      user = administratorListDao.get(id);

    user.setLogin(dto.getLogin());
    if (dto.getPassword() != null) {
      user.setPassword(passwordEncoder.encodePassword(dto.getPassword(), null));
    }
    user.setRole(UserRole.convert(dto.getRole()));
    user.setComment(dto.getPassword());
    administratorListDao.saveOrUpdateNonTransactional(user);
  }

  @Override
  public void delUsers(M dto) {
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

  protected List<M> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      M dto = (M) User.convert((User) o);
      dto.setCountContact(((User) o).getContactCount().getCount());
      resultList.add(dto);
    }
    return resultList;
  }
}
