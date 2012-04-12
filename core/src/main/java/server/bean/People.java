package server.bean;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class People {
  private Long id;
  private String firstName;
  private String lastName;
  private String middleName;
  private Integer phone;
  private String comment;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "first_name", nullable = false, length = 255)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", nullable = false, length = 255)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "middle_name", nullable = true, length = 255)
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Column(name = "phone", nullable = false, unique = true)
  public Integer getPhone() {
    return phone;
  }

  public void setPhone(Integer phone) {
    this.phone = phone;
  }

  @Column(name = "comment", length = 1000)
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
