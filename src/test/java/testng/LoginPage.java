package testng;

import org.testng.annotations.Test;


import Utilities.ReadConfigProperty;
import Utilities.WebDriverManager;
import pageElements.UnitPageElements;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
@Listeners(Utilities.TestListener.class)
public class LoginPage extends WebDriverManager{
	ReadConfigProperty objprop = new ReadConfigProperty();
	public static WebDriver driver;
	UnitPageElements unitObj;
@DataProvider(name="logins")
	  public Object[][] getDataFromDataprovider(){
		  return new Object[][] 
		  	{
		          { "admin", "123123" }
		               
		      };
}

  @Test(priority = 0,enabled =true,dataProvider ="logins")
  public void loginSite(String id,String password) {
	  
	  unitObj.login(id,password);
	  Assert.assertEquals(unitObj.welcome.getText(),"Welcome admin,"); 
  }
  @BeforeTest
  @Parameters("browser")
  public void beforeTest(String browser) {
	 
	  driver=launchBrowser(browser,objprop.url);
	  unitObj = new UnitPageElements(driver);
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }
 

	}

