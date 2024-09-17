package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageFooter {
    WebDriver driver;

    //constractor
    public PageFooter(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By customerRegistrationBtn = By.xpath("//*[@id=\"sec\"]/div/a/span");
    By facebookIcon = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[1]/div/div/a[1]/img");
    By twitterIcon = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[1]/div/div/a[2]/img");
    By instragramIcon = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[1]/div/div/a[3]/img");
    By pinterestIcon = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[1]/div/div/a[4]/img");
    By youtubeIcon = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[1]/div/div/a[5]");
    //By selectLngEng = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[2]/div/div[1]/div[2]/div/div[2]/a/strong");
    //By selectLngHe = By.xpath("//*[@id=\"platform_modernisation_footer\"]/footer/div/div[2]/div/div[1]/div[2]/div/div[2]/span");
    By selectLngEng = By.linkText("En");
    By selectLngHe = By.linkText("He");
}
