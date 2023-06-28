package testng;


import Utilities.ReadConfigProperty;
import Utilities.WebDriverManager;

import pageElements.UnitPageElements;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import Utilities.ExcelReader;

public class Product extends WebDriverManager{
	ReadConfigProperty objprop = new ReadConfigProperty();
	public static WebDriver driver;
	UnitPageElements unitObj;
	ExcelReader exObj = new ExcelReader();
	
  @Test(priority=0,enabled=true,dataProvider="logins")
    public void loginSite(String id,String password) {
	  
	  unitObj.login(id,password);
	  Assert.assertEquals(unitObj.welcome.getText(),"Welcome admin,"); 
		  
  }
  @Test(priority=1,enabled=true)
  public void productUnitAdd() throws InterruptedException
  {
	  
	  unitObj.addUnit();
	  Assert.assertEquals(unitObj.searchUnit(exObj.excelread(1, 0)), "Sooraj");
  }
  
  @Test(priority=2,enabled=true)
  public void productUnitEdit()
  {
	  
	  unitObj.editUnit(exObj.excelread(1, 2));

	  Assert.assertEquals(unitObj.searchUnit(exObj.excelread(1, 2)), "SoorajMK");
  }
  
  @Test(priority=3,enabled=true)
  public void productUnitDelete()
  {
	  
	  unitObj.deleteUnit();
	  unitObj.searchUnit(exObj.excelread(1, 2));
	  Assert.assertEquals(unitObj.no_Records(), "No matching records found");
  }
  
  @Parameters("browser")
  @BeforeTest(alwaysRun=true)
  public void beforeTest() {
	 
	  driver=launchBrowser(objprop.browser,objprop.url);
	  unitObj = new UnitPageElements(driver);
  }


  @AfterTest(alwaysRun=true)
  public void afterTest() {
	  driver.close();
  }
  @DataProvider(name="logins")
  public Object[][] getDataFromDataprovider(){
	  return new Object[][] 
	  	{
	          { "admin", "123123" }
	      
	      };

	}

}