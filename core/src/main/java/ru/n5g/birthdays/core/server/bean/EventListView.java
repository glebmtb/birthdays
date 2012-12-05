package ru.n5g.birthdays.core.server.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author belyaev
 */
@Entity
@Table(name = "event_list_view")
public class EventListView {
  private Long id;
  private Long userId;
  private Long eventTypeId;
  private String eventTypeName;
  private String contactFio;
  private Integer eventDaysLeft;
  private String eventYears;
  private Integer eventDay;
  private Integer eventMonth;
  private String contactLastName;
  private String contactFirstName;
  private String contactMiddleName;
  private String contactNickname;


  @Id
  @Column(name = "event_id", insertable = false, updatable = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "user_id", insertable = false, updatable = false)
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "event_type_id", insertable = false, updatable = false)
  public Long getEventTypeId() {
    return eventTypeId;
  }

  public void setEventTypeId(Long eventTypeId) {
    this.eventTypeId = eventTypeId;
  }

  @Column(name = "event_type_name", insertable = false, updatable = false)
  public String getEventTypeName() {
    return eventTypeName;
  }

  public void setEventTypeName(String eventTypeName) {
    this.eventTypeName = eventTypeName;
  }

  @Column(name = "contact_fio", insertable = false, updatable = false)
  public String getContactFio() {
    return contactFio;
  }

  public void setContactFio(String contactFio) {
    this.contactFio = contactFio;
  }

  @Column(name = "event_days_left", insertable = false, updatable = false)
  public Integer getEventDaysLeft() {
    return eventDaysLeft;
  }

  public void setEventDaysLeft(Integer eventDaysLeft) {
    this.eventDaysLeft = eventDaysLeft;
  }

  @Column(name = "event_years", insertable = false, updatable = false)
  public String getEventYears() {
    return eventYears;
  }

  public void setEventYears(String eventYears) {
    this.eventYears = eventYears;
  }

  @Column(name = "event_day", insertable = false, updatable = false)
  public Integer getEventDay() {
    return eventDay;
  }

  public void setEventDay(Integer eventDay) {
    this.eventDay = eventDay;
  }

  @Column(name = "event_month", insertable = false, updatable = false)
  public Integer getEventMonth() {
    return eventMonth;
  }

  public void setEventMonth(Integer eventMonth) {
    this.eventMonth = eventMonth;
  }

  @Column(name = "contact_last_name", insertable = false, updatable = false)
  public String getContactLastName() {
    return contactLastName;
  }

  public void setContactLastName(String contactLastName) {
    this.contactLastName = contactLastName;
  }

  @Column(name = "contact_first_name", insertable = false, updatable = false)
  public String getContactFirstName() {
    return contactFirstName;
  }

  public void setContactFirstName(String contactFirstName) {
    this.contactFirstName = contactFirstName;
  }

  @Column(name = "contact_middle_name", insertable = false, updatable = false)
  public String getContactMiddleName() {
    return contactMiddleName;
  }

  public void setContactMiddleName(String contactMiddleName) {
    this.contactMiddleName = contactMiddleName;
  }

  @Column(name = "contact_nickname", insertable = false, updatable = false)
  public String getContactNickname() {
    return contactNickname;
  }

  public void setContactNickname(String contactNickname) {
    this.contactNickname = contactNickname;
  }
}
