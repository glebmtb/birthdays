package ru.n5g.birthdays.core.server.bean;

import javax.persistence.*;

import ru.n5g.birthdays.core.shared.bean.EventDTO;
import ru.n5g.birthdays.core.shared.util.MonthTranslate;

/**
 * @author belyaev
 */
@Entity
@Table(name = "event")
public class Event {
  private Long id;
  private User user;
  private Long userId;
  private EventType eventType;
  private Long eventTypeId;
  private Integer day;
  private Integer month;
  private Integer year;
  private Contact contact;
  private Long contactId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Column(name = "user_id")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @ManyToOne
  @JoinColumn(name = "event_type_id", insertable = false, updatable = false)
  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  @Column(name = "event_type_id")
  public Long getEventTypeId() {
    return eventTypeId;
  }

  public void setEventTypeId(Long eventTypeId) {
    this.eventTypeId = eventTypeId;
  }

  @Column(name = "day")
  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  @Column(name = "month")
  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  @Column(name = "year")
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
  @JoinColumn(name = "contact_id", insertable = false, updatable = false)
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  @Column(name = "contact_id")
  public Long getContactId() {
    return contactId;
  }

  public void setContactId(Long contactId) {
    this.contactId = contactId;
  }

  public static EventDTO convert(Event bean) {
    EventDTO dto = new EventDTO();
    dto.setId(bean.getId());
    dto.setDay(bean.getDay());
    dto.setMonth(bean.getMonth());
    dto.setYear(bean.getMonth());
    StringBuffer eventDate = new StringBuffer();
    if (bean.getDay() != null)
      eventDate.append(bean.getDay());
    else
      eventDate.append("**");
    eventDate.append(" ");
    if (bean.getMonth() != null)
      eventDate.append(MonthTranslate.getMonthName(bean.getMonth()));
    else
      eventDate.append("**");
    dto.setEventDay(eventDate.toString());
    return dto;
  }

  public static Event convert(EventDTO dto) {
    Event bean = new Event();
    bean.setId(dto.getId());
    bean.setDay(dto.getDay());
    bean.setMonth(dto.getMonth());
    bean.setYear(dto.getYear());
    return bean;
  }
}
