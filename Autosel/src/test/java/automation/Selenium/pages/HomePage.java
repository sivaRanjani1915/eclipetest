package automation.Selenium.pages;
import automation.Selenium.*;
import automation.Selenium.utilityhelpers.*;
import net.thucydides.core.pages.PageObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Strings;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static automation.Selenium.utilityhelpers.utilityhelper.*;
import static org.junit.Assert.*;

public class HomePage extends PageObject {
	
	 public static List<String> shoppingCartFulfillmentGroupArrayList = new ArrayList<>();
	 public static List<String> namesOfItemList = new ArrayList<>();
	
	   utilityhelper UtilityHelper;
	  @FindBy(xpath = "//*[@id='logo']")
	    public WebElement MEIJER_LOGO;
	  
	  
	  @FindBy(xpath = "//*[@id='quickshop' and not(contains(@class,'hide'))]")
	    public WebElement GUEST_CURBSIDE_WIDGET;

	    @FindBy(xpath = "//*[@id='quickshop' and not(contains(@class,'hide'))]")
	    public WebElement SIGN_IN_CURBSIDE_WIDGET;

	    @FindBy(xpath = "//*[@id='quickshop' and contains(@class,'hide')]")
	    public WebElement NON_CURBSIDE_STORE;
	    
	    @FindBy(xpath = "//ul[@role='dialog']/li")
	    public WebElement PASSIVE_STORE_DIALOG;
	    
	    //Click Reserve Curbside Pickup Time
	    @FindBy(xpath = "//*[text()='Reserve Curbside Pickup Time']")
	    public WebElement RESERVE_CURBSIDE_PICKUP_TIME_LINK;
	    
	    @FindBy(xpath = "//ul[@role='dialog']//button[@id='keepStoreButton']")
	    public WebElement KEEP_STORE;
	    
	    //Pickup Time Modal
	    @FindBy(xpath = "//*[@class='modalContent']//*[@id='pickupForm']")
	    public WebElement TIME_SLOT_MODAL;
	    
	    @FindBy(xpath = "//*[@id='search-form']//*[@id='nav_search_submit']")
	    public WebElement SEARCH_BAR_ICON;
	    
	    @FindBy(xpath = "//*[@id='nav_search']")
	    public WebElement SEARCH_PRODUCTS_TEXTBOX;
	    
	    @FindBy(xpath = "//*[contains(text(),'SEARCH TIPS')]")
	    public WebElement NO_SEARCH_RESULTS_FOUND;
	    

	    @FindBy(xpath = "//*[@ui-view='mainContent']")
	    public WebElement TIER_RESULTS;
	    
	    @FindBy(xpath = "//*[@id='js-headerItemCount']")
	    public WebElement CART_ICON_ITEM_COUNT;
	    
	    @FindBy(xpath = "//button[contains(.,'Next')]")
	    public static WebElement NEXT_BUTTON;
	    
	    @FindBy(xpath = "//*[@name='email']")
	    public static WebElement emailaddressfield;
	    
	    
	    
	  //Meijer Home Page is displayed for Guest User
	    public void meijerHomePageDisplay() throws InterruptedException {
	        int limit = 5, count = 0;
	        if (UtilityHelper.elementNotExists(GUEST_CURBSIDE_WIDGET)) {
	            Thread.sleep(5000);
	            while (UtilityHelper.elementNotExists(GUEST_CURBSIDE_WIDGET)) {
	                getDriver().navigate().refresh();
	                Thread.sleep(2000);
	                count++;
	                if (limit < count) {
	                    Assert.fail("Failed to Fully Load 'Time Slot Header Widget' after [" + (2 * count + 5) + "] seconds ...");
	                }
	            }
	        }
	        Assert.assertTrue("Verify Successful Page Load: ", UtilityHelper.isDisplayed(GUEST_CURBSIDE_WIDGET));
	    }
	    
