package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators0
    By colorList = By.cssSelector("[id*='dk_container_Colour']");
    By colorDDOpt1 = By.cssSelector("a.dk_dropdown_option[data-dk-dropdown-value='355-232']");
    By sizeList = By.cssSelector("[id*='dk_container_Size']");
    By sizeDD912 = By.cssSelector("a.dk_dropdown_option[data-dk-dropdown-value='27']");
    By sizeDD152 = By.cssSelector("a.dk_dropdown_option[data-dk-dropdown-value='30']");
    By sizeDD23 = By.cssSelector("a.dk_dropdown_option[data-dk-dropdown-value='38']");

    By addToBagBtn = By.cssSelector("[onclick='ProductPage.Styles.AddToBagCTA.AddItem(this); return false;']");
    By viewEditBagBtn = By.cssSelector("[data-ga-v3='View Bag']");


    //methods
    //Opens color DDL & selects a color
    public void selectColor(){
        driver.findElement(colorList).click();
        driver.findElement(colorDDOpt1).click();
    }

    //Opens sizes DDL & selects a size
    public void selectSize(){
        driver.findElement(sizeList).click();
        driver.findElement(sizeDD152).click();
    }

    //Returns FALSE if 'Add To Bag' button is disable, TRUE is enable
    //ALWAYS RETURNS TRUE!!!
//    public boolean isAddToBagEnable(){
//        boolean enable = driver.findElement(addToBagBtn).isEnabled();
//        return enable;
//    }

    //Returns "This item is currently out of stock" if product is OOS
    //Returns 'null' if product is available
        public String isAddToBagEnable(){
        String desc = driver.findElement(addToBagBtn).getAttribute("data-description");
        return desc;
    }

    //Clicks 'Add to Bag' button
    public void addToBug(){
        driver.findElement(addToBagBtn).click();
    }

    //Clicks 'View/Edit bug' button
    public void viewBug(){
        driver.findElement(viewEditBagBtn).click();
    }
}
