package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.dtos.IccidWrapper;
import au.com.telstra.simcardactivator.dtos.SimCardActivateDto;
import au.com.telstra.simcardactivator.dtos.SimCardActivationLogResponse;
import au.com.telstra.simcardactivator.dtos.SuccessResponse;
import au.com.telstra.simcardactivator.entities.SimCardActivationLog;
import au.com.telstra.simcardactivator.repos.SimCardActivationLogRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/sim")
public class SimCardController {
  private final RestTemplate restTemplate;
  private final SimCardActivationLogRepo simCardActivationLogRepo;

  public SimCardController(RestTemplate restTemplate, SimCardActivationLogRepo simCardActivationLogRepo) {
    this.restTemplate = restTemplate;
    this.simCardActivationLogRepo = simCardActivationLogRepo;
  }

  @PostMapping("/activate")
  public SuccessResponse activateSimCard(@RequestBody SimCardActivateDto dto) {
    IccidWrapper iccidWrapper = new IccidWrapper(dto.getIccid());
    SuccessResponse successResponse = restTemplate.postForObject("http://localhost:8444/actuate", iccidWrapper, SuccessResponse.class);
    SimCardActivationLog newLog = new SimCardActivationLog(
        dto.getIccid(),
        dto.getCustomerEmail(),
        successResponse != null && successResponse.isSuccess()
    );
    simCardActivationLogRepo.save(newLog);
    return successResponse;
  }

  @GetMapping("/activate/log")
  public SimCardActivationLogResponse simCardActivationLog(@RequestParam Long simCardId) {
    Optional<SimCardActivationLog> logOpt = simCardActivationLogRepo.findById(simCardId);
    if (logOpt.isEmpty()) {
      return null;
    }
    var log = logOpt.get();
    return new SimCardActivationLogResponse(
        log.getIccid(),
        log.getCustomerEmail(),
        log.isActive()
    );
  }

}
