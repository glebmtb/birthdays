package ru.n5g.birthdays.core.server.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author belyaev
 */
@Entity
@Table(name = "contact_count")
public class ContactCount {
  private Long id;
  private Integer count;

  @Id
  @Column(name = "user_id", insertable = false, updatable = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "contact_count", insertable = false, updatable = false)
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
