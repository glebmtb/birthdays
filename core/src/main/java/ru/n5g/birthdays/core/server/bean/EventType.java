package ru.n5g.birthdays.core.server.bean;

import javax.persistence.*;

/**
 * @author home
 */
@Entity
@Table(name = "event_type")
public class EventType {
  private Long id;
  private String name;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_type_id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "event_name", length = 100)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