	    public void verifyPassiveStoreModalIsDisplayed() {
	        if (UtilityHelper.elementExists(PASSIVE_STORE_DIALOG)) {
	            UtilityHelper.isDisplayed(PASSIVE_STORE_DIALOG);
	        }
	    }
	    
	    public void userSearchesForProductType(String productType) throws InterruptedException {
	        String searchTerm = "";
	        switch (productType.toLowerCase()) {
	            case "cnc":
	                searchTerm = "is_click_and_collect:true";
	                break;

	            case "sellable":
	                searchTerm = "is_sellable:true";
	                break;

	            case "alcohol":
	                searchTerm = "is_alcohol:true";
	                break;

	            case "tobacco":
	                searchTerm = "is_tobacco:true";
	                break;

	            case "hazardous":
	                searchTerm = "is_hazardous_material:true";
	                break;

	            case "spo":
	                searchTerm = "is_prepared_item:true";
	                break;

	            case "sth":
	                searchTerm = "is_ship_to_home:true";
	                break;

	            case "prepared item":
	                searchTerm = "is_prepared_item:true";
	                break;

	            case "age restricted":
	                searchTerm = "is_age_restricted:true";
	                break;

	            case "on sale":
	                searchTerm = "ways_to_save:Sale";
	                break;

	            case "mperks":
	                searchTerm = "ways_to_save:mPerks";
	                break;

	            case "hot item":
	                searchTerm = "hot_item_flag:true";
	                break;

	            case "random weight":
	                searchTerm = "is_random_weight:true";
	                break;

	            case "price less than map":
	                searchTerm = "price_less_than_map:true";
	                break;
	//not flags
	            case "not cnc":
	                searchTerm = "is_click_and_collect:false";
	                break;

	            case "not sellable":
	                searchTerm = "is_sellable:false";
	                break;

	            case "not alcohol":
	                searchTerm = "is_alcohol:false";
	                break;

	            case "not tobacco":
	                searchTerm = "is_tobacco:false";
	                break;

	            case "not hazardous":
	                searchTerm = "is_hazardous_material:false";
	                break;

	            case "not spo":
	                searchTerm = "is_prepared_item:false";
	                break;

	            case "not sth":
	                searchTerm = "is_ship_to_home:false";
	                break;

	            case "not prepared item":
	                searchTerm = "is_prepared_item:false";
	                break;

	            case "not age restricted":
	                searchTerm = "is_age_restricted:false";
	                break;

	            case "not hot item":
	                searchTerm = "hot_item_flag:false";
	                break;

	            case "not random weight":
	                searchTerm = "is_random_weight:false";
	                break;

	            case "price greater than map":
	                searchTerm = "price_less_than_map:false";
	                break;

	            default:
	                searchTerm = productType;
	                break;
	        }
	        System.out.println("=== SEARCHING [" + searchTerm + "]");
	        searchItemInSearchField(searchTerm);
	    }
	    
	    public void searchItemInSearchField(String itemToSearch) {
	        UtilityHelper.moveToViewElement(SEARCH_PRODUCTS_TEXTBOX);
	        UtilityHelper.typeIntoElement(SEARCH_PRODUCTS_TEXTBOX, itemToSearch);
	    }
	    
	    public void userClicksKeepThisStoreOnPassiveStoreSelectionModal() {
	        if (UtilityHelper.elementExists(PASSIVE_STORE_DIALOG)) {
	            UtilityHelper.click(KEEP_STORE);
	        }
	    }
	    
	    public void clickOnSearchIconToViewProducts() {
	        UtilityHelper.click(SEARCH_BAR_ICON);
	    }
	    
