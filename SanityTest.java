package testCases;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.junit.Assert.*;

public class SanityTest {
   private static WebDriver driver;
   private static ExtentReports externalReports = new ExtentReports();
   private static ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
   Actions action = new Actions(driver);
   LoginPage loginPage = new LoginPage(driver);
   HomePage homePage = new HomePage(driver);
   PaymentPage paymentPage = new PaymentPage(driver);
   ProductPage productPage = new ProductPage(driver);
   ShoppingbagPage shoppingbagPage = new ShoppingbagPage(driver);
   String currentTime = String.valueOf(System.currentTimeMillis());

   @BeforeClass
   public static void beforeTests() {
      String browserType;
      try {
         browserType = Utilities.getData("BROWSER");
      } catch (ParserConfigurationException e) {
         throw new RuntimeException(e);
      } catch (IOException e) {
         throw new RuntimeException(e);
      } catch (SAXException e) {
         throw new RuntimeException(e);
      }
      System.out.println("BROWSER: " + browserType);
      if (browserType.equalsIgnoreCase("Chrome")) {
         driver = new ChromeDriver();
      }
      else if (browserType.equalsIgnoreCase("Firefox")) {
         driver = new FirefoxDriver();
      }
      else if (browserType.equalsIgnoreCase("Edge")) {
         driver = new EdgeDriver();
      } else {
         System.out.println("Illegal browser Type. Default Browser is Chrome");
         driver = new ChromeDriver();
      }
      driver.get(Constants.HOME_PAGE);
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      externalReports.attachReporter(spark);
      spark.config().setTheme(Theme.DARK);
   }


   @Test
   public void loginTest() throws InterruptedException, IOException {
      System.out.println("Starting test1 -login test");
      ExtentTest loginTest = externalReports.createTest("Login Test");
      homePage.openAccountPage();
      assertEquals(Constants.LOGIN_PAGE,driver.getCurrentUrl());
      try {
         loginPage.insertEmailAddress(Utilities.getData("EMAIL"));
      } catch (ParserConfigurationException e) {
         throw new RuntimeException(e);
      } catch (IOException e) {
         throw new RuntimeException(e);
      } catch (SAXException e) {
         throw new RuntimeException(e);
      }
      try {
         loginPage.insertPassword(Utilities.getData("PASSWORD"));
      } catch (ParserConfigurationException e) {
         throw new RuntimeException(e);
      } catch (IOException e) {
         throw new RuntimeException(e);
      } catch (SAXException e) {
         throw new RuntimeException(e);
      }
      loginPage.submitLogin();
      Thread.sleep(3000);if (driver.getCurrentUrl().equals("https://account.next.co.il/en/MyAccount/OrderTracking"))
          loginTest.pass("Login succeeded",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\pictures\\" +currentTime)).build());
      else  {
         loginTest.fail("Login failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\pictures\\" +currentTime)).build());

      }
      driver.navigate().back();
   }

   @Test
   public void homePage() throws InterruptedException, IOException {
      ExtentTest homePageLinksTest = externalReports.createTest("Links from Home Page");
      System.out.println("Starting test2 - links in Home page");
      //driver.get(Constants.HOME_PAGE);
      System.out.println("Opening LivingRoom from left side-bar links");
      try {
         homePage.clickSideCategory("LivingRoom");
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
      if (Constants.LIVING_ROOM.equalsIgnoreCase(driver.getCurrentUrl()))
         homePageLinksTest.pass("Left panel link works");
      else
         homePageLinksTest.fail("Left panel link doesn't work");
      //driver.navigate().back();   doesn't work
      driver.get(Constants.HOME_PAGE);

      System.out.println("Opening Kitchen from the center of the page");
      try {
         homePage.clickCentralCategory("kitchen");
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
      assertEquals(Constants.KITCHEN,driver.getCurrentUrl());
      driver.navigate().back();
      System.out.println("Opening Boys category from Banner");
      try {
         homePage.openMainCategory("boys");
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
      assertEquals(Constants.BOYS,driver.getCurrentUrl());
      driver.navigate().back();
      System.out.println("Changing language to Hebrew");
      homePage.clickCountrySelectorBtn();
      homePage.selectLanguage("Hebrew");
      Thread.sleep(4000);
      try {
         assertEquals("האתר הרשמי של Next: אופנה, בגדי ילדים ועיצוב הבית אונליין", driver.getTitle());
         homePageLinksTest.pass("Hebrew was selected");
      }catch (AssertionError e){
         homePageLinksTest.fail("Hebrew was not selected",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\pictures\\" +currentTime)).build());
      }
      System.out.println("Changing language back to English");
      homePage.clickCountrySelectorBtn();
      homePage.selectLanguage("en");
      assertEquals(driver.getTitle(), "Next Official Site: Online Fashion, Kids Clothes & Homeware");
   }

   @Test
   public void searchProductTest() throws IOException {
      System.out.println("Starting test3 - searching for a product");
      ExtentTest searchProductTest = externalReports.createTest("Search Product Test");
      try {
         homePage.searchProduct("D91-207");
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      productPage.selectColor();
      productPage.selectSize();
      if (productPage.isAddToBagEnable()!=null)
         searchProductTest.info("Product can't be added: \" + productPage.isAddToBagEnable()");
      else {
         productPage.addToBug();
         productPage.viewBug();
         try {
            shoppingbagPage.selectQuantity(2);
         } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
         } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
         }
         shoppingbagPage.clickToCheckout();
      }
      try {
         Assert.assertEquals("https://account.next.co.il/en/login/Checkout",driver.getCurrentUrl());
         searchProductTest.pass("Product was added to bug");
      } catch (Exception e){
         searchProductTest.fail("'Checkout' page wasn't opened",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\pictures\\" +currentTime)).build());
      }
   }

   @Test
   public void paymentTest(){
      System.out.println("Starting test5 - payment");
      driver.get(Constants.PAYMENT_PAGE);
      paymentPage.selectPaymentRadio("creditcard");
      paymentPage.insertCardNumber(Constants.CARD_NUMBER);
      paymentPage.insertCardHolderName(Constants.CARD_HOLDER);
      paymentPage.insertExpiryMonth(Constants.EXP_MONTH);
      paymentPage.insertExpiryYear(Constants.EXP_YEAR);
      paymentPage.insertSecurityCode(Constants.SEC_CODE);
      paymentPage.clickSubmitPayment();
      //missing validation of error here
   }

   @AfterClass
   public static void afterTests() throws InterruptedException {
      Thread.sleep(3000);
      externalReports.flush();
      driver.quit();
   }

   public static String takeScreenShot(String ImagesPath) {
      TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
      File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
      File destinationFile = new File(ImagesPath+".png");
      try {
         FileUtils.copyFile(screenShotFile, destinationFile);
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
      return ImagesPath+".png";
   }
}
