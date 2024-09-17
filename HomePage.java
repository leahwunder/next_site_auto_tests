package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class HomePage {
    WebDriver driver;

    Actions action;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    //locators
    //banner
    By accountLoginLink = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[2]/div[2]/div[2]/div/a/span");

    By boysLink = By.id("meganav-link-0");
    By bootsLink = By.cssSelector("title=Boots");
    By girlsLink = By.id("meganav-link-1");
    By babyLink = By.id("meganav-link-2");
    By womenLink = By.id("meganav-link-3");
    By menLink = By.id("meganav-link-4");
    By homeLink = By.id("meganav-link-5");
    By brandsLink = By.id("meganav-link-6");
    By countrySelectorBtn = By.cssSelector("[data-testid='header-country-lang-selector']");
    By HeSelectorBtn = By.cssSelector("[data-testid='country-selector-language-button-0']");
    By EnSelectorBtn = By.cssSelector("[data-testid='country-selector-language-button-1']");
    By shopNowBtn = By.cssSelector("[data-testid='country-selector-CTA-button']");
    By searchBox = By.id("header-big-screen-search-box");
    By submitSearch = By.cssSelector("button.MuiButtonBase-root");
    By d91207Product = By.cssSelector("[data-testid='product_summary_image_d91207']");
    By itemCode = By.cssSelector("div.ItemNumber");


    //Sidebar categories
    By newinhomewareSideLink = By.id("left-sidebar-links4-1wz6n8ycmkpbyuhyj1rrgl24g");
    By livingroomSideLink = By.xpath("//div[@class='sidebar-links-wrap']//a[contains(text(),'Living Room')]");


    By diningroomSideLink = By.id("left-sidebar-links5-1lhk46ovqu13z5zixy673i6le");
    By bedroomSideLink = By.id("left-sidebar-links6-591t9mdbppte0mtgt0cgdwspf");
    By childrensbedroomSideLink = By.id("left-sidebar-links7-39fop0ufllrodv2nuwk46s677");
    By nurserySideLink = By.id("left-sidebar-links8-86ax3qvigbiaymedjpghp2uvy");

    //Central categories
    By bedroomCentralLink = By.linkText("Bedroom");
    By kitchenCentralLink = By.linkText("Kitchen");


    //methods
    //Method for opening login page
    public void openAccountPage() {
       driver.findElement(accountLoginLink).click();
    }

    //Gets main category & opens it by double click
    public void openMainCategory(String mainCategory) throws NoSuchFieldException, IllegalAccessException {
        String realCategory = mainCategory.toLowerCase().concat("Link");

        // Use reflection to get the By object dynamically
        By categoryBy = (By) HomePage.class.getDeclaredField(realCategory).get(this);

        // Find the WebElement and perform double click
        WebElement actCategory = driver.findElement(categoryBy);
        action.doubleClick(actCategory).build().perform();
    }

    //Gets an internal category & opens it from the side menu
    public void clickSideCategory(String intCategory) throws NoSuchFieldException, IllegalAccessException {
        String realCategory = intCategory.toLowerCase().concat("SideLink");
        // Use reflection to get the By object dynamically
        By categoryBy = (By) HomePage.class.getDeclaredField(realCategory).get(this);
        //Find the element and perform click
        driver.findElement(categoryBy).click();
    }

    //Gets an internal category & opens it from central links
    public void clickCentralCategory(String intCategory) throws NoSuchFieldException, IllegalAccessException {

        String realCategory = intCategory.concat("CentralLink");
        // Use reflection to get the By object dynamically
        By categoryBy = (By) HomePage.class.getDeclaredField(realCategory).get(this);
        //Find the element and perform click
        driver.findElement(categoryBy).click();
    }

    public void clickCountrySelectorBtn(){
        driver.findElement(countrySelectorBtn).click();
    }

    //Gets selected language & clicks it
    //Clicks 'Shop Now' button
    public void selectLanguage(String lang){
        String langHe = "hebrew";
        String langEn = "english";
        if (langHe.contains(lang.toLowerCase()))
            driver.findElement(HeSelectorBtn).click();
        if (langEn.contains(lang.toLowerCase()))
            driver.findElement(EnSelectorBtn).click();
        driver.findElement(shopNowBtn).click();
    }

    //Gets a products to search
    //Inserts it in the search field
    //Clicks the found product to open it
    //Return a message if the product wasn't found
    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(submitSearch).click();
        //driver.findElement(d91207Product).click();
        try {
            driver.findElement(itemCode).getText().equalsIgnoreCase(product);
        } catch (Exception e){
            //throw new Exception (e);
            System.out.println("***********Product wasn't found!!!!!!!!!1************");
        }

    }
}