	    public void verifySearchResultsDisplay() throws InterruptedException {
	        int count = 0;
	        if (UtilityHelper.elementExists(NO_SEARCH_RESULTS_FOUND)) {
	            while (UtilityHelper.elementExists(NO_SEARCH_RESULTS_FOUND)) {
	                UtilityHelper.click(SEARCH_BAR_ICON);
	                Thread.sleep(3000);
	                if (count > 2) {
	                    Assert.fail("Search Results Service Call Failed to Populate.");
	                }
	                count++;
	            }
	        } else {
	            UtilityHelper.waitUntilElementVisible(TIER_RESULTS); //to wait for expected search results.
	        }
	    }
	    
	    public void atRandomViewProductAndAddToCart(WebElement element, String itemQty) {
	        WebElement addToCart;
	        String productName, fGroup, currentCartCount;

	        UtilityHelper.waitUntilClickable(element);
	        // To determine products alt_UoM
	        verifyProductUnitOfMeasure(element);
	        // To fetch products name
	        productName = UtilityHelper.elementGetText(element.findElement(By.xpath(".//*[@class='mjr-product-name']//a")));
	        fGroup = UtilityHelper.elementGetAttribute(element.findElement(By.xpath(".//*[@name='fulfillmentGroupTypeId']")), "value");
	        // To ensure product name is not duplicated in ArrayList 'namesOfItemList'
	        ensureNoDuplicatesExistInArrayList(productName, fGroup);
	        // To enter desired Qty
	        productTileQuantityStepperAction(element, itemQty);
	        // To click Add to Cart
	        addToCart = element.findElement(By.xpath(".//*[contains(@class, 'addToCartButton')]"));
	        currentCartCount = CART_ICON_ITEM_COUNT.getText();
	        UtilityHelper.moveToViewElement(addToCart);
	        UtilityHelper.click(addToCart);
	        System.out.println("NAME: [" + productName + "]");
	        System.out.println(" QTY: [" + itemQty + "]");
	        System.out.println("TYPE: [" + fGroup + "]");

	        addNameToHomePageArrayList(productName);
	        shoppingCartFulfillmentGroupArrayList.add(fGroup);
	        UtilityHelper.moveToViewElement(element);
	        verifyAddedQtyViaCartIcon(itemQty, currentCartCount);
	    }
	  
	    private void verifyProductUnitOfMeasure(WebElement element) {
	        WebElement UoM = element.findElement(By.xpath(".//p[@class='product-price']//span[@class='uom']"));
	        setIsProductAltUoM(UtilityHelper.elementExists(UoM));
	        if (getIsAltUoM()) { // if alt_UoM type IS found, capture value
	            String value = UtilityHelper.elementGetText(UoM).toUpperCase();
	            setAltUoM_Type(value);
	        } else {
	            setAltUoM_Type(""); // set as empty.
	        }
	    }
	    
	    private void ensureNoDuplicatesExistInArrayList(String productName, String fGroup) {
	        if ((namesOfItemList.contains(productName)) && !(fGroup.equals("2"))) {
	            namesOfItemList.remove(productName);
	        }
	    }
	    
	    private void productTileQuantityStepperAction(WebElement element, String itemQty) {
	        WebElement plusQtyButton, prodQtyField;
	        prodQtyField = element.findElement(By.xpath(".//*[@name='quantity']"));
	        plusQtyButton = element.findElement(By.xpath(".//input[contains(@class,'plus')]"));
	        // If Quantity is a decimal (LB). Clear and type desired quantity
	        if (itemQty.contains(".")) {
	            UtilityHelper.moveToViewElement(prodQtyField);
	            UtilityHelper.typeIntoElement(prodQtyField, itemQty);
	        } else {
	            // If qty > 1. click '+' qty button.
	            int desiredQty = Integer.valueOf(itemQty);
	            if (Integer.valueOf(itemQty) != 1) {
	                for (int i = 1; i < desiredQty; i++) {
	                    UtilityHelper.moveToViewElement(plusQtyButton);
	                    UtilityHelper.waitUntilClickable(plusQtyButton);
	                    UtilityHelper.click(plusQtyButton);
	                }
	            }
	        }
	    }
	    
