package testng;

import org.testng.annotations.Test;

import Utilities.ExcelReader;
import Utilities.ReadConfigProperty;
import Utilities.WebDriverManager;
import pageElements.CategoriesPageElements;
import pageElements.UnitPageElements;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Categories extends WebDriverManager{
	ReadConfigProperty objprop = new ReadConfigProperty();
	public static WebDriver driver;
	UnitPageElements unitObj;
	CategoriesPageElements  catelements;
	ExcelReader exObj = new ExcelReader();
	
  @Test(priority = 0,groups = {"functional"},enabled =true,dataProvider ="logins")
  public void loginSite(String id,String password) {
	  unitObj.login(id,password);
	  Assert.assertEquals(unitObj.welcome.getText(),"Welcome admin,"); 
  }
  
  @Test(priority = 1,groups = {"functional"},enabled =true)
  public void categoriesAdd() {
	  catelements.categories_add();
	  Assert.assertEquals(catelements.categories_search(exObj.excelread(4, 1)), "Maruthi");
	 
	  
  }
   @Test(priority=2,groups = {"regression"},enabled=true)
   public void categoriesEdit()
   {
	   catelements.categories_search(exObj.excelread(4, 1));
	   
	   catelements.categories_edit(exObj.excelread(4, 2));
	   Assert.assertEquals(exObj.excelread(4, 2), "Volvo");
	    
   }
  @Test(priority=3,groups = {"regression"},enabled=true)
  public void categoriesDelete()
  
  {
	  catelements.categories_search(exObj.excelread(4, 2));
	  
	  catelements.categories_delete();
	  catelements.categories_search(exObj.excelread(4, 2));
	  Assert.assertEquals(catelements.noRecords(), "No matching records found");
	  
  }
  @Parameters("browser")
  @BeforeTest(alwaysRun = true)
  public void beforeTest() {
	 
	  driver=launchBrowser(objprop.browser,objprop.url);
	  unitObj = new UnitPageElements(driver);
	  catelements= new CategoriesPageElements(driver);
  }


  @AfterTest(alwaysRun = true)
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