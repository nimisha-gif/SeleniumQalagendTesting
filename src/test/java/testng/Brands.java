package testng;

import org.testng.annotations.Test;

import Utilities.ReadConfigProperty;
import Utilities.WebDriverManager;
import pageElements.BrandPageElements;
import pageElements.UnitPageElements;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import Utilities.ExcelReader;

public class Brands extends WebDriverManager{
	ReadConfigProperty objprop = new ReadConfigProperty();
	public static WebDriver driver;
	UnitPageElements unitObj;
	BrandPageElements brandObj;
	ExcelReader exObj = new ExcelReader();
	
  @Test(priority = 0,enabled =true,dataProvider ="logins")
  public void loginSite(String id,String password) {
	  unitObj.login(id,password);
	  Assert.assertEquals(unitObj.welcome.getText(),"Welcome admin,"); 
  }
  
  @Test(priority = 1,enabled =true)
public void brandsAdd()
{
	  brandObj.brands_add();
	  Assert.assertEquals(brandObj.brand_search(exObj.excelread(7, 0)),"Dove");
}
  @Test(priority =2,enabled = true)
  public void brandsEdit()
  {
	  brandObj.brand_search(exObj.excelread(7, 0));
	  brandObj.brands_edit(exObj.excelread(7, 2));
	  Assert.assertEquals(brandObj.brand_search(exObj.excelread(7, 2)),"Dove Soap");
  }
  @Test(priority =3,enabled = true)
  public void brandsDelete()
  {
	  brandObj.brand_search(exObj.excelread(7, 2));
	  brandObj.brands_delete();
	  brandObj.brand_search(exObj.excelread(7, 2));

	  Assert.assertEquals(brandObj.no_Records(), "No matching records found");
  }
  @Parameters("browser")
  @BeforeTest
  public void beforeTest() {
	 
	  driver=launchBrowser(objprop.browser,objprop.url);
	  unitObj = new UnitPageElements(driver);
	  brandObj= new BrandPageElements(driver);
  }
  @AfterTest
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