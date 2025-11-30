package au.com.telstra.simcardactivator.dtos;

public class SuccessResponse {
  private boolean success;

  public SuccessResponse() {
    this.success = false;
  }

  public SuccessResponse(boolean success) {
    this.success = success;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
