package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.dtos.SimCardActivateDto;
import au.com.telstra.simcardactivator.dtos.SimCardActivationLogResponse;
import au.com.telstra.simcardactivator.dtos.SuccessResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;
    private SimCardActivateDto activateDto;
    private static Long simCardId = 1L;

    @Given("iccid = {string}")
    public void givenIccid(String iccid) {
        activateDto = new SimCardActivateDto(iccid, "user@gmail.com");
    }

    @When("activate")
    public void whenActivate() {
        restTemplate.postForObject(
            "http://localhost:8080/sim/activate",
            activateDto,
            SuccessResponse.class
        );
    }

    @Then("success = {string}")
    public void thenSuccess(String expectedSuccess) {
        var res = restTemplate.getForObject(
            "http://localhost:8080/sim/activate/log?=simCardId={simCardId}",
            SimCardActivationLogResponse.class,
            simCardId++
        );
        boolean expected = Boolean.parseBoolean(expectedSuccess);
        boolean actual = res.isActive();
        assertThat(res.getIccid()).isEqualTo(activateDto.getIccid());
        assertThat(res.getCustomerEmail()).isEqualTo(activateDto.getCustomerEmail());
        assertThat(expected).isEqualTo(actual);
    }

}