	    private void addNameToHomePageArrayList(String name) {
	        // removing special character symbol for DEV and UAT
	        // shopping cart and mini cart replaces these symbols with question mark (?)
	        name = name.replace("®", "");
	        name = name.replace("™", "");
	        namesOfItemList.add(name);
	    }
	    
	    private void verifyAddedQtyViaCartIcon(String itemQty, String currentCartCount) {
	        try {
	            Thread.sleep(2000);

	            if (!isModalDisplayed()) {
	                String conditionalStr;
	                int itemQtyInt, currentCountInt, updateCartCount;
	                if (getIsAltUoM()) {  //To accommodate for '/LB' UoM items
	                    if (getAltUoM_Type().contains("LB")) {
	                        double itemQtyDouble, currentCountDouble, updateCartDouble;
	                        itemQty = "1";
	                        conditionalStr = String.valueOf((Double.valueOf(itemQty)) + (Double.valueOf(currentCartCount)));
	                        UtilityHelper.waitUntilExpectedCondition(CART_ICON_ITEM_COUNT, conditionalStr.substring(0, 1));
	                        double delta = 0.0; //required for Assert.assertEquals validation
	                        itemQtyDouble = Double.valueOf(itemQty);
	                        currentCountDouble = Double.valueOf(currentCartCount);
	                        updateCartDouble = Double.valueOf(CART_ICON_ITEM_COUNT.getText());
	                        Assert.assertEquals("Verify Item Qty is Calculated on Cart Icon Correctly: ", (currentCountDouble + itemQtyDouble), updateCartDouble, delta);
	                    } else {
	                        conditionalStr = String.valueOf((Integer.valueOf(itemQty)) + (Integer.valueOf(currentCartCount)));
	                        UtilityHelper.waitUntilExpectedCondition(CART_ICON_ITEM_COUNT, conditionalStr);
	                        itemQtyInt = Integer.valueOf(itemQty);
	                        currentCountInt = Integer.valueOf(currentCartCount);
	                        updateCartCount = Integer.valueOf(CART_ICON_ITEM_COUNT.getText());
	                        Assert.assertEquals("Verify Item Qty is Calculated on Cart Icon Correctly: ", (currentCountInt + itemQtyInt), updateCartCount);
	                    }
	                } else {
	                    conditionalStr = String.valueOf((Integer.valueOf(itemQty)) + (Integer.valueOf(currentCartCount)));
	                    UtilityHelper.waitUntilExpectedCondition(CART_ICON_ITEM_COUNT, conditionalStr);
	                    itemQtyInt = Integer.valueOf(itemQty);
	                    currentCountInt = Integer.valueOf(currentCartCount);
	                    updateCartCount = Integer.valueOf(CART_ICON_ITEM_COUNT.getText());
	                    Assert.assertEquals("Verify Item Qty is Calculated on Cart Icon Correctly: ", (currentCountInt + itemQtyInt), updateCartCount);
	                }
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private Boolean isModalDisplayed() {
	        String clazz = getDriver().findElement(By.xpath("//body")).getAttribute("class");
	        return (clazz.contains("pgwModalOpen") || clazz.contains("mjr-modalBody"));
	    }
	    
	    public String textComparison = null;
	    public void validEmailAddress(String correctemail) {
	        textComparison = correctemail;
	        UtilityHelper.waitUntilClickable(emailaddressfield);
	        UtilityHelper.typeIntoElement(emailaddressfield, correctemail);
	        System.out.println("=== EMAIL [" + correctemail + "]");
	    }
	    
	    public void nextButtonAfterEnteringEmail() throws InterruptedException {
	        UtilityHelper.click(NEXT_BUTTON);
	         Thread.sleep(1000);
	     }
	    
	    public void highLightNextButton() {
	        UtilityHelper.highLightElement(NEXT_BUTTON);
	    }
	    
	    
}


