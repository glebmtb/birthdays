package ru.n5g.birthdays.administrator.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.n5g.birthdays.administrator.client.service.AdministratorService;

@Controller
public class AdministratorController extends RemoteServiceServlet {
  private transient Logger logger = LoggerFactory.getLogger(getClass());

  AdministratorService service;

  @Autowired
  public AdministratorController(AdministratorService service) {
    super(service);
    this.service = service;
  }

  @RequestMapping("/*/administratorService.rpc")
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      super.doPost(request, response);
    }
    catch (RuntimeException ex) {
      logger.error(ex.toString(), ex);
      throw ex;
    }
    catch (Exception ex) {
      logger.error(ex.toString(), ex);
      throw ex;
    }
    return null;
  }
}
