package au.com.telstra.simcardactivator.entities;

import javax.persistence.*;

@Entity
public class SimCardActivationLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String iccid;

  @Column(nullable = false)
  private String customerEmail;

  @Column(nullable = false)
  private boolean active;

  public SimCardActivationLog() {
  }

  public SimCardActivationLog(String iccid, String customerEmail, boolean active) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public String getIccid() {
    return iccid;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public boolean isActive() {
    return active;
  }
}
