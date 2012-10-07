package ru.n5g.birthdays.core.server.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import ru.n5g.birthdays.core.shared.bean.ContactDTO;

@Entity
@Table(name = "contact")
public class Contact {
  private Long id;
  private String firstName;
  private String lastName;
  private String middleName;
  private String nickname;
  private User user;
  private Long userId;
  private String comment;
  private List<Event> event = new ArrayList<Event>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "first_name", length = 255)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", length = 255)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "middle_name", length = 255)
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Column(name = "comment", length = 1000)
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Column(name = "nickname", length = 255)
  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
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

  @OneToMany(mappedBy = "contact")
  public List<Event> getEvent() {
    return event;
  }

  public void setEvent(List<Event> event) {
    this.event = event;
  }

  public static Contact convert(ContactDTO dto) {
    Contact bean = new Contact();
    bean.setId(dto.getId());
    bean.setNickname(dto.getNickname());
    bean.setFirstName(dto.getFirstName());
    bean.setLastName(dto.getLastName());
    bean.setMiddleName(dto.getMiddleName());
    bean.setComment(dto.getComment());
    bean.setUserId(dto.getUserId());
    return bean;
  }

  public static ContactDTO convert(Contact bean) {
    ContactDTO dto = new ContactDTO();
    dto.setId(bean.getId());
    dto.setNickname(bean.getNickname());
    dto.setFirstName(bean.getFirstName());
    dto.setLastName(bean.getLastName());
    dto.setMiddleName(bean.getMiddleName());
    dto.setComment(bean.getComment());
    dto.setUserId(bean.getUserId());
    return dto;
  }
}
