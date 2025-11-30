package au.com.telstra.simcardactivator.dtos;

public class IccidWrapper {
  private final String iccid;

  public IccidWrapper(String iccid) {
    this.iccid = iccid;
  }

  public String getIccid() {
    return iccid;
  }
}
