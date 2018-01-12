package automation.Selenium.steps;

import java.util.List;

import org.openqa.selenium.WebElement;

import automation.Selenium.*;
import automation.Selenium.pages.*;
import automation.Selenium.steps.serenity.*;
import automation.Selenium.utilityhelpers.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Steps;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import net.serenitybdd.core.annotations.findby.By;



public class HomePageStepDefinition {
    HomePage homePage;
    utilityhelper UtilityHelper;

    @Steps
    HomePageStepSerenity homePageStepSerenity;
    
    @Then("clear all array list")
    @Screenshots(disabled = true)
    public void clearAllArrayList() {
        UtilityHelper.clearAllArrayList();
        restartWebDriver();
    }
    
    @Screenshots(disabled = true)
    private void restartWebDriver() {
        getDriver().quit();
    }
    
    @Then("Meijer Home page displays for Guest user")
    public void meijerHomePageDisplay() throws InterruptedException {
        homePageStepSerenity.meijerHomePageDisplay();
    }
    
    @Then("user clicks Keep this Store on passive store selection modal")
    public void userClicksKeepThisStoreOnPassiveStoreSelectionModal() {
        if (UtilityHelper.elementExists(homePage.PASSIVE_STORE_DIALOG)) {
            homePageStepSerenity.verifyPassiveStoreModalIsDisplayed();
            homePageStepSerenity.highLightKeepThisStoreButton();
            homePageStepSerenity.userClicksKeepThisStoreOnPassiveStoreSelectionModal();
        }
    }
    
    @When("user searches for '(.*)' product")
    public void userSearchesForProductType(String productType) throws InterruptedException {
        homePageStepSerenity.userSearchesForProductType(productType);
        homePageStepSerenity.highLightSearchIconToViewProducts();
        homePageStepSerenity.clickOnSearchIconToViewProducts();
        homePageStepSerenity.verifySearchResultsDisplay();
    }
    
    @Then("select item '(.*)' with desire qty '(.*)' and Add to Cart")
    public void atRandomViewProductAndAddToCart(int itemCount, String itemQty) throws InterruptedException {
        quickThreadSleepMethod(3);
        int limit = 0, elementIndex;
        String availability;
        List<WebElement> productList = getDriver().findElements(By.xpath("//*[@id='tierResults']/div[3]/ul/li"));
        UtilityHelper.shuffleList(1, productList.size());
        for (WebElement element : productList) {
            if (limit != itemCount) {
                elementIndex = UtilityHelper.popShuffledList();
                element = getDriver().findElement(By.xpath("//*[@id='tierResults']/div[3]/ul/li[" + elementIndex + "]"));
                availability = UtilityHelper.elementGetAttribute(element, "class");
                UtilityHelper.moveToViewElement(element);
                if (availability.contains("unavailable") || availability.contains("instore")) {
                    continue;
                }
                homePageStepSerenity.atRandomViewProductAndAddToCart(element, itemQty);
                limit++;
            }
        }
    }

    @Then("quick and easy thread.sleep'(.*)' method")
    public static void quickThreadSleepMethod(int seconds) throws InterruptedException {
        String millis = seconds + "000";
        Thread.sleep(Integer.valueOf(millis));
    }
    
    
    @When("user enter correct email '(.*)'")
    public void userEnterCorrectEmailAddress(String correctemail) throws InterruptedException {
    	homePageStepSerenity.user_Enter_Correct_Email_Address(correctemail);
    	homePageStepSerenity.highLightNextButton();
    	homePageStepSerenity.nextButonAfterEnteringEmail();
    }
    
    
}
