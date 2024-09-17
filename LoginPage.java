package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    //constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By emailAddressField = By.id("EmailOrAccountNumber");
    By rememberEmailCheckbox = By.id("RememberEmail");
    By passwordField = By.id("Password");
    By showPassword;
    By hidePassword;
    By forgotPasswordLink = By.linkText("שחכתי סיסמא");
//    By savedEmailFieldInForgotPassword = By.id("lockedID");
//    By editEmailInForgotPassword = By.linkText("עריכה");
//    By resetPasswordbtn = By.id("resetPassword");
//    By closeDialogWindow = By.className("close");
    By submitBtn = By.id("SignInNow");

//methods
    //Gets an email address & inserts it to the email field
    public void insertEmailAddress(String emailAddress){
    driver.findElement(emailAddressField).sendKeys(emailAddress);
    }
//    public void selectRememberEmail(boolean userSelection){
//        WebElement rememberCheckbox = driver.findElement(rememberEmailCheckbox);
//        boolean checkboxStatus = rememberCheckbox.isSelected();
//        if(userSelection==true && checkboxStatus==false)
//            rememberCheckbox.click();
//        if (userSelection==false && checkboxStatus==true)
//            rememberCheckbox.click();
//    }
    //Clicks the 'Remember email' checkbox
    public void clickRememberEmailCheckbox(){
        driver.findElement(rememberEmailCheckbox).click();
    }

    //Gets a password & inserts it to the password field
    public void insertPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    //Clicks 'Forgot password' link
    public void clickForgotPassword(){
        driver.findElement(forgotPasswordLink).click();
    }
//    public String getSavedEmailInForgotPassword(){
//        String savedPassword = driver.findElement(savedEmailFieldInForgotPassword).getText();
//        return savedPassword;
//    }
//    public void editPasswordInForgotPassword(String newEmail){
//        driver.findElement(savedEmailFieldInForgotPassword).clear();
//        driver.findElement(savedEmailFieldInForgotPassword).sendKeys(newEmail);
//    }
//            public void submitForgotPsw(){
//        driver.findElement(resetPasswrdbtn).click();
//            }
//            public void closeForgotPasswordWindow(){
//        driver.findElement(closeDialogWindow).click();
//            }

    //Clicks Login button
public void submitLogin(){
        driver.findElement(submitBtn).click();
}

}
