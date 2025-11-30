package au.com.telstra.simcardactivator.dtos;

public class SimCardActivateDto {
  private final String iccid;
  private final String customerEmail;

  public SimCardActivateDto(String iccid, String customerEmail) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
  }

  public String getIccid() {
    return iccid;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

}
