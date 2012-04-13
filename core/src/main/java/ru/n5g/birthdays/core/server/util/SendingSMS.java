package ru.n5g.birthdays.core.server.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.n5g.birthdays.core.shared.bean.SMS;

public class SendingSMS {
  // Логин для доступа к платформе Giper.mobi
  private String login;

  // Пароль для доступа к платформе Giper.mobi
  private String password;

  public SendingSMS(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String makeXML(SMS sms) throws UnsupportedEncodingException {
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<message>" +
        "<login>" + login + "</login>" +
        "<pwd>" + password + "</pwd>" +
        "<id>" + sms.getTransactionId() + "</id>" +
        "<sender>" + sms.getSender_id() + "</sender>" +
        "<text>" + new String(sms.getSms_text().getBytes("UTF-8")) + "</text>" +
        // "<time>20101118214600</time>"+		// Можно указать время отправки этого сообщения
        "<phones>" +
        "<phone>" + sms.getPhone() + "</phone>" +
        // "<phone>79091234567</phone>"+		// Можно указать дополнительные номера телефонов
        "</phones>" +
        // "<test>1</test>"+					// Если раскомментировать эту строку, то СМС не будет отправлено фактически и не будет протарифицированно
        "</message>";
    return xml;
  }

  public String send(SMS sms) {
    String response;
    try {
      String urlStr = "http://giper.mobi/api/message";
      String xml = makeXML(sms);

      URL url = new URL(urlStr);
      HttpURLConnection huc = (HttpURLConnection) url.openConnection();
      huc.setDoOutput(true);
      huc.setDoInput(true);
      huc.setRequestMethod("POST");
      huc.setRequestProperty("Content-Type", "application/xml");
      OutputStream os = huc.getOutputStream();

      os.write(xml.getBytes());
      os.flush();
      os.close();

      InputStream is = huc.getInputStream();
      InputStreamReader inputstreamreader = new InputStreamReader(is);
      BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
      StringBuffer inString = new StringBuffer();
      String s3;
      while ((s3 = bufferedreader.readLine()) != null) {
        inString.append(s3);
      }
      bufferedreader.close();

      huc.disconnect();

      // Ответ сервера Giper.mobi
      String responceXML = inString.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&gt;&lt;", "&gt;<br>&lt;");

      response = "HTTP - соединение с сервером Giper.mobi состоялось.<br >" +
          "Ответ сервера:<br >" + responceXML;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      response = "Во время транзакции произошла ошибка:" + ex.getMessage() +
          "<br > Смотриле лог своего WEB - сервера для большей информации.";
    }
    return response;
  }
}
