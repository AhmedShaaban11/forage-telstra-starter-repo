package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.dtos.IccidWrapper;
import au.com.telstra.simcardactivator.dtos.SimCardActivateDto;
import au.com.telstra.simcardactivator.dtos.SuccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sim")
public class SimCardController {
  private final RestTemplate restTemplate;

  public SimCardController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("/activate")
  public SuccessResponse activateSimCard(@RequestBody SimCardActivateDto dto) {
    IccidWrapper iccidWrapper = new IccidWrapper(dto.getIccid());
    return restTemplate.postForObject("http://localhost:8444/actuate", iccidWrapper, SuccessResponse.class);
  }

}
