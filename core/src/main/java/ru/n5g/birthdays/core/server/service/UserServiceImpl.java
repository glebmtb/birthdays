package ru.n5g.birthdays.core.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.n5g.birthdays.core.server.bean.AppUserDetails;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.UserDao;

@Transactional
public class UserServiceImpl implements UserDetailsService {
  @Autowired
  UserDao userDao;

  private PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.loadByUserName(username);
    if (user == null)
      user = new User();
    AppUserDetails appUserDetails = new AppUserDetails(user);
    String newPass = passwordEncoder.encodePassword("pass", null);
    return appUserDetails;
  }
}
