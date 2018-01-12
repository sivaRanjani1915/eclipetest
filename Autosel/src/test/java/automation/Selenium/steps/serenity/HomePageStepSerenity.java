package automation.Selenium.steps.serenity;

import automation.Selenium.pages.*;
import automation.Selenium.utilityhelpers.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.text.ParseException;

public class HomePageStepSerenity extends ScenarioSteps {

    HomePage homePage;
   
    utilityhelper UtilityHelper;


    @Step
    public void meijerHomePageDisplay() throws InterruptedException {
        homePage.meijerHomePageDisplay();
    }
    
    @Step
    public void verifyPassiveStoreModalIsDisplayed() {
        homePage.verifyPassiveStoreModalIsDisplayed();
    }
    
    @Step
    public void highLightKeepThisStoreButton() {
        if (UtilityHelper.elementExists(homePage.PASSIVE_STORE_DIALOG)) {
            UtilityHelper.highLightElement(homePage.KEEP_STORE);
        }
    }
    
    @Step
    public void userClicksKeepThisStoreOnPassiveStoreSelectionModal() {
        homePage.userClicksKeepThisStoreOnPassiveStoreSelectionModal();
    }
    
    @Step
    public void userSearchesForProductType(String productType) throws InterruptedException {
        homePage.userSearchesForProductType(productType);
    }
    
    @Step
    public void highLightSearchIconToViewProducts() {
        UtilityHelper.highLightElement(homePage.SEARCH_BAR_ICON);
    }
    @Step
    public void clickOnSearchIconToViewProducts() {
        homePage.clickOnSearchIconToViewProducts();
    }
    @Step
    public void verifySearchResultsDisplay() throws InterruptedException {
        homePage.verifySearchResultsDisplay();
    }
    
    @Step
    public void atRandomViewProductAndAddToCart(WebElement element, String itemQty) {
        homePage.atRandomViewProductAndAddToCart(element, itemQty);
    }
    
    @Step
    public void user_Enter_Correct_Email_Address(String correctemail) throws InterruptedException {
    	homePage.validEmailAddress(correctemail);
    }
    
    @Step
    public void highLightNextButton() {
    	homePage.highLightNextButton();
    }
    
    @Step
    public void nextButonAfterEnteringEmail() throws InterruptedException {
    	homePage.nextButtonAfterEnteringEmail();
    }
    
}





