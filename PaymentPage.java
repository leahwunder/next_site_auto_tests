package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By paypalRadioBtn = By.cssSelector(".PaymentId[value='6']");
    By creditCardRadioBtn = By.cssSelector(".PaymentId[value='5']");
    By creditcardArrowDown = By.cssSelector("@nx-icon margin-top-m pull-right guest-checkout-payment-arrowdown checkout-down-Arrow");
    By cardholderNameField = By.id("cardholderName");
    By saveCardButton = By.id("saveCardButton");
    By cardNumberField = By.id("cardNumber");

    By expiryMonthField = By.id("expiryMonth");
    By expiryYearField = By.id("expiryYear");
    By securityCodeField = By.id("securityCode");
    By submitButton = By.id("submitButton");
    By cardNumberErrHint = By.id("cardNumber-hint");
    By expiryDateErrHint= By.id("expiryDate-hint");

   //methods
    //Gets the selected payment method & selects its radio button
    //Open the selected method area by the arrow-down button
   public void selectPaymentRadio(String paymentMethod) {
       if (paymentMethod.equalsIgnoreCase("paypal"))
           driver.findElement(paypalRadioBtn).click();
       if (paymentMethod.equalsIgnoreCase("creditCard"))
           driver.findElement(creditCardRadioBtn).click();
       driver.findElement(creditcardArrowDown).click();
   }

   //Gets credit card number & insert it in the card number field
   public void insertCardNumber(String cardNumber){
       //String cardNumberString = Integer.toString(cardNumber);
       driver.findElement(cardNumberField).sendKeys(cardNumber);
       String err = driver.findElement(cardNumberErrHint).getText();
       if (!err.equals(null))
           System.out.println(err);
       }


   //Clicks the save card details checkbox
   public void setSaveCard(){
       driver.findElement(saveCardButton).click();
   }

   //Gets holder name & inserts in the card-holder name field
   public void insertCardHolderName(String holderName){
      driver.findElement(cardNumberField).sendKeys(holderName);
   }

   //Gets card expiry month & insert it
   public void insertExpiryMonth(int expMonth){
       String expMonthString = Integer.toString(expMonth);
       driver.findElement(expiryMonthField).sendKeys(expMonthString);
   }

    //Gets card expiry year & insert it
    public void insertExpiryYear(int expYear){
        String expYearString = Integer.toString(expYear);
        driver.findElement(expiryYearField).sendKeys(expYearString);
    }

    //Gets card security code & insert it
    public void insertSecurityCode(int securityCode){
        String securityCodeString = Integer.toString(securityCode);
        driver.findElement(securityCodeField).sendKeys(securityCodeString);
    }

    public void clickSubmitPayment(){
       driver.findElement(submitButton).click();
    }
}
