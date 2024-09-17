package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingbagPage {
    WebDriver driver;

    public ShoppingbagPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By quantityDD = By.id("dk_container_Qty_1");
    By quantityDDVal1 = By.cssSelector("[data-dk-dropdown-value='1']");
    By quantityDDVal2 = By.cssSelector("[data-dk-dropdown-value='2']");
    By quantityDDVal3 = By.cssSelector("[data-dk-dropdown-value='3']");
    By quantityDDVal4 = By.cssSelector("[data-dk-dropdown-value='4']");
    By quantityDDVal5 = By.cssSelector("[data-dk-dropdown-value='5']");
    //By goToCheckoutBtn = By.className("nxbtn primary GoToCheckout");
    By goToCheckoutBtn = By.xpath("//*[@id=\"pageWidth\"]/div[5]/div[3]/a[3]");

    //methods

    //Gets selected quantity
    //Opens quantities DDL
    //Selects the quantity
    public void selectQuantity(int quantity) throws NoSuchFieldException, IllegalAccessException {
        String locatorName = "quantityDDVal" + quantity;
        By quantityBy = (By) ShoppingbagPage.class.getDeclaredField(locatorName).get(this);
        driver.findElement(quantityDD).click();
        driver.findElement(quantityBy).click();

    }

    //Clicks 'Checkout' button
    public void clickToCheckout(){
        driver.findElement(goToCheckoutBtn).click();
    }
}
