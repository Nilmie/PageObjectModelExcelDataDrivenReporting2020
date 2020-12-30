package Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
 

import MiHCM.*;
import Utility.ReadExcel;

    public class Login {

 

    	WebDriver driver;

	    LoginPage objLogin;

	    HomePage objHomePage;

	    @BeforeTest

	    public void setup() throws FileNotFoundException, IOException {
	    	
	    	System.setProperty("webdriver.chrome.driver","C:\\seldrv\\chromedriver.exe");
	        ChromeOptions optons = new ChromeOptions();
	
	        optons.addArguments("ignore-certificate-errors");

	         driver = new ChromeDriver();
	        driver.get(ReadExcel.readExcel(1,0, ".\\Data\\data.xlsx","Sheet1"));

	    }

	    /**

	     * This test case will login in http://demo.guru99.com/V4/

	     * Verify login page title as guru99 bank

	     * Login to application

	     * Verify the home page using Dashboard message
	     * @throws ParseException 
	     * @throws IOException 
	     * @throws FileNotFoundException 

	     */

	    @Test(priority=0)

	    public void test_Home_Page_Appear_Correct() throws FileNotFoundException, IOException{

	        //Create Login Page object

	    objLogin = new LoginPage(driver);

	    //Verify login page title

	    String loginPageTitle = objLogin.getLoginTitle();

	    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

	    //login to application

	    objLogin.loginToGuru99(ReadExcel.readExcel(1,1, ".\\Data\\data.xlsx","Sheet1"), ReadExcel.readExcel(1,2, ".\\Data\\data.xlsx","Sheet1"));
	     WebDriverWait wait = new WebDriverWait(driver, 20);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='heading3']/td")));
	    // go the next page

	    objHomePage = new HomePage(driver);

	    //Verify home page

	    //Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains(ReadExcel.readExcel(1,3, ".\\Data\\data.xlsx","Sheet1")));
	    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().contains(ReadExcel.readExcel(1,3, ".\\Data\\data.xlsx","Sheet1")));

	    }
	}