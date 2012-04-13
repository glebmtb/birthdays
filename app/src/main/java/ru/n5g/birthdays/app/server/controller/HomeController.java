package ru.n5g.birthdays.app.server.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.n5g.birthdays.app.server.service.BirthdaysService;
import ru.n5g.birthdays.core.server.util.SendingSMS;
import ru.n5g.birthdays.core.shared.bean.SMS;

@Controller
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private BirthdaysService birthdaysService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    return homeID(null, locale, model);
  }

  @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
  public String homeID(@PathVariable("id") Integer id, Locale locale, Model model) {

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);
    model.addAttribute("serverMessage", birthdaysService.getHelloWorld());
    if (id == null) {
      model.addAttribute("id", "Ид не введены");
    }
    if (id != null) {
      model.addAttribute("id", id.toString());
    }
    model.addAttribute("people", birthdaysService.getPeople(id));
    return "home";
  }

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public String getDeliveryReport() {
    return "getDeliveryReport";
  }

  @RequestMapping(value = "/send", method = RequestMethod.GET)
  public String sendSms(Model model) throws UnsupportedEncodingException {
    model.addAttribute("sms", new SMS());
    model.addAttribute("xml", new String());
    model.addAttribute("response", new String());
    return "sendSms";
  }

  @RequestMapping(value = "/report", method = RequestMethod.GET)
  public String report(Model model) throws UnsupportedEncodingException {
    String smsText = "Hellow World SMS2";
    String smsId = "111111111111111111111112";
    String sender_id = "79274313113";
    String phone = "79274313113";
    SMS sms1 = new SMS(smsText, smsId, sender_id, phone);
    SendingSMS sendingSMS = new SendingSMS("mabden", "casper");
    String response = sendingSMS.getStatus(sms1);

    model.addAttribute("response", response);
    return "getDeliveryReport";
  }

  @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
  public String send(@ModelAttribute("sms") SMS sms,
                     BindingResult result, Model model) throws UnsupportedEncodingException {
    String smsText = sms.getSms_text();
    String smsId = sms.getTransactionId();
    String sender_id = "79274313113";
    String phone = sms.getPhone();
    SMS sms1 = new SMS(smsText, smsId, sender_id, phone);

    SendingSMS sendingSMS = new SendingSMS("mabden", "casper");
    String xml = sendingSMS.makeXML(sms1);
    String response = sendingSMS.send(sms1);

    model.addAttribute("sms", new SMS());
    model.addAttribute("xml", xml.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&gt;&lt;", "&gt;<br>&lt;"));
    model.addAttribute("response", response);
    return "sendSms";
  }

  @RequestMapping(value = "/*", method = RequestMethod.GET)
  public String other() {
    return "redirect:/";
  }

}
