package au.com.telstra.simcardactivator.dtos;

public class SimCardActivationLogResponse {
  private final String iccid;
  private final String customerEmail;
  private final boolean active;

  public SimCardActivationLogResponse(String iccid, String customerEmail, boolean active) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
    this.active = active;
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
