package ru.n5g.birthdays.core.shared.bean;

public class SMS {
  // Текст СМС-сообщения - текст на русском или латинице любой длины (до 800 знаков). В случае необходимости платформа Giper.mobi автоматически порубит текст на несколько сообщений.
  private String sms_text;

  // Уникальный идентификатор транзакции. Для каждой новой отправки он должен быть новым!
  // В данном примере он прошит жестко. Используя этот ID можно получить отчет о доставке сообщения.
  private String transactionId;

  // Имя отправителя.                 ДОЛЖНО БЫТЬ СОГЛАСОВАНО С Админом Giper.mobi!
  // может быть либо текстом на литинице либо цифрами или номером телефона (по согласованию с Giper.mobi)
  private String sender_id;

  // номер телефона получателя СМС в формате 7хххххххххх.
  // В одной транзакции отправки может быть указано и более 1го телефона.
  private String phone;

  public SMS(String sms_text, String transactionId, String sender_id, String phone) {
    this.sms_text = sms_text;
    this.transactionId = transactionId;
    this.sender_id = sender_id;
    this.phone = phone;
  }

  public String getSms_text() {
    return sms_text;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getSender_id() {
    return sender_id;
  }

  public String getPhone() {
    return phone;
  }
}
