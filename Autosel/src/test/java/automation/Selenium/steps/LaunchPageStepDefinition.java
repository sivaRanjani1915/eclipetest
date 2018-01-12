package automation.Selenium.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;
import java.text.ParseException;

import automation.Selenium.steps.serenity.*;


public class LaunchPageStepDefinition {

    @Steps
    LaunchStepSerenity launchSteps;

    @Given("user navigate to test environment URL")
    public void launchMeijerDotCom() throws InterruptedException {
        launchSteps.launchURL();
        Thread.sleep(5000);
        launchSteps.verifyPageLoad();
    }

   
}
